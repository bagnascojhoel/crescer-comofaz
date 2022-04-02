package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.UsuarioBasicoResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.UsuarioBasicoResponseMapper;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExibirInformacoesBasicasUsuarioServiceTest {

    @InjectMocks
    private ExibirInformacoesBasicasUsuarioService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private UsuarioBasicoResponseMapper usuarioBasicoResponseMapper;

    @Test
    public void deveExibirInformacoesCorretamente(){

        Usuario usuario = new Usuario();

        UsuarioBasicoResponse response = new UsuarioBasicoResponse();

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);

        Mockito.when(usuarioBasicoResponseMapper.apply(usuario)).thenReturn(response);

        UsuarioBasicoResponse result = tested.doIt();

        Mockito.verify(identificarUsuarioService).doIt();

        Mockito.verify(usuarioBasicoResponseMapper).apply(usuario);

        Assert.assertEquals(response, result);

    }

}