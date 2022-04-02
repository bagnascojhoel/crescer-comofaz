package br.com.cwi.crescer.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ListaRequest {

    @NotEmpty(message="Por favor, insira um assunto.")
    private String assunto;

    private Boolean isPrivado;
}