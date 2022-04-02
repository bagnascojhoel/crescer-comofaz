package br.com.cwi.crescer.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PassoRequest {

    @NotEmpty(message = "Por favor, insira um texto.")
    private String texto;
}