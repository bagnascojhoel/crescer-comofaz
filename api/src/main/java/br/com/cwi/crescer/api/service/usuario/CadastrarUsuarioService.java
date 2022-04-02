package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.AutenticarUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.AutenticarUsuarioResponseMapper;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AutenticarUsuarioResponseMapper autenticarUsuarioResponseMapper;

    public AutenticarUsuarioResponse cadastrar(Usuario usuario){

        Usuario novoUsuario = usuarioRepository.save(usuario);

        AutenticarUsuarioResponse response = autenticarUsuarioResponseMapper.convert(novoUsuario);

        response.setPrimeiroAcesso(true);

        return response;

    }

}
