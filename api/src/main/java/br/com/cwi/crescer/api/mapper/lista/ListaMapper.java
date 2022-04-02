package br.com.cwi.crescer.api.mapper.lista;

import br.com.cwi.crescer.api.controller.request.ListaRequest;
import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Lista convert(ListaRequest request, Usuario usuario) {
        Lista lista = modelMapper.map(request, Lista.class);
        lista.setUsuario(usuario);
        return lista;
    }
}