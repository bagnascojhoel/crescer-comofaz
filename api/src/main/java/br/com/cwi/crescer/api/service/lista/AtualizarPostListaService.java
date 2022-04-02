package br.com.cwi.crescer.api.service.lista;

import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.repository.ListaRepository;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtualizarPostListaService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private PostRepository postRepository;

    public Boolean atualizar(Integer idLista, Integer idPost) {

        Optional<Lista> lista = listaRepository.findById(idLista);

        if (!lista.isPresent())
            throw new NaoEncontradoException("Lista não encontrada.");

        Optional<Post> post = postRepository.findById(idPost);

        if (!post.isPresent())
            throw new NaoEncontradoException("Post não encontrado.");

        Usuario usuario = identificarUsuarioService.doIt();

        isUsuarioProprietariodaLista(usuario, lista.get());

        boolean postExiste = lista.get().getPosts().contains(post.get());

        if (postExiste)
            lista.get().getPosts().remove(post.get());

        else
            lista.get().getPosts().add(post.get());

        listaRepository.save(lista.get());

        return !postExiste;
    }

    private void isUsuarioProprietariodaLista(Usuario usuario, Lista lista) {
        if (!lista.getUsuario().equals(usuario))
            throw new NaoAutorizadoException("A lista que você está tentando editar não é sua.");
    }

}