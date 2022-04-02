package br.com.cwi.crescer.api.service.feito;

import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.lista.ListarPostResponseMapper;
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

@RunWith(MockitoJUnitRunner.class)
public class ExibirFeitosServiceTest {

    @InjectMocks
    private ExibirFeitosService tested;

    @Mock
    private ListarPostResponseMapper listarPostResponseMapper;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Test
    public void deveExibirComSucesso(){

        Usuario usuario = new Usuario();
        List<ListarPostResponse> response = new ArrayList<>();

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(listarPostResponseMapper.convertFeito(usuario.getFeitos(), usuario.getFavoritos())).thenReturn(response);

        List<ListarPostResponse> result = tested.exibir();

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(listarPostResponseMapper).convertFeito(usuario.getFavoritos(), usuario.getFeitos());

        Assert.assertEquals(response, result);
    }

}