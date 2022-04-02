package br.com.cwi.crescer.api.service.lista;

import br.com.cwi.crescer.api.controller.response.ListaResponse;
import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.lista.ListaResponseMapper;
import br.com.cwi.crescer.api.repository.ListaRepository;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import br.com.cwi.crescer.api.service.feito.AtualizarFeitoService;
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
public class BuscarListaPublicoServiceTest {

    @InjectMocks
    private BuscarListaPublicoService tested;

    @Mock
    private ListaRepository listaRepository;

    @Mock
    private ListaResponseMapper listaResponseMapper;

    @Test
    public void deveBuscarComSucesso(){

        Optional<Lista> lista = Optional.of(new Lista());
        Integer idLista = 1;
        ListaResponse response = new ListaResponse();

        Mockito.when(listaRepository.findById(idLista)).thenReturn(lista);
        Mockito.when(listaResponseMapper.convert(lista.get())).thenReturn(response);

        ListaResponse result = tested.doIt(idLista);

        Mockito.verify(listaRepository).findById(idLista);
        Mockito.verify(listaResponseMapper).convert(lista.get());

        Assert.assertEquals(response, result);
    }


    @Test
    public void deveLancarExceptionQuandoListaNaoForEncontrada(){

        try {
            tested.doIt(null);
        } catch (NaoEncontradoException e){
            Assert.assertEquals("Lista não encontrada.", e.getMessage());
        }

    }

    @Test
    public void deveLancarExceptionQuandoListaForPrivada(){
        Optional<Lista> lista = Optional.of(new Lista());
        lista.get().setPrivado(true);
        Integer idLista = 1;

        Mockito.when(listaRepository.findById(idLista)).thenReturn(lista);


        try {
            tested.doIt(idLista);
        } catch (NaoAutorizadoException e){
            Mockito.verify(listaRepository).findById(idLista);
            Assert.assertEquals("A lista que você está buscando é privada.", e.getMessage());
        }

    }
}