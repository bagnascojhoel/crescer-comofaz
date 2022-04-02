package br.com.cwi.crescer.api.controller.publico;


import br.com.cwi.crescer.api.controller.response.UsuarioPublicoResponse;
import br.com.cwi.crescer.api.service.usuario.ExibirPerfilPublicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publico/usuario")
public class UsuarioPublicoController {

    @Autowired
    private ExibirPerfilPublicoService exibirPerfilPublicoService;

    @GetMapping("/perfil/{idUsuario}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioPublicoResponse exibirPerfil(@PathVariable Integer idUsuario){
        return exibirPerfilPublicoService.doIt(idUsuario);
    }

}
