package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.ListaSimplificadaResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.usuario.BuscarListaPostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuscarListasSimplificadasService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BuscarListaPostResponseMapper buscarListaPostResponseMapper;

    public List<ListaSimplificadaResponse> buscar( Integer idPost) {

        Usuario usuario = identificarUsuarioService.doIt();

        Optional<Post> post = postRepository.findById(idPost);

        if (!post.isPresent())
            throw new NaoEncontradoException("Post não encontrado!");

        return buscarListaPostResponseMapper.convert(usuario.getListas(), post.get());

    }

}