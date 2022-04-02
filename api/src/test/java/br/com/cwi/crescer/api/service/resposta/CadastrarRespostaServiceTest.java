package br.com.cwi.crescer.api.service.resposta;

import br.com.cwi.crescer.api.controller.response.RespostaResponse;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Resposta;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.notificacao.NotificacaoMapper;
import br.com.cwi.crescer.api.mapper.resposta.RespostaMapper;
import br.com.cwi.crescer.api.mapper.resposta.RespostaResponseMapper;
import br.com.cwi.crescer.api.repository.ComentarioRepository;
import br.com.cwi.crescer.api.repository.NotificacaoRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarRespostaServiceTest {

    @InjectMocks
    private CadastrarRespostaService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private RespostaMapper respostaMapper;

    @Mock
    private RespostaResponseMapper respostaResponseMapper;

    @Mock
    private NotificacaoRepository notificacaoRepository;

    @Mock
    private NotificacaoMapper notificacaoMapper;

    @Test
    public void deveCadastrarComSucesso(){

        Usuario usuario = new Usuario();
        Optional<Comentario> comentario = Optional.of(new Comentario());
        comentario.get().setUsuario(usuario);
        Resposta resposta = new Resposta();
        RespostaResponse response = new RespostaResponse();
        Integer idComentario = 1;
        String texto = "texto";
        response.setTexto(texto);

        Mockito.when(comentarioRepository.findById(idComentario)).thenReturn(comentario);
        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(respostaMapper.convert(texto, comentario.get(), usuario)).thenReturn(resposta);
        Mockito.when(respostaResponseMapper.convert(resposta)).thenReturn(response);

        RespostaResponse result = tested.cadastrar(idComentario, texto);

        Mockito.verify(comentarioRepository).findById(idComentario);
        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(respostaMapper).convert(texto, comentario.get(), usuario);
        Mockito.verify(respostaResponseMapper).convert(resposta);
 
        Assert.assertEquals(texto, result.getTexto());
    }

    @Test
    public void deveLancarExceptionQuandoComentarioNaoForEncontrado(){

        try {
            tested.cadastrar(null, "texto");
        } catch (NaoEncontradoException e){
            Assert.assertEquals("Comentario não encontrado.", e.getMessage());
        }
    }

}