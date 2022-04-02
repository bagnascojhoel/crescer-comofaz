package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.controller.request.ComentarioRequest;
import br.com.cwi.crescer.api.controller.response.ComentarioResponse;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.comentario.ComentarioMapper;
import br.com.cwi.crescer.api.mapper.comentario.ComentarioResponseMapper;
import br.com.cwi.crescer.api.mapper.notificacao.NotificacaoMapper;
import br.com.cwi.crescer.api.repository.ComentarioRepository;
import br.com.cwi.crescer.api.repository.NotificacaoRepository;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.amazon.FileSaverService;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarComentarioServiceTest {

    @InjectMocks
    private CadastrarComentarioService tested;

    @Mock
    private FileSaverService fileSaverService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private ComentarioMapper comentarioMapper;

    @Mock
    private ComentarioResponseMapper comentarioResponseMapper;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private NotificacaoMapper notificacaoMapper;

    @Mock
    private NotificacaoRepository notificacaoRepository;

    @Test
    public void deveCadastrarComSucesso(){

        Integer id = 1;
        ComentarioRequest request = new ComentarioRequest();
        Optional<Post> post = Optional.of(new Post());
        Usuario usuario = new Usuario();
        Comentario comentario = new Comentario();
        ComentarioResponse response = new ComentarioResponse();
        comentario.setIdComentario(id);
        response.setIdComentario(id);
        post.get().setUsuario(usuario);
        MockMultipartFile imagem = new MockMultipartFile("imagem", new byte[0]);
        post.get().getComentarios().add(comentario);

        Mockito.when(postRepository.findById(id)).thenReturn(post);
        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(comentarioMapper.convert(request, post.get(), usuario)).thenReturn(comentario);
        Mockito.when(comentarioRepository.save(comentario)).thenReturn(comentario);
        Mockito.when(comentarioResponseMapper.convert(comentario)).thenReturn(response);

        ComentarioResponse result = tested.cadastrar(id, request, imagem);

        Mockito.verify(postRepository).findById(id);
        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(comentarioMapper).convert(request, post.get(), usuario);
        Mockito.verify(fileSaverService).write(imagem, comentario.hashCode());
        Mockito.verify(postRepository).save(post.get());
        Mockito.verify(comentarioRepository).save(comentario);
        Mockito.verify(comentarioResponseMapper).convert(comentario);

        Assert.assertEquals(post.get().getComentarios().get(id).getIdComentario(), result.getIdComentario());
    }

    @Test
    public void deveLancarExceptionQuandoPostNaoForEncontrado(){

        Integer id = 1;
        ComentarioRequest request = new ComentarioRequest();
        MockMultipartFile imagem = new MockMultipartFile("imagem", new byte[0]);

        try {
            ComentarioResponse result = tested.cadastrar(id, request, imagem);
        } catch (NaoEncontradoException e){
            Assert.assertEquals("Post não encontrado.", e.getMessage());
        }

    }
}