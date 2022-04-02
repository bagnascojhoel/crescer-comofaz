package br.com.cwi.crescer.api.controller.request;

import br.com.cwi.crescer.api.domain.enums.Definicao;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ComentarioRequest {

    @NotEmpty(message="Por favor, insira um texto para o comentário.")
    private String texto;

    @NotNull(message="Por favor, selecione uma definição.")
    private Definicao definicao;

}