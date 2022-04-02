package br.com.cwi.crescer.api.validator.security;

import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioNaoExisteValidatorTest {

    @InjectMocks
    UsuarioNaoExisteValidator tested;

    @Test(expected = NaoEncontradoException.class)
    public void deveLancarExceptionQuandoGooGleIdTokenForNull(){

        GoogleIdToken googleIdToken = null;

        tested.check(googleIdToken);

    }

    @Test()
    public void deveProsseguirQuandoGoogleIdTokenForDiferenteDeNull(){

        tested.check(Mockito.mock(GoogleIdToken.class));

    }

}