package br.com.cwi.crescer.api.service.favorito;

import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.repository.UsuarioRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtualizarFavoritoService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostRepository postRepository;

    public boolean adicionar(Integer idPost){

        Usuario usuario = identificarUsuarioService.doIt();

        Optional<Post> post = postRepository.findById(idPost);

        if(!post.isPresent())
            throw new NaoEncontradoException("Post não encontrado.");

        boolean favoritoExiste = usuario.getFavoritos().contains(post.get());

        if (favoritoExiste)
            usuario.getFavoritos().remove(post.get());

        else
            usuario.getFavoritos().add(post.get());

        usuarioRepository.save(usuario);

        return !favoritoExiste;
    }
}