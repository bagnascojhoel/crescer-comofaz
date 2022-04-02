package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.lista.ListarPostResponseMapper;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListarSugestoesPorPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private ListarPostResponseMapper listarPostResponseMapper;

    public List<ListarPostResponse> doIt(Integer idPost){

        Usuario usuario = identificarUsuarioService.doIt();

        Optional<Post> post = postRepository.findById(idPost);

        if (!post.isPresent())
            throw new NaoEncontradoException("Post não encontrado.");

        List<Post> pagePosts = postRepository.findDistinctByTagsInOrderByIdPostDesc(post.get().getTags());

        pagePosts.remove(post.get());
        
        List<Post> posts = pagePosts.stream().filter(post1 -> post1.getUsuario() != usuario).collect(Collectors.toList());

        return listarPostResponseMapper.convert(posts, usuario.getFavoritos());
    }

}
