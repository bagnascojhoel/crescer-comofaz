package br.com.cwi.crescer.api.service.lista;

import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.repository.ListaRepository;
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
public class AtualizarPostListaServiceTest {

    @InjectMocks
    private AtualizarPostListaService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private ListaRepository listaRepository;

    @Mock
    private PostRepository postRepository;

    @Test
    public void deveCadastrarNovoPostNaLista(){

        Usuario usuario = new Usuario();
        Optional<Lista> lista = Optional.of(new Lista());
        lista.get().setUsuario(usuario);
        Optional<Post> post = Optional.of(new Post());
        Integer id = 1;

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(listaRepository.findById(id)).thenReturn(lista);
        Mockito.when(postRepository.findById(id)).thenReturn(post);

        boolean result = tested.atualizar(id, id);

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(listaRepository).findById(id);
        Mockito.verify(postRepository).findById(id);
        Mockito.verify(listaRepository).save(lista.get());

        Assert.assertTrue(result);
    }

    @Test
    public void deveRemoverPostNaLista(){

        Usuario usuario = new Usuario();
        Optional<Lista> lista = Optional.of(new Lista());
        lista.get().setUsuario(usuario);
        Optional<Post> post = Optional.of(new Post());
        lista.get().getPosts().add(post.get());
        Integer id = 1;

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(listaRepository.findById(id)).thenReturn(lista);
        Mockito.when(postRepository.findById(id)).thenReturn(post);

        boolean result = tested.atualizar(id, id);

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(listaRepository).findById(id);
        Mockito.verify(postRepository).findById(id);
        Mockito.verify(listaRepository).save(lista.get());

        Assert.assertFalse(result);
    }

    @Test
    public void deveLancarExceptionQuandoListaoNaoForEncontrada(){

        try {
            tested.atualizar(null, 1);
        } catch (NaoEncontradoException e){
            Assert.assertEquals("Lista não encontrada.", e.getMessage());
        }
    }

    @Test
    public void deveLancarExceptionQuandoPostNaoForEncontrada(){

        Optional<Lista> lista = Optional.of(new Lista());

        Mockito.when(listaRepository.findById(1)).thenReturn(lista);

        try {
            tested.atualizar(1, null);
        } catch (NaoEncontradoException e){
            Assert.assertEquals("Post não encontrado.", e.getMessage());
        }
    }

    @Test
    public void deveLancarExceptionQuandoListaNaoForDoUsuario(){

        Usuario usuario = new Usuario();
        Usuario usuarioCriadorLista = new Usuario();
        Optional<Lista> lista = Optional.of(new Lista());
        lista.get().setUsuario(usuarioCriadorLista);
        Optional<Post> post = Optional.of(new Post());
        Integer id = 1;

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(listaRepository.findById(id)).thenReturn(lista);
        Mockito.when(postRepository.findById(id)).thenReturn(post);

        try {
            tested.atualizar(id, id);
        } catch (NaoAutorizadoException e){
            Assert.assertEquals("A lista que você está tentando editar não é sua.", e.getMessage());
        }
    }

}