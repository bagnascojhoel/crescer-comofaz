package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.UsuarioBasicoResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.UsuarioBasicoResponseMapper;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExibirInformacoesBasicasUsuarioService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private UsuarioBasicoResponseMapper usuarioBasicoResponseMapper;

    public UsuarioBasicoResponse doIt() {

        Usuario usuario = identificarUsuarioService.doIt();

        return usuarioBasicoResponseMapper.apply(usuario);
    }
}
