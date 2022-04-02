package br.com.cwi.crescer.api.controller.request;

import br.com.cwi.crescer.api.domain.Categoria;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class UsuarioRequest {

    @NotEmpty(message = "O campo nome é obrigatório.")
    private String nome;

    private String email;

    private String foto;

    private List<Categoria> interesses;

}