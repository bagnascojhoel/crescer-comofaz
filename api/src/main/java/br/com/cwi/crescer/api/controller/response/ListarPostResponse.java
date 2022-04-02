package br.com.cwi.crescer.api.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarPostResponse {
    private Integer idPost;
    private String usuarioNome;
    private String titulo;
    private Integer duracaoMinutos;
    private Integer dificuldade;
    private String imagemCapa;
    private boolean isFavorito;
    private boolean isFeito;
}