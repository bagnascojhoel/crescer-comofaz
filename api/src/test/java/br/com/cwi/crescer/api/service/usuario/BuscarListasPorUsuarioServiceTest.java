package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.ListaHeaderResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.lista.ListaHeaderResponseMapper;
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
public class BuscarListasPorUsuarioServiceTest {

    @InjectMocks
    private BuscarListasPorUsuarioService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private ListaHeaderResponseMapper listaHeaderResponseMapper;

    @Test
    public void deveRetornarListaCorretamente(){

        Usuario usuario = new Usuario();

        List<ListaHeaderResponse> listasResponse = new ArrayList<>();

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);

        Mockito.when(listaHeaderResponseMapper.convert(usuario.getListas())).thenReturn(listasResponse);

        List<ListaHeaderResponse> result = tested.buscar();

        Mockito.verify(identificarUsuarioService).doIt();

        Mockito.verify(listaHeaderResponseMapper).convert(usuario.getListas());

        Assert.assertEquals(listasResponse, result);

    }


}