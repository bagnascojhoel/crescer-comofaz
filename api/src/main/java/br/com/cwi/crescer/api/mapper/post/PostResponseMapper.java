package br.com.cwi.crescer.api.mapper.post;

import br.com.cwi.crescer.api.controller.response.PostResponse;
import br.com.cwi.crescer.api.domain.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PostResponse convert(Post post, boolean isFavorito, boolean isFeito) {
        PostResponse response = modelMapper.map(post, PostResponse.class);
        response.setFavorito(isFavorito);
        response.setFeito(isFeito);
        return response;
    }

    public PostResponse convert(Post post) {
        return modelMapper.map(post, PostResponse.class);
    }

}