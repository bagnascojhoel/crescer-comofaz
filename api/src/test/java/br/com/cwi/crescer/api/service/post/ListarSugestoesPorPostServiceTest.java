package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.lista.ListarPostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
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
public class ListarSugestoesPorPostServiceTest {

    @InjectMocks
    private ListarSugestoesPorPostService tested;

    @Mock
    private PostRepository postRepository;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private ListarPostResponseMapper listarPostResponseMapper;

    @Test
    public void deveListarComSucesso(){

        Usuario usuario = new Usuario();
        Optional<Post> post = Optional.of(new Post());
        List<Post> posts = new ArrayList<>();
        List<ListarPostResponse> response = new ArrayList<>();
        Integer idPost = 1;

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(postRepository.findById(idPost)).thenReturn(post);
        Mockito.when(postRepository.findDistinctByTagsInOrderByIdPostDesc(post.get().getTags())).thenReturn(posts);
        Mockito.when(listarPostResponseMapper.convert(posts, usuario.getFavoritos())).thenReturn(response);

        List<ListarPostResponse> result = tested.doIt(idPost);

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(postRepository).findById(idPost);
        Mockito.verify(postRepository).findDistinctByTagsInOrderByIdPostDesc(post.get().getTags());
        Mockito.verify(listarPostResponseMapper).convert(posts, usuario.getFavoritos());

        Assert.assertEquals(response, result);
    }

    @Test
    public void deveLancarExceptionQuandoPostNaoForEncontrado(){

        try {
            List<ListarPostResponse> result = tested.doIt(null);
        } catch (NaoEncontradoException e){
            Assert.assertEquals("Post não encontrado.", e.getMessage());
        }

    }

}