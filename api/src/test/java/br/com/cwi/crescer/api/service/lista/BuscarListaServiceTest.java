package br.com.cwi.crescer.api.service.lista;


import br.com.cwi.crescer.api.controller.response.ListaResponse;
import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.lista.ListaResponseMapper;
import br.com.cwi.crescer.api.repository.ListaRepository;
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
public class BuscarListaServiceTest {

    @InjectMocks
    private BuscarListaService tested;

    @Mock
    private ListaRepository listaRepository;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private ListaResponseMapper listaResponseMapper;

    @Test
    public void deveBuscarComSucesso(){

        Optional<Lista> lista = Optional.of(new Lista());
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2);
        lista.get().setPrivado(true);
        lista.get().setUsuario(usuario);
        ListaResponse response = new ListaResponse();
        Integer idLista = 1;

        Mockito.when(listaRepository.findById(idLista)).thenReturn(lista);
        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(listaResponseMapper.convert(lista.get())).thenReturn(response);

        ListaResponse result = tested.buscar(idLista);

        Mockito.verify(listaRepository).findById(idLista);
        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(listaResponseMapper).convert(lista.get());

        Assert.assertEquals(response, result);
    }

    @Test
    public void deveLancarExceptionQuandoUsuarioNaoForCriadorDaLista(){

        Optional<Lista> lista = Optional.of(new Lista());
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2);
        lista.get().setUsuario(new Usuario());
        lista.get().setPrivado(true);
        Integer idLista = 1;

        Mockito.when(listaRepository.findById(idLista)).thenReturn(lista);
        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);

        try {
            tested.buscar(idLista);
        } catch (NaoAutorizadoException e){
            Assert.assertEquals("Esta lista é privada e não pertence a você." , e.getMessage());
        }

        Mockito.verify(listaRepository).findById(idLista);
        Mockito.verify(identificarUsuarioService).doIt();
    }

    @Test
    public void deveLancarExceptionQuandoListaNaoForEncontrado(){

        try {
            tested.buscar(null);
        } catch (NaoEncontradoException e){
            Assert.assertEquals("Lista não encontrada.", e.getMessage());
        }

    }
}