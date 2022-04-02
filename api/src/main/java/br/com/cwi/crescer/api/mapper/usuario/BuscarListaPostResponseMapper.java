package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.response.ListaSimplificadaResponse;
import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuscarListaPostResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public List<ListaSimplificadaResponse> convert(List<Lista> listas, Post post) {
        return listas.stream()
                     .map(lista -> {
                         boolean isPostAdicionado = lista.getPosts().contains(post);
                         ListaSimplificadaResponse response = modelMapper.map(lista, ListaSimplificadaResponse.class);
                         response.setPostAdicionado(isPostAdicionado);
                         return response;
                     })
                     .collect(Collectors.toList());
    }
}