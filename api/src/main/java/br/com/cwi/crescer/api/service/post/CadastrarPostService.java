package br.com.cwi.crescer.api.service.post;

import br.com.cwi.crescer.api.controller.request.PostRequest;
import br.com.cwi.crescer.api.domain.Categoria;
import br.com.cwi.crescer.api.domain.Post;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.post.PostMapper;
import br.com.cwi.crescer.api.repository.CategoriaRepository;
import br.com.cwi.crescer.api.repository.PostRepository;
import br.com.cwi.crescer.api.service.amazon.FileSaverService;
import br.com.cwi.crescer.api.service.seguranca.IdentificarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CadastrarPostService {

    @Autowired
    private IdentificarUsuarioService identificarUsuarioService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FileSaverService fileSaverService;

    @Autowired
    private PostMapper postMapper;

    public Integer cadastrar(PostRequest request, MultipartFile[] imagens, MultipartFile imagemCapa) {

        Usuario usuario = identificarUsuarioService.doIt();

        List<Categoria> categorias = categoriaRepository.findAllByIdCategoriaIn(request.getIdTags());

        Post post = postMapper.convert(request, usuario, categorias);

        salvarImagens(post, imagens, imagemCapa);

        return postRepository.save(post).getIdPost();

    }

    private void salvarImagens(Post post, MultipartFile[] imagens, MultipartFile imagemCapa){

        post.setImagemCapa(fileSaverService.write(imagemCapa, post.hashCode()));

        for (int i = 0; i < post.getPassos().size(); i++){
            String url = fileSaverService.write(imagens[i], post.getPassos().get(i).hashCode());
            post.getPassos().get(i).setFoto(url);
        }

    }
}