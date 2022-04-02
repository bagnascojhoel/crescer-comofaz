package br.com.cwi.crescer.api.service.lista;

import br.com.cwi.crescer.api.controller.request.ListaRequest;
import br.com.cwi.crescer.api.controller.response.ListaHeaderResponse;
import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.lista.ListaHeaderResponseMapper;
import br.com.cwi.crescer.api.mapper.lista.ListaMapper;
import br.com.cwi.crescer.api.repository.ListaRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarListaServiceTest {

    @InjectMocks
    private CadastrarListaService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private ListaRepository listaRepository;

    @Mock
    private ListaMapper listaMapper;

    @Mock
    private ListaHeaderResponseMapper listaHeaderResponseMapper;

    @Test
    public void deveCadastrarComSucesso(){

        Usuario usuario = new Usuario();
        Lista lista = new Lista();
        ListaRequest request = new ListaRequest();
        ListaHeaderResponse response = new ListaHeaderResponse();

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(listaMapper.convert(request, usuario)).thenReturn(lista);
        Mockito.when(listaRepository.save(lista)).thenReturn(lista);
        Mockito.when(listaHeaderResponseMapper.convert(lista)).thenReturn(response);

        ListaHeaderResponse result = tested.cadastrar(request);

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(listaMapper).convert(request, usuario);
        Mockito.verify(listaRepository).save(lista);
        Mockito.verify(listaHeaderResponseMapper).convert(lista);

        Assert.assertEquals(response, result);
    }

}