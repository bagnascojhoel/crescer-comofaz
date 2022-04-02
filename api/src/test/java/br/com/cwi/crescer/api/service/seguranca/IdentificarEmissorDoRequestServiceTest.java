package br.com.cwi.crescer.api.service.seguranca;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.UsuarioMapper;
import br.com.cwi.crescer.api.security.FederationAuthentication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;

@RunWith(MockitoJUnitRunner.class)
public class IdentificarEmissorDoRequestServiceTest {

    @InjectMocks
    private IdentificarEmissorDoRequestService tested;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Test
    public void deveIdentificarComSucesso(){

        Usuario usuario = new Usuario();
        FederationAuthentication authenticated = (FederationAuthentication) SecurityContextHolder.getContext().getAuthentication();

        Mockito.when(usuarioMapper.convert(authenticated)).thenReturn(usuario);

        Usuario result = tested.identificar();

        Mockito.verify(usuarioMapper).convert(authenticated);

        Assert.assertEquals(usuario, result);
    }

}