package br.com.cwi.crescer.api.service.seguranca;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.UsuarioMapper;
import br.com.cwi.crescer.api.security.FederationAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class IdentificarEmissorDoRequestService {

    @Autowired
    private UsuarioMapper usuarioMapper;

    public Usuario identificar() {
        FederationAuthentication authenticated = (FederationAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return usuarioMapper.convert(authenticated);
    }

}
