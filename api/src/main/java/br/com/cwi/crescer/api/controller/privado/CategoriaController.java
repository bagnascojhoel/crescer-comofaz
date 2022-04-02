package br.com.cwi.crescer.api.controller.privado;

import br.com.cwi.crescer.api.controller.response.CategoriaResponse;
import br.com.cwi.crescer.api.service.categoria.ListarCategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/privado/categoria")
public class CategoriaController {

    @Autowired
    private ListarCategoriasService listarCategoriasService;

    @GetMapping
    public List<CategoriaResponse> listarCategorias(){
        return listarCategoriasService.listar();
    }
}