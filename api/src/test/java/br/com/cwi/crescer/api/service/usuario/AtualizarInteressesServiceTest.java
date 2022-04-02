package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.domain.Categoria;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.CategoriaRepository;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
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
public class AtualizarInteressesServiceTest {

    @InjectMocks
    private AtualizarInteressesService tested;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void deveCadastrarCorretamente(){

        List<Integer> idInteresses = new ArrayList<>();

        Usuario usuario = new Usuario();

        List<Categoria> novosInteresses = new ArrayList<>();

        Categoria categoria = new Categoria();

        novosInteresses.add(categoria);

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);

        Mockito.when(categoriaRepository.findAllByIdCategoriaIn(idInteresses)).thenReturn(novosInteresses);

        tested.doIt(idInteresses);

        Mockito.verify(identificarUsuarioService).doIt();

        Mockito.verify(categoriaRepository).findAllByIdCategoriaIn(idInteresses);

        Mockito.verify(usuarioRepository).save(usuario);

        Assert.assertEquals(novosInteresses, usuario.getInteresses());

    }

}