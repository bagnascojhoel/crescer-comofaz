package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.request.EditarUsuarioRequest;
import br.com.cwi.crescer.api.controller.response.UsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.EditarUsuarioMapper;
import br.com.cwi.crescer.api.mapper.usuario.UsuarioResponseMapper;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
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

@RunWith(MockitoJUnitRunner.class)
public class EditarPerfilServiceTest {

    @InjectMocks
    private EditarPerfilService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EditarUsuarioMapper editarUsuarioMapper;

    @Mock
    private UsuarioResponseMapper usuarioResponseMapper;

    @Mock
    private FileSaverService fileSaverService;

    @Test
    public void deveEditarPerfilComNomeEFoto(){

        String novoNome = "Nome";
        String urlImagem = "url";
        Usuario usuarioAEditar = new Usuario();
        Usuario usuarioEditado = new Usuario();
        UsuarioResponse response = new UsuarioResponse();
        EditarUsuarioRequest request = new EditarUsuarioRequest();
        response.setNome(novoNome);
        response.setFoto(urlImagem);

        MockMultipartFile imagem = new MockMultipartFile("text", new byte[200]);

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuarioAEditar);
        Mockito.when(fileSaverService.write(imagem, usuarioAEditar.hashCode())).thenReturn(urlImagem);
        Mockito.when(editarUsuarioMapper.apply(usuarioAEditar, request.getNome(), urlImagem)).thenReturn(usuarioEditado);
        Mockito.when(usuarioResponseMapper.convert(usuarioEditado)).thenReturn(response);

        UsuarioResponse result = tested.doIt(request, imagem);

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(fileSaverService).write(imagem, usuarioAEditar.hashCode());
        Mockito.verify(editarUsuarioMapper).apply(usuarioAEditar, request.getNome(), urlImagem);
        Mockito.verify(usuarioRepository).save(usuarioEditado);
        Mockito.verify(usuarioResponseMapper).convert(usuarioEditado);

        Assert.assertEquals(response, result);
        Assert.assertEquals(novoNome, result.getNome());
        Assert.assertEquals(urlImagem, result.getFoto());
    }

    @Test
    public void deveEditarPerfilComNome(){

        String novoNome = "Nome";
        Usuario usuarioAEditar = new Usuario();
        Usuario usuarioEditado = new Usuario();
        UsuarioResponse response = new UsuarioResponse();
        EditarUsuarioRequest request = new EditarUsuarioRequest();
        response.setNome(novoNome);
        request.setNome("novoNome");
        MockMultipartFile imagem = new MockMultipartFile("text", new byte[0]);

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuarioAEditar);
        Mockito.when(fileSaverService.write(imagem, usuarioAEditar.hashCode())).thenReturn(null);
        Mockito.when(editarUsuarioMapper.apply(usuarioAEditar, request.getNome())).thenReturn(usuarioEditado);
        Mockito.when(usuarioResponseMapper.convert(usuarioEditado)).thenReturn(response);

        UsuarioResponse result = tested.doIt(request, imagem);

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(fileSaverService).write(imagem, usuarioAEditar.hashCode());
        Mockito.verify(editarUsuarioMapper).apply(usuarioAEditar, request.getNome());
        Mockito.verify(usuarioRepository).save(usuarioEditado);
        Mockito.verify(usuarioResponseMapper).convert(usuarioEditado);

        Assert.assertEquals(response, result);
        Assert.assertEquals(novoNome, result.getNome());
    }

}