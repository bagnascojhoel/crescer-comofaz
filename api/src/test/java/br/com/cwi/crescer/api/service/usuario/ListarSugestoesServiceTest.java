package br.com.cwi.crescer.api.service.usuario;

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
public class ListarSugestoesServiceTest {

    @InjectMocks
    private ListarSugestoesService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ListarPostResponseMapper listarPostResponseMapper;

    @Test
    public void deveListarCorretamente(){

        Usuario usuario = new Usuario();

        List<Post> posts = new ArrayList<>();

        List<Post> favoritosUsuario = new ArrayList<>();

        List<ListarPostResponse> postsResponse = new ArrayList<>();

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);

        Mockito.when(postRepository.findDistinctByTagsInOrderByIdPostDesc(usuario.getInteresses())).thenReturn(posts);

        Mockito.when(listarPostResponseMapper.convert(posts, favoritosUsuario)).thenReturn(postsResponse);

        List<ListarPostResponse> result = tested.listar();

        Mockito.verify(identificarUsuarioService).doIt();

        Mockito.verify(postRepository).findDistinctByTagsInOrderByIdPostDesc(usuario.getInteresses());

        Mockito.verify(listarPostResponseMapper).convert(posts, favoritosUsuario);

        Assert.assertEquals(postsResponse, result);

    }

}