package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.response.UsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.lista.ListarPostResponseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListarPostResponseMapper listarPostResponseMapper;

    public UsuarioResponse convert(Usuario usuario, Integer totalContribuicoes) {
        UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);
        response.setTotalContribuicoes(totalContribuicoes);
        response.setFavoritos(listarPostResponseMapper.convert(usuario.getPosts(), usuario.getFavoritos()));
        response.setFeitos(listarPostResponseMapper.convert(usuario.getPosts(), usuario.getFavoritos()));
        return response;
    }

    public UsuarioResponse convert(Usuario usuario) {
        UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);
        response.setFavoritos(listarPostResponseMapper.convert(usuario.getPosts(), usuario.getFavoritos()));
        return response;
    }
}