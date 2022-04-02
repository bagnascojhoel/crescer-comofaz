package br.com.cwi.crescer.api.service.favorito;

import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.lista.ListarPostResponseMapper;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExibirFavoritosService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private ListarPostResponseMapper listarPostResponseMapper;

    public List<ListarPostResponse> exibir() {

        Usuario usuario = identificarUsuarioService.doIt();

        return listarPostResponseMapper.convertFavoritos(usuario.getFavoritos());

    }

}