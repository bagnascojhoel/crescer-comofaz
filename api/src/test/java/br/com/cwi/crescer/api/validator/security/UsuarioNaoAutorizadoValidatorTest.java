package br.com.cwi.crescer.api.validator.security;

import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioNaoAutorizadoValidatorTest {

    @InjectMocks
    UsuarioNaoAutorizadoValidator tested;

    @Test(expected = NaoAutorizadoException.class)
    public void deveLancarErroQuandoExpectidEActualIdForemDiferentes(){

        String expectedId = "a";

        String actualId = "b";

        tested.check(expectedId, actualId);

    }

    @Test()
    public void deveProsseguirQuandoExpectedidEActualIdForemIguais(){

        String expectedId = "b";

        String actualId = "b";

        tested.check(expectedId, actualId);

        Assert.assertEquals(expectedId, actualId);

    }

    @Test(expected = NaoAutorizadoException.class)
    public void deveLancarExceptionQuandoHttpStatusForDiferenteDeOK(){

        HttpStatus status = HttpStatus.NO_CONTENT;

        tested.check(status);

    }

    @Test()
    public void deveProsseguirNormalmenteQuandoStatusForOk(){

        HttpStatus status = HttpStatus.OK;

        tested.check(status);

    }

}