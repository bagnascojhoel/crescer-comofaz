package br.com.cwi.crescer.api.service.favorito;

import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
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
public class AtualizarFavoritoServiceTest {

    @InjectMocks
    private AtualizarFavoritoService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PostRepository postRepository;

    @Test
    public void deveAdicionarFavoritoQuandoNaoExistir(){

        Usuario usuario = new Usuario();

        Optional<Post> post = Optional.of(new Post());
        Integer idPost = 1;

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(postRepository.findById(idPost)).thenReturn(post);

        boolean result = tested.adicionar(idPost);

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(postRepository).findById(1);
        Mockito.verify(usuarioRepository).save(usuario);

        Assert.assertTrue(result);
    }

    @Test
    public void deveRemoverFavoritoQuandoExistir(){

        Usuario usuario = new Usuario();

        Optional<Post> post = Optional.of(new Post());
        Integer idPost = 1;
        usuario.getFavoritos().add(post.get());

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(postRepository.findById(idPost)).thenReturn(post);

        boolean result = tested.adicionar(idPost);

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(postRepository).findById(1);
        Mockito.verify(usuarioRepository).save(usuario);

        Assert.assertFalse(result);
    }

    @Test
    public void deveLancarExceptionQuandoPostNaoForEncontrado(){

        try {
            boolean result = tested.adicionar(null);
        } catch (NaoEncontradoException e){
            Assert.assertEquals("Post não encontrado.", e.getMessage());
        }

    }
}