package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.request.PostRequest;
import br.com.cwi.crescer.api.domain.Categoria;
import br.com.cwi.crescer.api.domain.Passo;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.post.PostMapper;
import br.com.cwi.crescer.api.repository.CategoriaRepository;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.amazon.FileSaverService;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarPostServiceTest {

    @InjectMocks
    private CadastrarPostService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private FileSaverService fileSaverService;

    @Mock
    private PostMapper postMapper;

    @Test
    public void deveCadastrarComSucesso(){

        Usuario usuario = new Usuario();
        Post post = new Post();
        post.getPassos().add(new Passo());
        post.setIdPost(1);
        PostRequest request = new PostRequest();
        List<Categoria> categorias = new ArrayList<>();
        MockMultipartFile imagemCapa = new MockMultipartFile("texto", new byte[0]);
        MockMultipartFile[] imagens = new MockMultipartFile[1];
        imagens[0] = imagemCapa;

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(categoriaRepository.findAllByIdCategoriaIn(request.getIdTags())).thenReturn(categorias);
        Mockito.when(postMapper.convert(request, usuario, categorias)).thenReturn(post);
        Mockito.when(postRepository.save(post)).thenReturn(post);

        Integer result = tested.cadastrar(request, imagens, imagemCapa);

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(categoriaRepository).findAllByIdCategoriaIn(request.getIdTags());
        Mockito.verify(postMapper).convert(request, usuario, categorias);
        Mockito.verify(postRepository).save(post);
        Mockito.verify(fileSaverService).write(imagemCapa, post.hashCode());

        Assert.assertEquals(post.getIdPost(), result);
    }

}