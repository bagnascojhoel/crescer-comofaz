package br.com.cwi.crescer.api.controller.publico;

import br.com.cwi.crescer.api.controller.response.CategoriaResponse;
import br.com.cwi.crescer.api.service.categoria.ListarCategoriasPublicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publico/categoria")
public class CategoriaPublicoController {

    @Autowired
    private ListarCategoriasPublicoService listarCategoriasPublicoService;

    @GetMapping
    public List<CategoriaResponse> listarCategorias(){
        return listarCategoriasPublicoService.listar();
    }
}