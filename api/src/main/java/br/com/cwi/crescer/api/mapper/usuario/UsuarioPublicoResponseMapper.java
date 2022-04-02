package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.response.UsuarioPublicoResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPublicoResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioPublicoResponse apply(Usuario usuario, Integer totalContribuicoes) {
        UsuarioPublicoResponse response = modelMapper.map(usuario, UsuarioPublicoResponse.class);
        response.setTotalContribuicoes(totalContribuicoes);

        return response;
    }

}
