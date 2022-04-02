package br.com.cwi.crescer.api.controller.publico;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("publico/status")
public class StatusController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void verificar() {
        //Método que o frontend chama para garantir que o backend está rodando.
    }
}
