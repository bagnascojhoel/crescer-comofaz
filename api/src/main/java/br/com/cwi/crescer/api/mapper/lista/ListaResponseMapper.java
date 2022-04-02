package br.com.cwi.crescer.api.mapper.lista;

import br.com.cwi.crescer.api.controller.response.ListaResponse;
import br.com.cwi.crescer.api.domain.Lista;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public List<ListaResponse> convert(List<Lista> listas) {
        return listas.stream()
                     .map(lista -> modelMapper.map(lista, ListaResponse.class))
                     .collect(Collectors.toList());
    }

    public ListaResponse convert(Lista lista){
        return modelMapper.map(lista, ListaResponse.class);
    }
}