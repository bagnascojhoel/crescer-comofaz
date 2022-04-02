package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.mapper.lista.ListarPostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ListarPostsServiceTest {

    @InjectMocks
    private ListarPostsService tested;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ListarPostResponseMapper listarPostResponseMapper;

    @Test
    public void deveListarComSucesso(){

        List<Post> posts = new ArrayList<>();
        List<ListarPostResponse> response = new ArrayList<>();

        Mockito.when(postRepository.findAll()).thenReturn(posts);
        Mockito.when(listarPostResponseMapper.convert(posts)).thenReturn(response);

        List<ListarPostResponse> result = tested.doIt();

        Mockito.verify(postRepository).findAll();
        Mockito.verify(listarPostResponseMapper).convert(posts);

        Assert.assertEquals(response, result);
    }

}