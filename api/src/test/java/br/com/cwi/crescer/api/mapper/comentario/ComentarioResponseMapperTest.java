package br.com.cwi.crescer.api.mapper.comentario;

import br.com.cwi.crescer.api.controller.response.ComentarioResponse;
import br.com.cwi.crescer.api.domain.Comentario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class ComentarioResponseMapperTest {

    @InjectMocks
    ComentarioResponseMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearComSucesso(){

        Comentario comentario = new Comentario();
        ComentarioResponse comentarioResponse = new ComentarioResponse();

        Mockito.when(modelMapper.map(comentario, ComentarioResponse.class)).thenReturn(comentarioResponse);

        ComentarioResponse result = tested.convert(comentario);

        Mockito.verify(modelMapper).map(comentario, ComentarioResponse.class);

        Assert.assertEquals(comentarioResponse, result);

    }

}