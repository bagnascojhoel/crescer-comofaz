package br.com.cwi.crescer.api.service.categoria;

import br.com.cwi.crescer.api.controller.response.CategoriaResponse;
import br.com.cwi.crescer.api.mapper.categoria.CategoriaResponseMapper;
import br.com.cwi.crescer.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarCategoriasPublicoService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaResponseMapper categoriaResponseMapper;

    public List<CategoriaResponse> listar() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaResponseMapper::convert)
                .collect(Collectors.toList());
    }
}