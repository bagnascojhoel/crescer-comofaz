package br.com.cwi.crescer.api.controller.privado;

import br.com.cwi.crescer.api.controller.request.ComentarioRequest;
import br.com.cwi.crescer.api.controller.response.ComentarioResponse;
import br.com.cwi.crescer.api.service.comentario.CadastrarComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/privado/comentario")
public class ComentarioController {

    @Autowired
    private CadastrarComentarioService cadastrarComentarioService;

    @PostMapping("/cadastrar/{idPost}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ComentarioResponse comentar(@PathVariable Integer idPost, @RequestPart @Valid ComentarioRequest request, @RequestPart MultipartFile imagem){
        return cadastrarComentarioService.cadastrar(idPost, request, imagem);
    }

}