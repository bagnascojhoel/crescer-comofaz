package br.com.cwi.crescer.api.controller.privado;

import br.com.cwi.crescer.api.controller.request.ListaRequest;
import br.com.cwi.crescer.api.controller.response.ListaHeaderResponse;
import br.com.cwi.crescer.api.controller.response.ListaResponse;
import br.com.cwi.crescer.api.service.lista.AtualizarPostListaService;
import br.com.cwi.crescer.api.service.lista.BuscarListaService;
import br.com.cwi.crescer.api.service.lista.CadastrarListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/privado/lista")
public class ListaController {

    @Autowired
    private CadastrarListaService cadastrarListaService;

    @Autowired
    private AtualizarPostListaService atualizarPostListaService;

    @Autowired
    private BuscarListaService buscarListaService;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ListaHeaderResponse cadastrarLista(@RequestBody ListaRequest request) {
        return cadastrarListaService.cadastrar(request);
    }

    @PostMapping("/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean atualizarPostLista(@RequestParam Integer idLista, @RequestParam Integer idPost){
        return atualizarPostListaService.atualizar(idLista, idPost);
    }

    @GetMapping("/{idLista}")
    @ResponseStatus(HttpStatus.OK)
    public ListaResponse buscarLista(@PathVariable Integer idLista) {
        return buscarListaService.buscar(idLista);
    }
}