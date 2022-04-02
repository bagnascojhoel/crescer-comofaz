package br.com.cwi.crescer.api.validator.security;

import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HeaderProviderExisteValidatorTest {

    @InjectMocks
    HeaderProviderExisteValidator tested;

    @Test(expected = ValidacaoNegocioException.class)
    public void deveLancarExceptionQuandoProviderForNulo(){

        String provider = null;

        tested.check(provider);

    }

    @Test(expected = ValidacaoNegocioException.class)
    public void deveLancarExceptionQuandoProviderForVazio(){

        String provider = "";

        tested.check(provider);

    }

    @Test()
    public void deveProsseguirSemErrosQuandoProviderForDiferenteDeNull(){

        String provider = "aaaa";

        tested.check(provider);

        Assert.assertNotNull(provider);

    }

    @Test()
    public void deveProsseguirSemErrosQuandoProviderForDiferenteDeVazio(){

        String provider = "aaaa";

        tested.check(provider);

        Assert.assertFalse(provider.isEmpty());

    }

}