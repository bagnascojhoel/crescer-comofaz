package br.com.cwi.crescer.api.mapper.comentario;

import br.com.cwi.crescer.api.controller.response.ComentarioResponse;
import br.com.cwi.crescer.api.domain.Comentario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComentarioResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ComentarioResponse convert(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioResponse.class);
    }
}