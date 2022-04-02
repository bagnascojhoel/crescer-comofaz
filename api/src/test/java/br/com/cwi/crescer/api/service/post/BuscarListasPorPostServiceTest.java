package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.ListaResponse;
import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.lista.ListaResponseMapper;
import br.com.cwi.crescer.api.repository.ListaRepository;
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
public class BuscarListasPorPostServiceTest {

    @InjectMocks
    private BuscarListasPorPostService tested;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ListaRepository listaRepository;

    @Mock
    private ListaResponseMapper listaResponseMapper;

    @Test
    public void deveBuscarCorretamente(){

        Optional<Post> post = Optional.of(new Post());
        List<Lista> listas = new ArrayList<>();

        List<ListaResponse> response = new ArrayList<>();
        Integer idPost = 1;

        Mockito.when(postRepository.findById(idPost)).thenReturn(post);
        Mockito.when(listaRepository.findDistinctByPostsTagsInAndIsPrivadoFalseOrderByIdListaDesc(post.get().getTags())).thenReturn(listas);
        Mockito.when(listaResponseMapper.convert(listas)).thenReturn(response);

        List<ListaResponse> result = tested.doIt(idPost);

        Mockito.verify(postRepository).findById(idPost);
        Mockito.verify(listaRepository).findDistinctByPostsTagsInAndIsPrivadoFalseOrderByIdListaDesc(post.get().getTags());
        Mockito.verify(listaResponseMapper).convert(listas);

        Assert.assertEquals(response, result);
    }

    @Test(expected = NaoEncontradoException.class)
    public void deveLancarExceptionQuandoPostNaoForEncontrado(){

        Optional<Post> post = Optional.empty();
        Integer idPost = 1;

        Mockito.when(postRepository.findById(idPost)).thenReturn(post);

        try {
            tested.doIt(idPost);
        } catch (NaoEncontradoException e){
            Mockito.verify(postRepository).findById(idPost);
            throw e;
        }
    }

}