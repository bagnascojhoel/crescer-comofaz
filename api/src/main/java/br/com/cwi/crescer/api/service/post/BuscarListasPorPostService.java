package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.ListaResponse;
import br.com.cwi.crescer.api.domain.Lista;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.lista.ListaResponseMapper;
import br.com.cwi.crescer.api.repository.ListaRepository;
import br.com.cwi.crescer.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuscarListasPorPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ListaResponseMapper listaResponseMapper;

    public List<ListaResponse> doIt(Integer idPost){

        Optional<Post> post = postRepository.findById(idPost);

        if (!post.isPresent())
            throw new NaoEncontradoException("Post não encontrado.");

        List<Lista> listas = listaRepository.findDistinctByPostsTagsInAndIsPrivadoFalseOrderByIdListaDesc(post.get().getTags());

        return listaResponseMapper.convert(listas);
    }
}
