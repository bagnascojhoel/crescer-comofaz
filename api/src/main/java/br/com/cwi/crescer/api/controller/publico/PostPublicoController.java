package br.com.cwi.crescer.api.controller.publico;

import br.com.cwi.crescer.api.controller.request.FiltroRequest;
import br.com.cwi.crescer.api.controller.response.ListaResponse;
import br.com.cwi.crescer.api.controller.response.ListarPostResponse;
import br.com.cwi.crescer.api.controller.response.PostResponse;
import br.com.cwi.crescer.api.service.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publico/post")
public class PostPublicoController {

    @Autowired
    private BuscarPostNaoLogadoService buscarPostNaoLogadoService;

    @Autowired
    private ListarPostsService listarPostsService;

    @Autowired
    private ListarSugestoesPorPostPublicoService listarSugestoesPorPostPublicoService;

    @Autowired
    private FiltrarPostNaoLogadoService filtrarPostNaoLogadoService;

    @Autowired
    private FiltrarPostPorCategoriaNaoLogadoService filtrarPostPorCategoriaNaoLogadoService;

    @Autowired
    private BuscarListasPorPostService buscarListasPorPostService;

    @GetMapping("/{idPost}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse exibirPostPorId(@PathVariable Integer idPost) {
        return buscarPostNaoLogadoService.buscar(idPost);
    }

    @GetMapping("/{idPost}/sugestoes")
    @ResponseStatus(HttpStatus.OK)
    public List<ListarPostResponse> listarSugestoesPorPost(@PathVariable Integer idPost) {
        return listarSugestoesPorPostPublicoService.doIt(idPost);
    }

    @GetMapping("/{idPost}/sugestoes/listas")
    @ResponseStatus(HttpStatus.OK)
    public List<ListaResponse> buscarListasPorPost(@PathVariable Integer idPost) {
        return buscarListasPorPostService.doIt(idPost);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ListarPostResponse> listarPosts(){
        return listarPostsService.doIt();
    }

    @PostMapping("/filtro")
    @ResponseStatus(HttpStatus.OK)
    public List<ListarPostResponse> filtrar(@RequestBody FiltroRequest request){
        return filtrarPostNaoLogadoService.doIt(request);
    }

    @PostMapping("/filtro/categoria")
    @ResponseStatus(HttpStatus.OK)
    public List<ListarPostResponse> filtrarCategoria(@RequestParam List<Integer> idCategorias){
        return filtrarPostPorCategoriaNaoLogadoService.filtrar(idCategorias);
    }

}