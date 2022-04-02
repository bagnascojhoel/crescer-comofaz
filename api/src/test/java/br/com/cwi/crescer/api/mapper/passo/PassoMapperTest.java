package br.com.cwi.crescer.api.mapper.passo;

import br.com.cwi.crescer.api.controller.request.PassoRequest;
import br.com.cwi.crescer.api.domain.Passo;
import br.com.cwi.crescer.api.domain.Post;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class PassoMapperTest {

    @InjectMocks
    PassoMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearCorretamente(){

        PassoRequest request =  new PassoRequest();
        Passo passo = new Passo();
        Post post =  new Post();

        Mockito.when(modelMapper.map(request, Passo.class)).thenReturn(passo);

        Passo result = tested.convert(request, post);

        Mockito.verify(modelMapper).map(request, Passo.class);

        Assert.assertEquals(passo.getFoto(), result.getFoto());
        Assert.assertEquals(passo.getTexto(), result.getTexto());
        Assert.assertEquals(post, result.getPost());

    }

}