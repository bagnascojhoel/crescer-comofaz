package br.com.cwi.crescer.api.controller.privado;

import br.com.cwi.crescer.api.controller.response.RespostaResponse;
import br.com.cwi.crescer.api.service.resposta.CadastrarRespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/privado/resposta")
public class RespostaController {

    @Autowired
    private CadastrarRespostaService cadastrarRespostaService;

    @PostMapping("/cadastrar/{idComentario}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public RespostaResponse responder(@PathVariable Integer idComentario, @RequestParam String texto){
        return cadastrarRespostaService.cadastrar(idComentario, texto);
    }
}