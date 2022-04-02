package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.AutenticarUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.AutenticarUsuarioResponseMapper;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarUsuarioServiceTest {

    @InjectMocks
    private CadastrarUsuarioService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AutenticarUsuarioResponseMapper autenticarUsuarioResponseMapper;

    @Test
    public void deveCadastrarUsuarioCorretamente(){

        Usuario usuario = new Usuario();

        AutenticarUsuarioResponse response = new AutenticarUsuarioResponse();

        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Mockito.when(autenticarUsuarioResponseMapper.convert(usuario)).thenReturn(response);

        AutenticarUsuarioResponse result = tested.cadastrar(usuario);

        Mockito.verify(usuarioRepository).save(usuario);

        Mockito.verify(autenticarUsuarioResponseMapper).convert(usuario);

        Assert.assertTrue(result.isPrimeiroAcesso());
        Assert.assertEquals(response, result);

    }

}