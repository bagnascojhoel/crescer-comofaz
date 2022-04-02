package br.com.cwi.crescer.api.service.feito;

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
public class AtualizarFeitoService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean adicionar(Integer idPost) {

        Usuario usuario = identificarUsuarioService.doIt();

        Optional<Post> post = postRepository.findById(idPost);

        if (!post.isPresent())
            throw new NaoEncontradoException("Post não encontrado.");


        boolean isFeito = usuario.getFeitos().contains(post.get());

        if (isFeito) {
            usuario.getFeitos().remove(post.get());
        } else {

            usuario.getFeitos().add(post.get());
        }

        usuarioRepository.save(usuario);

        return !isFeito;
    }
}