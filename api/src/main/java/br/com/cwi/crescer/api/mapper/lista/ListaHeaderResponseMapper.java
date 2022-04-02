package br.com.cwi.crescer.api.mapper.lista;


import br.com.cwi.crescer.api.controller.response.ListaHeaderResponse;
import br.com.cwi.crescer.api.domain.Lista;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaHeaderResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ListaHeaderResponse convert(Lista lista) {
        return modelMapper.map(lista, ListaHeaderResponse.class);
    }

    public List<ListaHeaderResponse> convert(List<Lista> listas) {
        return listas.stream()
                .map(lista -> modelMapper.map(lista, ListaHeaderResponse.class))
                .collect(Collectors.toList());
    }

}
