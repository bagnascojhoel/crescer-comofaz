package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
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

@RunWith(MockitoJUnitRunner.class)
public class FiltrarPostsCategoriaServiceTest {

    @InjectMocks
    private FiltrarPostsCategoriaService tested;

    @Mock
    private PostRepository postRepository;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private ListarPostResponseMapper listarPostResponseMapper;

    @Test
    public void deveFiltrarComSucesso(){

        List<Integer> request = new ArrayList<>();
        Usuario usuario = new Usuario();
        List<Post> posts = new ArrayList<>();
        List<ListarPostResponse> response = new ArrayList<>();

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(postRepository.findDistinctByTagsIdCategoriaIn(request)).thenReturn(posts);
        Mockito.when(listarPostResponseMapper.convert(posts, usuario.getFavoritos())).thenReturn(response);

        List<ListarPostResponse> result = tested.filtrar(request);

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(postRepository).findDistinctByTagsIdCategoriaIn(request);
        Mockito.verify(listarPostResponseMapper).convert(posts, usuario.getFavoritos());

        Assert.assertEquals(response, result);
    }

}