package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.lista.ListarPostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarSugestoesService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ListarPostResponseMapper listarPostResponseMapper;

    public List<ListarPostResponse> listar() {

        Usuario usuario = identificarUsuarioService.doIt();

        List<Post> posts = postRepository.findDistinctByTagsInOrderByIdPostDesc(usuario.getInteresses());

        List<Post> favoritosUsuario = usuario.getFavoritos();

        return listarPostResponseMapper.convert(posts, favoritosUsuario);
    }
}