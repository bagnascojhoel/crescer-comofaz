package br.com.cwi.crescer.api.controller.publico;

import br.com.cwi.crescer.api.controller.response.ListaResponse;
import br.com.cwi.crescer.api.service.lista.BuscarListaPublicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publico/lista")
public class ListaPublicoController {

    @Autowired
    private BuscarListaPublicoService buscarListaPublicoService;

    @GetMapping("/{idLista}")
    @ResponseStatus(HttpStatus.OK)
    public ListaResponse buscarLista(@PathVariable Integer idLista) {
        return buscarListaPublicoService.doIt(idLista);
    }
}