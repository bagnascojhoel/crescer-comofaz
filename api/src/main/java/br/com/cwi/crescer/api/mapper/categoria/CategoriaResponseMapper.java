package br.com.cwi.crescer.api.mapper.categoria;

import br.com.cwi.crescer.api.controller.response.CategoriaResponse;
import br.com.cwi.crescer.api.domain.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaResponse convert(Categoria categoria, boolean isAdicionada) {
        CategoriaResponse response = modelMapper.map(categoria, CategoriaResponse.class);
        response.setAdicionada(isAdicionada);
        return response;
    }

    public CategoriaResponse convert(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaResponse.class);
    }
}