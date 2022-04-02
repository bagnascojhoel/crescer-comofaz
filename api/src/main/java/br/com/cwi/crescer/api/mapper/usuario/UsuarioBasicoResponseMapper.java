package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.response.UsuarioBasicoResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioBasicoResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioBasicoResponse apply(Usuario usuario){
        return modelMapper.map(usuario, UsuarioBasicoResponse.class);
    }

}
