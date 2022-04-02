package br.com.cwi.crescer.api.validator.security;

import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HeaderUserIdExisteValidatorTest {

    @InjectMocks
    HeaderUserIdExisteValidator tested;

    @Test(expected = ValidacaoNegocioException.class)
    public void deveLancarExceptionQuandoUserIdForNulo(){

        String userId = null;

        tested.check(userId);

    }

    @Test(expected = ValidacaoNegocioException.class)
    public void deveLancarExceptionQuandoUserIdForVazio(){

        String userId = "";

        tested.check(userId);

    }

    @Test()
    public void deveProsseguirSemErrosQuandoUserIdForDiferenteDeNull(){

        String userId = "aaaa";

        tested.check(userId);

        Assert.assertNotNull(userId);

    }

    @Test()
    public void deveProsseguirSemErrosQuandoUserIdForDiferenteDeVazio(){

        String userId = "aaaa";

        tested.check(userId);

        Assert.assertFalse(userId.isEmpty());

    }

}