package br.com.cwi.crescer.api.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaSimplificadaResponse {
    private Integer idLista;
    private String assunto;
    private boolean postAdicionado;
}