package br.com.cwi.crescer.api.mapper.resposta;

import br.com.cwi.crescer.api.controller.response.RespostaResponse;
import br.com.cwi.crescer.api.domain.Resposta;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class RespostaResponseMapperTest {

    @InjectMocks
    RespostaResponseMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearCorretamente(){

        Resposta resposta = new Resposta();

        RespostaResponse response = new RespostaResponse();

        Mockito.when(modelMapper.map(resposta, RespostaResponse.class)).thenReturn(response);

        RespostaResponse result = tested.convert(resposta);

        Mockito.verify(modelMapper).map(resposta, RespostaResponse.class);

        Assert.assertEquals(response, result);

    }

}