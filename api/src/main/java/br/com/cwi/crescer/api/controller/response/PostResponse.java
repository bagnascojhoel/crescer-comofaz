package br.com.cwi.crescer.api.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostResponse {
    private String usuarioNome;
    private String usuarioFoto;
    private Integer usuarioIdUsuario;
    private String titulo;
    private String descricao;
    private List<String> materiais;
    private Integer duracaoMinutos;
    private List<PassoResponse> passos;
    private List<ComentarioResponse> comentarios;
    private Integer dificuldade;
    private List<CategoriaResponse> tags;
    private String imagemCapa;
    private String dica;
    private boolean isFavorito;
    private boolean isFeito;
}