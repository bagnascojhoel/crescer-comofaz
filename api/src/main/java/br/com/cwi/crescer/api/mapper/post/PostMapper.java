package br.com.cwi.crescer.api.mapper.post;

import br.com.cwi.crescer.api.controller.request.PostRequest;
import br.com.cwi.crescer.api.domain.Categoria;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.passo.PassoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    @Autowired
    private PassoMapper passoMapper;

    public Post convert(PostRequest request, Usuario usuario, List<Categoria> categorias) {
        Post post = new Post();
        post.setTitulo(request.getTitulo());
        post.setDescricao(request.getDescricao());
        post.setDuracaoMinutos(request.getDuracaoMinutos());
        post.setDificuldade(request.getDificuldade());
        post.setTags(categorias);
        post.setUsuario(usuario);
        post.setMateriais(request.getMateriais());
        post.setDica(request.getDica());
        post.setPassos(
                request.getPassos()
                       .stream()
                       .map(passo -> passoMapper.convert(passo, post))
                       .collect(Collectors.toList())
        );

        return post;
    }

    public Post convert(PostRequest request, Usuario usuario, List<Categoria> categorias, Post postAntigo) {

        postAntigo.setIdPost(postAntigo.getIdPost());
        postAntigo.setTitulo(request.getTitulo());
        postAntigo.setDescricao(request.getDescricao());
        postAntigo.setDuracaoMinutos(request.getDuracaoMinutos());
        postAntigo.setDificuldade(request.getDificuldade());
        postAntigo.setTags(categorias);
        postAntigo.setUsuario(usuario);
        postAntigo.setMateriais(request.getMateriais());
        postAntigo.setDica(request.getDica());

        postAntigo.setPassos(
                request.getPassos()
                        .stream()
                        .map(passo -> passoMapper.convert(passo, postAntigo))
                        .collect(Collectors.toList())
        );

        return postAntigo;

    }
}