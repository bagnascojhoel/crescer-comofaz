package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.PostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.post.PostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class BuscarPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private PostResponseMapper postResponseMapper;

    public PostResponse buscar(Integer idPost) {
        Optional<Post> post = postRepository.findById(idPost);

        Usuario usuario = identificarUsuarioService.doIt();

        if (!post.isPresent())
            throw new NaoEncontradoException("Post não encontrado.");

        boolean isFavorito = usuario.getFavoritos().contains(post.get());

        boolean isFeito = usuario.getFeitos().contains(post.get());

        Collections.sort(post.get().getComentarios());

        return postResponseMapper.convert(post.get(), isFavorito, isFeito);
    }
}