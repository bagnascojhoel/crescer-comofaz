package br.com.cwi.crescer.api.controller.privado;

import br.com.cwi.crescer.api.controller.request.FiltroRequest;
import br.com.cwi.crescer.api.controller.request.PostRequest;
import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.controller.response.PostResponse;
import br.com.cwi.crescer.api.service.favorito.AtualizarFavoritoService;
import br.com.cwi.crescer.api.service.feito.AtualizarFeitoService;
import br.com.cwi.crescer.api.service.post.*;
import br.com.cwi.crescer.api.service.usuario.ListarSugestoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/privado/post")
public class PostController {

    @Autowired
    private BuscarPostService buscarPostService;

    @Autowired
    private ListarSugestoesService listarSugestoesService;

    @Autowired
    private ListarSugestoesPorPostService listarSugestoesPorPostService;

    @Autowired
    private CadastrarPostService cadastrarPostService;

    @Autowired
    private AtualizarFavoritoService atualizarFavoritoService;

    @Autowired
    private AtualizarFeitoService atualizarFeitoService;

    @Autowired
    private FiltrarPostsService filtrarPostsService;

    @Autowired
    private FiltrarPostsCategoriaService filtrarPostsCategoriaService;

    @Autowired
    private EditarPostService editarPostService;

    @GetMapping("/{idPost}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse exibirPostPorId(@PathVariable Integer idPost) {
        return buscarPostService.buscar(idPost);
    }

    @GetMapping("/sugestoes")
    public List<ListarPostResponse> listarSugestoesPorUsuario(){
        return listarSugestoesService.listar();
    }

    @GetMapping("/{idPost}/sugestoes")
    @ResponseStatus(HttpStatus.OK)
    public List<ListarPostResponse> listarSugestoesPorPost(@PathVariable Integer idPost) {
        return listarSugestoesPorPostService.doIt(idPost);
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer postar(@RequestPart @Valid PostRequest request, @RequestPart MultipartFile[] imagens, @RequestPart MultipartFile imagem){
        return cadastrarPostService.cadastrar(request, imagens, imagem);
    }

    @PutMapping("/favorito/{idPost}")
    @ResponseStatus(HttpStatus.OK)
    public boolean atualizarFavorito(@PathVariable Integer idPost){
        return atualizarFavoritoService.adicionar(idPost);
    }

    @PutMapping("/feito/{idPost}")
    @ResponseStatus(HttpStatus.OK)
    public boolean atualizarFeito(@PathVariable Integer idPost){
        return atualizarFeitoService.adicionar(idPost);
    }

    @PostMapping("/filtro")
    @ResponseStatus(HttpStatus.OK)
    public List<ListarPostResponse> filtrar(@RequestBody FiltroRequest request){
        return filtrarPostsService.filtrar(request);
    }

    @PostMapping("/filtro/categoria")
    @ResponseStatus(HttpStatus.OK)
    public List<ListarPostResponse> filtrarCategoria(@RequestParam List<Integer> idCategorias){
        return filtrarPostsCategoriaService.filtrar(idCategorias);
    }

    @PutMapping("/{idPost}/editar")
    public Integer editarPost(@PathVariable Integer idPost, @RequestPart PostRequest request, @RequestPart MultipartFile[] imagens, @RequestPart MultipartFile imagem){
        return editarPostService.doIt(idPost, request, imagens, imagem);
    }

}
