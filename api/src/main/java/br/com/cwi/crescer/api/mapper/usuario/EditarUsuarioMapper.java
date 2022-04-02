package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.domain.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EditarUsuarioMapper {

    public Usuario apply(Usuario usuarioAEditar, String novoNome, String urlNovaFoto){
        usuarioAEditar.setNome(novoNome);
        usuarioAEditar.setFoto(urlNovaFoto);
        return usuarioAEditar;
    }

    public Usuario apply(Usuario usuarioAEditar, String novoNome){
        usuarioAEditar.setNome(novoNome);
        return usuarioAEditar;
    }
}
