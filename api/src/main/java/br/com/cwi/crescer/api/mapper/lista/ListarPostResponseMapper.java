package br.com.cwi.crescer.api.mapper.lista;

import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListarPostResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public List<ListarPostResponse> convert(List<Post> posts, List<Post> favoritosUsuario) {
        return posts.stream().map(post -> {
            boolean isFavorito = favoritosUsuario.contains(post);
            ListarPostResponse listarPost = modelMapper.map(post, ListarPostResponse.class);
            listarPost.setFavorito(isFavorito);
            return listarPost;
        }).collect(Collectors.toList()
        );
    }

    public List<ListarPostResponse> convert(List<Post> posts) {
        return posts.stream().map(post ->
            modelMapper.map(post, ListarPostResponse.class)
        ).collect(Collectors.toList()
        );
    }

    public List<ListarPostResponse> convertFavoritos(List<Post> favoritosUsuario) {
        return favoritosUsuario.stream().map(favoritoUsuario -> {
            ListarPostResponse listarFeitos = modelMapper.map(favoritoUsuario, ListarPostResponse.class);
            listarFeitos.setFavorito(true);
            return listarFeitos;
        }).collect(Collectors.toList()
        );
    }

    public List<ListarPostResponse> convertFeito(List<Post> feitosUsuario, List<Post> favoritosUsuario) {
        return feitosUsuario.stream().map(feitoUsuario -> {
            boolean isFavorito = favoritosUsuario.contains(feitoUsuario);
            ListarPostResponse listarFeitos = modelMapper.map(feitoUsuario, ListarPostResponse.class);
            listarFeitos.setFavorito(isFavorito);
            return listarFeitos;
        }).collect(Collectors.toList()
        );
    }
}