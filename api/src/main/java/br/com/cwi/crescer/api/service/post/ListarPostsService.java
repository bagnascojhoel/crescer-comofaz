package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.mapper.lista.ListarPostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPostsService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ListarPostResponseMapper listarPostResponseMapper;

    public List<ListarPostResponse> doIt(){

        List<Post> posts = postRepository.findAll();

        return listarPostResponseMapper.convert(posts);

    }
}
