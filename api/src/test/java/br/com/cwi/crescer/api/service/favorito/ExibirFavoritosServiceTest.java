package br.com.cwi.crescer.api.service.favorito;

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
public class ExibirFavoritosServiceTest {

    @InjectMocks
    private ExibirFavoritosService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private ListarPostResponseMapper listarPostResponseMapper;

    @Test
    public void deveExibirComSucesso(){

        Usuario usuario = new Usuario();
        List<ListarPostResponse> response = new ArrayList<>();

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(listarPostResponseMapper.convertFavoritos(usuario.getFavoritos())).thenReturn(response);

        List<ListarPostResponse> result = tested.exibir();

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(listarPostResponseMapper).convertFavoritos(usuario.getFavoritos());

        Assert.assertEquals(response, result);
    }

}