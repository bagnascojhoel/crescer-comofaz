package br.com.cwi.crescer.api.controller.publico;


import br.com.cwi.crescer.api.domain.enums.Definicao;
import br.com.cwi.crescer.api.service.definicao.ListarDefinicoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/publico/definicoes")
public class DefinicoesPublicoController {

    @Autowired
    private ListarDefinicoesService listarDefinicoesService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Definicao> listarDefinicoes() {
        return listarDefinicoesService.listar();
    }

}
