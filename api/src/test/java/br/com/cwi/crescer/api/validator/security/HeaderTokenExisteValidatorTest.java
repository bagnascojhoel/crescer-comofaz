package br.com.cwi.crescer.api.validator.security;

import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HeaderTokenExisteValidatorTest {

    @InjectMocks
    HeaderTokenExisteValidator tested;

    @Test(expected = ValidacaoNegocioException.class)
    public void deveLancarExceptionQuandoTokenForNull(){

        String token = null;

        tested.check(token);

    }

    @Test(expected = ValidacaoNegocioException.class)
    public void deveLancarExceptionQuandoTokenForVazio(){

        String token = "";

        tested.check(token);

    }

    @Test()
    public void deveProsseguirSemErroQuandoTokenNaoForVazio(){

        String token = "a";

        tested.check(token);

        Assert.assertFalse(token.isEmpty());

    }

    @Test()
    public void deveProsseguirSemErroQuandoTokenNaoForNulo(){

        String token = "a";

        tested.check(token);

        Assert.assertNotNull(token);

    }

}