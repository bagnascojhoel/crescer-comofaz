package br.com.cwi.crescer.api.service.resposta;

import br.com.cwi.crescer.api.controller.response.RespostaResponse;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Notificacao;
import br.com.cwi.crescer.api.domain.Resposta;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.domain.enums.TipoNotificacao;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.notificacao.NotificacaoMapper;
import br.com.cwi.crescer.api.mapper.resposta.RespostaMapper;
import br.com.cwi.crescer.api.mapper.resposta.RespostaResponseMapper;
import br.com.cwi.crescer.api.repository.ComentarioRepository;
import br.com.cwi.crescer.api.repository.NotificacaoRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastrarRespostaService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private RespostaMapper respostaMapper;

    @Autowired
    private RespostaResponseMapper respostaResponseMapper;

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private NotificacaoMapper notificacaoMapper;

    public RespostaResponse cadastrar(Integer idComentario, String texto) {

        Optional<Comentario> comentario = comentarioRepository.findById(idComentario);

        if (!comentario.isPresent())
            throw new NaoEncontradoException("Comentario não encontrado.");

        Usuario usuario = identificarUsuarioService.doIt();

        Resposta resposta = respostaMapper.convert(texto, comentario.get(), usuario);

        comentario.get().getRespostas().add(resposta);

        comentarioRepository.save(comentario.get());


        if (!comentario.get().getUsuario().equals(usuario)) {

            Notificacao notificacao = notificacaoMapper.convert(comentario.get(), comentario.get().getUsuario(), usuario, TipoNotificacao.RESPOSTA);

            notificacaoRepository.save(notificacao);
        }

        return respostaResponseMapper.convert(resposta);

    }

}