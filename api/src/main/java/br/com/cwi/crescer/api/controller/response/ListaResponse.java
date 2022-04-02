package br.com.cwi.crescer.api.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListaResponse {
    private Integer idLista;
    private List<ListarPostResponse> posts;
    private String assunto;
    private Boolean isPrivado = false;
}