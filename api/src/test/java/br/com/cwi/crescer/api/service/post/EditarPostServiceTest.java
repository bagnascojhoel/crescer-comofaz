package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.request.PostRequest;
import br.com.cwi.crescer.api.domain.Categoria;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.passo.PassoMapper;
import br.com.cwi.crescer.api.mapper.post.PostMapper;
import br.com.cwi.crescer.api.repository.CategoriaRepository;
import br.com.cwi.crescer.api.repository.PassoRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class EditarPostServiceTest {

    @InjectMocks
    private EditarPostService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostMapper postMapper;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private FileSaverService fileSaverService;

    @Mock
    private PassoMapper passoMapper;

    @Mock
    private PassoRepository passoRepository;

    @Test
    public void deveEditarComSucesso(){

        Usuario usuario = new Usuario();
        Optional<Post> postAEditar = Optional.of(new Post());
        Post post = new Post();
        List<Categoria> categorias = new ArrayList<>();
        MockMultipartFile[] imagens = new MockMultipartFile[1];
        MockMultipartFile imagem = new MockMultipartFile("texto", new byte[0]);
        PostRequest request = new PostRequest();
        request.setTitulo("titulo 1");
        post.setTitulo("titulo 2");
        usuario.getPosts().add(postAEditar.get());
        Integer idPost = 1;
        post.setIdPost(idPost);
        String url = "url";

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(categoriaRepository.findAllByIdCategoriaIn(request.getIdTags())).thenReturn(categorias);
        Mockito.when(postRepository.findById(idPost)).thenReturn(postAEditar);
        Mockito.when(postMapper.convert(request, usuario, categorias, postAEditar.get())).thenReturn(post);
        Mockito.when(postRepository.save(post)).thenReturn(post);
        Mockito.when(fileSaverService.write(imagem, post.hashCode())).thenReturn(url);

        Integer result = tested.doIt(idPost, request, imagens, imagem);

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(categoriaRepository).findAllByIdCategoriaIn(request.getIdTags());
        Mockito.verify(postRepository).findById(idPost);
        Mockito.verify(postMapper).convert(request, usuario, categorias, postAEditar.get());
        Mockito.verify(postRepository).save(post);

        Assert.assertEquals(idPost, result);
    }

    @Test
    public void deveLancarExceptionQuandoEditarPostDeOutroUsuario(){

        try {
            tested.doIt(null, new PostRequest(), null, null);
        } catch (NaoEncontradoException e){
            Assert.assertEquals("Post não encontrado.", e.getMessage());
        }
    }

    @Test
    public void deveLancarExceptionQuandoPostNaoForEncontrado(){

        try {
            Usuario usuario = new Usuario();
            Optional<Post> postAEditar = Optional.of(new Post());
            PostRequest request = new PostRequest();
            List<Categoria> categorias = new ArrayList<>();
            MockMultipartFile[] imagens = new MockMultipartFile[1];
            MockMultipartFile imagem = new MockMultipartFile("texto", new byte[0]);
            Integer IdPost = 1;

            Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
            Mockito.when(postRepository.findById(IdPost)).thenReturn(postAEditar);

            tested.doIt(IdPost, request, imagens, imagem);

        } catch (NaoAutorizadoException e){
            Assert.assertEquals("O post que você está tentando editar não pertence a você.", e.getMessage());
        }

    }

}