package br.com.cwi.crescer.api.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioResponse {

    private Integer idUsuario;
    private String nome;
    private String foto;
    private List<ListarPostResponse> posts;
    private List<CategoriaResponse> interesses;
    private List<ListarPostResponse> favoritos;
    private List<ListarPostResponse> feitos;
    private List<ListaResponse> listas;
    private Integer totalContribuicoes;

}