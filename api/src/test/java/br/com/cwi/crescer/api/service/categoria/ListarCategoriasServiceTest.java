package br.com.cwi.crescer.api.service.categoria;

import br.com.cwi.crescer.api.controller.response.CategoriaResponse;
import br.com.cwi.crescer.api.domain.Categoria;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.categoria.CategoriaResponseMapper;
import br.com.cwi.crescer.api.repository.CategoriaRepository;
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
public class ListarCategoriasServiceTest {

    @InjectMocks
    private ListarCategoriasService tested;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaResponseMapper categoriaResponseMapper;

    @Mock
    private IdentificarUsuarioService identificarUsuarioService;

    @Test
    public void deveListarComSucesso(){

        Usuario usuario = new Usuario();
        List<Categoria> categorias = new ArrayList<>();
        List<CategoriaResponse> response = new ArrayList<>();
        categorias.add(new Categoria());
        CategoriaResponse categoriaResponse = new CategoriaResponse();

        Mockito.when(identificarUsuarioService.doIt()).thenReturn(usuario);
        Mockito.when(categoriaRepository.findAll()).thenReturn(categorias);
        Mockito.when(categoriaResponseMapper.convert(categorias.get(0), false)).thenReturn(categoriaResponse);

        List<CategoriaResponse> result = tested.listar();

        Mockito.verify(identificarUsuarioService).doIt();
        Mockito.verify(categoriaRepository).findAll();
        Mockito.verify(categoriaResponseMapper).convert(categorias.get(0), false);

        Assert.assertEquals(categoriaResponse, result.get(0));
    }
}