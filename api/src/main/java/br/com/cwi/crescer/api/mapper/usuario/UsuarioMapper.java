package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.request.UsuarioRequest;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.security.FederationAuthentication;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario convert(UsuarioRequest request) {
        return modelMapper.map(request, Usuario.class);
    }

    public Usuario convert(FederationAuthentication authentication) {
        Usuario result = new Usuario();

        result.setEmail(authentication.getEmail());
        result.setNome(authentication.getName());
        result.setFoto(authentication.getPicture());
        result.setProviderId(authentication.getUserId());
        result.setProvider(authentication.getProvider());

        return result;
    }
}