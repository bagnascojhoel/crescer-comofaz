package br.com.cwi.crescer.api.mapper.post;

import br.com.cwi.crescer.api.controller.response.PostResponse;
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
public class PostResponseMapperTest {

    @InjectMocks
    PostResponseMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearCorretamente(){

        Post post = new Post();
        PostResponse response = new PostResponse();

        Mockito.when(modelMapper.map(post, PostResponse.class)).thenReturn(response);

        PostResponse result = tested.convert(post, true, true);

        Mockito.verify(modelMapper).map(post, PostResponse.class);

        Assert.assertEquals(response, result);
    }

}