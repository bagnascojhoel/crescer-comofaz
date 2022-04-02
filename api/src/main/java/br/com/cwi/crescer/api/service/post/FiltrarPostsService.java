package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.request.FiltroRequest;
import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.lista.ListarPostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import br.com.cwi.crescer.api.utils.PostSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiltrarPostsService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private ListarPostResponseMapper listarPostResponseMapper;

    public List<ListarPostResponse> filtrar(FiltroRequest request) {

        List<Post> response = postRepository.findAll(PostSpecifications.withDynamicQuery(request.getTitulo(), request.getDificuldade()));

        Usuario usuario = identificarUsuarioService.doIt();

        List<Post> favoritosUsuario = usuario.getFavoritos();

        return listarPostResponseMapper.convert(response, favoritosUsuario);
    }
}