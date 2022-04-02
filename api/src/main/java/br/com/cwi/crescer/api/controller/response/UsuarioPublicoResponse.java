package br.com.cwi.crescer.api.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioPublicoResponse {

    private Integer idUsuario;

    private String nome;

    private String foto;

    private List<ListarPostResponse> posts;

    private List<ListaResponse> listas;

    private List<CategoriaResponse> interesses;

    private Integer totalContribuicoes;

}
