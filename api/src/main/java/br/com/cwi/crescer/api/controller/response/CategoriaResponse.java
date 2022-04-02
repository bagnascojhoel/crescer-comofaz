package br.com.cwi.crescer.api.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaResponse {
    private Integer idCategoria;
    private String nome;
    private String foto;
    private boolean isAdicionada;
}