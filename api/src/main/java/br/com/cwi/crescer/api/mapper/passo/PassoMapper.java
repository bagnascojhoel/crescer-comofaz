package br.com.cwi.crescer.api.mapper.passo;

import br.com.cwi.crescer.api.controller.request.PassoRequest;
import br.com.cwi.crescer.api.domain.Passo;
import br.com.cwi.crescer.api.domain.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PassoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Passo convert(PassoRequest request, Post post) {
        Passo passo = modelMapper.map(request, Passo.class);
        passo.setPost(post);
        return passo;
    }
}