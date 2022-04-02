package br.com.cwi.crescer.api.service.seguranca;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
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
public class IdentificarUsuarioServiceTest {

    @InjectMocks
    private IdentificarUsuarioService tested;

    @Mock
    private IdentificarEmissorDoRequestService identificarEmissorDoRequestService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void deveIdentificarComSucesso(){

        Usuario emissorRequest = new Usuario();
        Optional<Usuario> usuario = Optional.of(new Usuario());

        Mockito.when(identificarEmissorDoRequestService.identificar()).thenReturn(emissorRequest);
        Mockito.when(usuarioRepository.findFirstByProviderIdAndProvider(emissorRequest.getProviderId(), emissorRequest.getProvider())).thenReturn(usuario);

        Usuario result = tested.doIt();

        Mockito.verify(identificarEmissorDoRequestService).identificar();
        Mockito.verify(usuarioRepository).findFirstByProviderIdAndProvider(emissorRequest.getProviderId(), emissorRequest.getProvider());

        Assert.assertEquals(usuario.get(), result);
    }

    @Test
    public void deveLancarExceptionQuandoUsuarioNaoForAutorizado(){

        Usuario emissorRequest = new Usuario();
        Optional<Usuario> usuario = Optional.empty();

        Mockito.when(identificarEmissorDoRequestService.identificar()).thenReturn(emissorRequest);
        Mockito.when(usuarioRepository.findFirstByProviderIdAndProvider(emissorRequest.getProviderId(), emissorRequest.getProvider())).thenReturn(usuario);

        try {
            tested.doIt();
        } catch (NaoAutorizadoException e){
            Assert.assertEquals("Usuário não autorizado.", e.getMessage());
        }
    }

}