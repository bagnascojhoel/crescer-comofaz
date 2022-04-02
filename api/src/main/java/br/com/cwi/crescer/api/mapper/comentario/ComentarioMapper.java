package br.com.cwi.crescer.api.mapper.comentario;

import br.com.cwi.crescer.api.controller.request.ComentarioRequest;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    public Comentario convert(ComentarioRequest request, Post post, Usuario usuario) {
        Comentario comentario = new Comentario();
        comentario.setPost(post);
        comentario.setUsuario(usuario);
        comentario.setDefinicao(request.getDefinicao());
        comentario.setTexto(request.getTexto());
        return comentario;
    }
}