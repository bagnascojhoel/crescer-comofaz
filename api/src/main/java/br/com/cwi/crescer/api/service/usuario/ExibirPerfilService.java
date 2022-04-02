package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.UsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.UsuarioResponseMapper;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ExibirPerfilService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private UsuarioResponseMapper usuarioResponseMapper;

    public UsuarioResponse exibir(){

        Usuario usuario = identificarUsuarioService.doIt();

        Integer totalContribuicoes = usuario.getPosts().size();

        Collections.sort(usuario.getListas());

        return usuarioResponseMapper.convert(usuario, totalContribuicoes);

    }

}