package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.ListaSimplificadaResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.usuario.BuscarListaPostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class BuscarListasSimplificadasServiceTest {

    @InjectMocks
    private BuscarListasSimplificadasService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private BuscarListaPostResponseMapper buscarListaPostResponseMapper;

    @Test
    public void deveBuscarListasCorretamenteQuandoOPostExistirNoBanco() {

        Usuario usuario = new Usuario();

        Integer idPost = 0;

        Optional<Post> post = Optional.of(new Post());

        List<ListaSimplificadaResponse> listaSimplificada = new ArrayList<>();

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);

        Mockito.when(postRepository.findById(idPost)).thenReturn(post);

        Mockito.when(buscarListaPostResponseMapper.convert(usuario.getListas(), post.get())).thenReturn(listaSimplificada);

        List<ListaSimplificadaResponse> result = tested.buscar(idPost);

        Mockito.verify(identificarUsuarioService).doIt();

        Mockito.verify(postRepository).findById(idPost);

        Mockito.verify(buscarListaPostResponseMapper).convert(usuario.getListas(), post.get());

        Assert.assertEquals(listaSimplificada, result);

    }

    @Test(expected = NaoEncontradoException.class)
    public void deveLancarExceptionSeOPostNaoExistirNoBanco() {

        Usuario usuario = new Usuario();

        Integer idPost = 0;

        Optional<Post> post = Optional.empty();

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);

        Mockito.when(postRepository.findById(idPost)).thenReturn(post);

        try {

            tested.buscar(idPost);

        } catch (NaoEncontradoException exception) {

            Mockito.verify(identificarUsuarioService).doIt();

            Mockito.verify(postRepository).findById(idPost);

            throw exception;
        }

    }

}