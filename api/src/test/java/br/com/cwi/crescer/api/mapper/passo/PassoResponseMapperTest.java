package br.com.cwi.crescer.api.mapper.passo;

import br.com.cwi.crescer.api.controller.response.PassoResponse;
import br.com.cwi.crescer.api.domain.Passo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class PassoResponseMapperTest {

    @InjectMocks
    PassoResponseMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearCorretamente(){

        Passo passo = new Passo();
        PassoResponse response = new PassoResponse();

        Mockito.when(modelMapper.map(passo, PassoResponse.class)).thenReturn(response);

        PassoResponse result = tested.convert(passo);

        Mockito.verify(modelMapper).map(passo, PassoResponse.class);

        Assert.assertEquals(response, result);

    }


}