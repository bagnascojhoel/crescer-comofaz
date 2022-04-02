package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.response.AutenticarUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutenticarUsuarioResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AutenticarUsuarioResponse convert (Usuario usuario){
        return modelMapper.map(usuario, AutenticarUsuarioResponse.class);
    }

}
