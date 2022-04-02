package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.controller.request.ComentarioRequest;
import br.com.cwi.crescer.api.controller.response.ComentarioResponse;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Notificacao;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.domain.enums.TipoNotificacao;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.comentario.ComentarioMapper;
import br.com.cwi.crescer.api.mapper.comentario.ComentarioResponseMapper;
import br.com.cwi.crescer.api.mapper.notificacao.NotificacaoMapper;
import br.com.cwi.crescer.api.repository.ComentarioRepository;
import br.com.cwi.crescer.api.repository.NotificacaoRepository;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.amazon.FileSaverService;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CadastrarComentarioService {

    @Autowired
    private FileSaverService fileSaverService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ComentarioMapper comentarioMapper;

    @Autowired
    private NotificacaoMapper notificacaoMapper;

    @Autowired
    private ComentarioResponseMapper comentarioResponseMapper;

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Transactional
    public ComentarioResponse cadastrar(Integer idPost, ComentarioRequest request, MultipartFile imagem) {

        Optional<Post> post = postRepository.findById(idPost);

        if (!post.isPresent())
            throw new NaoEncontradoException("Post não encontrado.");

        Usuario usuario = identificarUsuarioService.doIt();

        Comentario comentario = comentarioMapper.convert(request, post.get(), usuario);

        comentario.setFoto(fileSaverService.write(imagem, comentario.hashCode()));

        post.get().getComentarios().add(comentario);

        postRepository.save(post.get());
        comentario =  comentarioRepository.save(comentario);

        if (!post.get().getUsuario().equals(usuario)) {

            Notificacao notificacao = notificacaoMapper.convert(comentario, post.get().getUsuario(), usuario, TipoNotificacao.COMENTARIO);

            notificacaoRepository.save(notificacao);
        }

        return comentarioResponseMapper.convert(comentario);

    }

}