package br.com.cwi.crescer.api.service.seguranca;

import br.com.cwi.crescer.api.controller.response.AutenticarUsuarioResponse;
import br.com.cwi.crescer.api.domain.Categoria;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.AutenticarUsuarioResponseMapper;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import br.com.cwi.crescer.api.service.usuario.CadastrarUsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AutenticacaoServiceTest {

    @InjectMocks
    private AutenticacaoService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private IdentificarEmissorDoRequestService identificarEmissorDoRequestService;

    @Mock
    private CadastrarUsuarioService cadastrarUsuarioService;

    @Mock
    private AutenticarUsuarioResponseMapper autenticarUsuarioResponseMapper;

    @Test
    public void deveAutenticarUsuario(){

        Usuario emissorRequest = new Usuario();
        Optional<Usuario> usuario = Optional.of(new Usuario());
        AutenticarUsuarioResponse response = new AutenticarUsuarioResponse();
        usuario.get().getInteresses().add(new Categoria());

        Mockito.when(identificarEmissorDoRequestService.identificar()).thenReturn(emissorRequest);
        Mockito.when(usuarioRepository.findFirstByProviderIdAndProvider(emissorRequest.getProviderId(), emissorRequest.getProvider())).thenReturn(usuario);
        Mockito.when(autenticarUsuarioResponseMapper.convert(usuario.get())).thenReturn(response);

        AutenticarUsuarioResponse result = tested.autenticar();

        Mockito.verify(identificarEmissorDoRequestService).identificar();
        Mockito.verify(usuarioRepository).findFirstByProviderIdAndProvider(emissorRequest.getProviderId(), emissorRequest.getProvider());
        Mockito.verify(autenticarUsuarioResponseMapper).convert(usuario.get());

        Assert.assertFalse(result.isPrimeiroAcesso());
    }

    @Test
    public void deveCadastrarUsuario(){

        Usuario emissorRequest = new Usuario();
        Optional<Usuario> usuario = Optional.empty();
        AutenticarUsuarioResponse response = new AutenticarUsuarioResponse();
        response.setPrimeiroAcesso(true);

        Mockito.when(identificarEmissorDoRequestService.identificar()).thenReturn(emissorRequest);
        Mockito.when(usuarioRepository.findFirstByProviderIdAndProvider(emissorRequest.getProviderId(), emissorRequest.getProvider())).thenReturn(usuario);
        Mockito.when(cadastrarUsuarioService.cadastrar(emissorRequest)).thenReturn(response);

        AutenticarUsuarioResponse result = tested.autenticar();

        Mockito.verify(identificarEmissorDoRequestService).identificar();
        Mockito.verify(usuarioRepository).findFirstByProviderIdAndProvider(emissorRequest.getProviderId(), emissorRequest.getProvider());
        Mockito.verify(cadastrarUsuarioService).cadastrar(emissorRequest);

        Assert.assertTrue(result.isPrimeiroAcesso());
    }

}