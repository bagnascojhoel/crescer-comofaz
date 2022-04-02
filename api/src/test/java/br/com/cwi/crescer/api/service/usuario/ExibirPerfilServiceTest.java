package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.UsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.UsuarioResponseMapper;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExibirPerfilServiceTest {

    @InjectMocks
    private ExibirPerfilService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private UsuarioResponseMapper usuarioResponseMapper;

    @Test
    public void deveExibirPerfilCorretamente(){

        Usuario usuario = new Usuario();

        Integer totalContribuicoes = 0;

        UsuarioResponse response = new UsuarioResponse();

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);

        Mockito.when(usuarioResponseMapper.convert(usuario, totalContribuicoes)).thenReturn(response);

        UsuarioResponse result = tested.exibir();

        Mockito.verify(identificarUsuarioService).doIt();

        Mockito.verify(usuarioResponseMapper).convert(usuario, totalContribuicoes);

        Assert.assertEquals(response, result);

    }

}