package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.UsuarioPublicoResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.usuario.UsuarioPublicoResponseMapper;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ExibirPerfilPublicoServiceTest {

    @InjectMocks
    ExibirPerfilPublicoService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioPublicoResponseMapper usuarioPublicoResponseMapper;

    @Test
    public void deveRetornarAsListasCorretamenteQuandoOUsuarioExistir(){

        Integer idUsuario = 0;

        Optional<Usuario> usuario = Optional.of(new Usuario());

        Integer totalContribuicoes = 0;

        UsuarioPublicoResponse usuarioPublicoResponse = new UsuarioPublicoResponse();

        Mockito.when(usuarioRepository.findById(idUsuario)).thenReturn(usuario);

        Mockito.when(usuarioPublicoResponseMapper.apply(usuario.get(), totalContribuicoes)).thenReturn(usuarioPublicoResponse);

        UsuarioPublicoResponse result = tested.doIt(idUsuario);

        Mockito.verify(usuarioRepository).findById(idUsuario);

        Mockito.verify(usuarioPublicoResponseMapper).apply(usuario.get(), totalContribuicoes);

        Assert.assertEquals(usuarioPublicoResponse, result);

    }

    @Test(expected = NaoEncontradoException.class)
    public void deveLancarNaoEncontradoExceptionQuandousuarioNaoExistir(){

        Integer idUsuario = 0;

        Optional<Usuario> usuario = Optional.empty();

        Integer totalContribuicoes = 0;

        UsuarioPublicoResponse usuarioPublicoResponse = new UsuarioPublicoResponse();

        Mockito.when(usuarioRepository.findById(idUsuario)).thenReturn(usuario);

        try {

            tested.doIt(idUsuario);

        } catch (NaoEncontradoException exception) {

            Mockito.verify(usuarioRepository).findById(idUsuario);

            throw exception;
        }

    }

}