package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.request.PostRequest;
import br.com.cwi.crescer.api.domain.Categoria;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.mapper.passo.PassoMapper;
import br.com.cwi.crescer.api.mapper.post.PostMapper;
import br.com.cwi.crescer.api.repository.CategoriaRepository;
import br.com.cwi.crescer.api.repository.PassoRepository;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.amazon.FileSaverService;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EditarPostService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PassoMapper passoMapper;

    @Autowired
    private PassoRepository passoRepository;

    @Autowired
    private FileSaverService fileSaverService;

    @Transactional
    public Integer doIt(Integer idPost, PostRequest request, MultipartFile[] imagens, MultipartFile imagemCapa) {

        Usuario usuario = identificarUsuarioService.doIt();

        List<Categoria> categorias = categoriaRepository.findAllByIdCategoriaIn(request.getIdTags());

        Optional<Post> postAEditar = postRepository.findById(idPost);

        if (!postAEditar.isPresent())
            throw new NaoEncontradoException("Post não encontrado.");

        if (!usuario.getPosts().contains(postAEditar.get()))
            throw new NaoAutorizadoException("O post que você está tentando editar não pertence a você.");

        passoRepository.deleteAllByPost(postAEditar.get());

        Post post = postMapper.convert(request, usuario, categorias, postAEditar.get());

        salvarImagens(post, imagens, imagemCapa);

        return postRepository.save(post).getIdPost();
    }

    private void salvarImagens(Post post, MultipartFile[] imagens, MultipartFile imagemCapa) {

        String urlImagemCapa = fileSaverService.write(imagemCapa, post.hashCode());
        if (!Objects.isNull(urlImagemCapa))
            post.setImagemCapa(urlImagemCapa);

        for (int i = 0; i < post.getPassos().size(); i++){
            String urlImagemPasso = fileSaverService.write(imagens[i], post.getPassos().get(i).hashCode());
            if (!Objects.isNull(urlImagemPasso))
                post.getPassos().get(i).setFoto(urlImagemPasso);
        }

    }

}
