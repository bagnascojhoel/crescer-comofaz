package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.lista.ListarPostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListarSugestoesPorPostPublicoService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ListarPostResponseMapper listarPostResponseMapper;

    public List<ListarPostResponse> doIt(Integer idPost){

        Optional<Post> post = postRepository.findById(idPost);

        if (!post.isPresent())
            throw new NaoEncontradoException("Post não encontrado.");

        List<Post> posts = postRepository.findDistinctByTagsInOrderByIdPostDesc(post.get().getTags());

        posts.remove(post.get());

        return listarPostResponseMapper.convert(posts);
    }

}
