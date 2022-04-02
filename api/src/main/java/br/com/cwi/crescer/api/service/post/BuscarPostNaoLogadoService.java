package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.PostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.post.PostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class BuscarPostNaoLogadoService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostResponseMapper postResponseMapper;

    public PostResponse buscar(Integer idPost) {
        Optional<Post> post = postRepository.findById(idPost);

        if (!post.isPresent())
            throw new NaoEncontradoException("Post não encontrado.");

        Collections.sort(post.get().getComentarios());

        return postResponseMapper.convert(post.get());
    }
}