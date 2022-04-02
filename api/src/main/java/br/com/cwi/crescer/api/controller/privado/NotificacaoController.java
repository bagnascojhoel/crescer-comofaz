package br.com.cwi.crescer.api.controller.privado;

import br.com.cwi.crescer.api.controller.response.NotificacaoResponse;
import br.com.cwi.crescer.api.service.notificacao.AtualizarNotificacaoService;
import br.com.cwi.crescer.api.service.notificacao.BuscarNotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/privado/notificacao")
public class NotificacaoController {

    @Autowired
    private BuscarNotificacaoService buscarNotificacaoService;

    @Autowired
    private AtualizarNotificacaoService atualizarNotificacaoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NotificacaoResponse> buscarNotificacoes() {
        return buscarNotificacaoService.doIt();
    }

    @PutMapping("{idNotificacao}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarNotificacao(@PathVariable Integer idNotificacao) {
        atualizarNotificacaoService.doIt(idNotificacao);
    }
}