package br.com.cwi.crescer.api.controller.privado;

import br.com.cwi.crescer.api.controller.request.EditarUsuarioRequest;
import br.com.cwi.crescer.api.controller.response.*;
import br.com.cwi.crescer.api.service.favorito.ExibirFavoritosService;
import br.com.cwi.crescer.api.service.feito.ExibirFeitosService;
import br.com.cwi.crescer.api.service.seguranca.AutenticacaoService;
import br.com.cwi.crescer.api.service.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/privado/usuario")
public class UsuarioController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private AtualizarInteressesService atualizarInteressesService;

    @Autowired
    private ExibirPerfilService exibirPerfilService;

    @Autowired
    private ExibirFavoritosService exibirFavoritosService;

    @Autowired
    private ExibirFeitosService exibirFeitosService;

    @Autowired
    private EditarPerfilService editarPerfilService;

    @Autowired
    private BuscarListasPorUsuarioService buscarListasPorUsuarioService;

    @Autowired
    private BuscarListasSimplificadasService buscarListasSimplificadasService;

    @Autowired
    private ExibirInformacoesBasicasUsuarioService exibirInformacoesBasicasUsuarioService;

    @PostMapping("/autenticacao")
    @ResponseStatus(HttpStatus.CREATED)
    public AutenticarUsuarioResponse autenticarUsuario() {
        return autenticacaoService.autenticar();
    }

    @PutMapping("/cadastrar-interesses")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarInteresses(@RequestParam List<Integer> idInteresses){
        atualizarInteressesService.doIt(idInteresses);
    }

    @GetMapping("/perfil")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse exibirMeuPerfil(){
        return exibirPerfilService.exibir();
    }

    @GetMapping("/perfil/favoritos")
    @ResponseStatus(HttpStatus.OK)
    public List<ListarPostResponse> exibirFavoritos(){
        return exibirFavoritosService.exibir();
    }

    @GetMapping("/perfil/feitos")
    @ResponseStatus(HttpStatus.OK)
    public List<ListarPostResponse> exibirFeitos(){
        return exibirFeitosService.exibir();
    }

    @PutMapping("/perfil/editar")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse editarPerfil(@RequestPart EditarUsuarioRequest request, @RequestPart MultipartFile imagem){
        return editarPerfilService.doIt(request, imagem);
    }

    @GetMapping("/perfil/listas")
    @ResponseStatus(HttpStatus.OK)
    public List<ListaHeaderResponse> exibirListas(){
        return buscarListasPorUsuarioService.buscar();
    }

    @GetMapping("/perfil/listas/{idPost}")
    @ResponseStatus(HttpStatus.OK)
    public List<ListaSimplificadaResponse> exibirListasSimplificadas(@PathVariable Integer idPost){
        return buscarListasSimplificadasService.buscar(idPost);
    }

    @GetMapping("/perfil/basico")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioBasicoResponse exibirInformacoesBasicas(){
        return exibirInformacoesBasicasUsuarioService.doIt();
    }

}