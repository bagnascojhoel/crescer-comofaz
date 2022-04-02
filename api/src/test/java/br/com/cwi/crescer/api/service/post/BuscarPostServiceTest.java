package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.PostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.post.PostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
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
public class BuscarPostServiceTest {

    @InjectMocks
    private BuscarPostService tested;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostResponseMapper postResponseMapper;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Test
    public void deveBuscarCorretamente(){

        Optional<Post> post = Optional.of(new Post());
        Usuario usuario = new Usuario();
        PostResponse response = new PostResponse();
        Integer idPost = 1;

        Mockito.when(postRepository.findById(idPost)).thenReturn(post);
        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(postResponseMapper.convert(post.get(), false, false)).thenReturn(response);

        PostResponse result = tested.buscar(idPost);

        Mockito.verify(postRepository).findById(idPost);
        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(postResponseMapper).convert(post.get(), false, false);

        Assert.assertEquals(response, result);
    }

    @Test
    public void deveLancarExceptionQuandoPostNaoForEncontrado(){

        try {
            tested.buscar(null);
        } catch (NaoEncontradoException e){
            Assert.assertEquals("Post não encontrado.", e.getMessage());
        }
    }

}