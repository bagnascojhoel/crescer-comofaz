package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
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
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ListarSugestoesPorPostPublicoServiceTest {


    @InjectMocks
    private ListarSugestoesPorPostPublicoService tested;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ListarPostResponseMapper listarPostResponseMapper;

    @Test
    public void deveListarComSucesso(){

        Optional<Post> post = Optional.of(new Post());
        List<Post> posts = new ArrayList<>();
        List<ListarPostResponse> response = new ArrayList<>();
        Integer idPost = 1;

        Mockito.when(postRepository.findById(idPost)).thenReturn(post);
        Mockito.when(postRepository.findDistinctByTagsInOrderByIdPostDesc(post.get().getTags())).thenReturn(posts);
        Mockito.when(listarPostResponseMapper.convert(posts)).thenReturn(response);

        List<ListarPostResponse> result = tested.doIt(idPost);

        Mockito.verify(postRepository).findById(idPost);
        Mockito.verify(postRepository).findDistinctByTagsInOrderByIdPostDesc(post.get().getTags());
        Mockito.verify(listarPostResponseMapper).convert(posts);

        Assert.assertEquals(response, result);
    }

    @Test(expected = NaoEncontradoException.class)
    public void deveLancarExceptionQuandoPostNaoForEncontrado(){

        Optional<Post> post = Optional.empty();
        Integer idPost = 1;

        Mockito.when(postRepository.findById(idPost)).thenReturn(post);

        try {
            List<ListarPostResponse> result = tested.doIt(idPost);
        } catch (NaoEncontradoException e){
            Mockito.verify(postRepository).findById(idPost);
            throw e;
        }

    }

}