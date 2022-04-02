package br.com.cwi.crescer.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class PostRequest {

    @NotEmpty(message = "Por favor, insira um titulo.")
    private String titulo;

    @NotEmpty(message = "Por favor, insira uma descrição.")
    private String descricao;

    private String dica;

    @NotNull(message = "Por favor, insira os materiais.")
    private List<String> materiais;

    @NotNull(message = "Por favor, insira a duração.")
    private Integer duracaoMinutos;

    @NotNull(message = "Por favor, insira os passos.")
    private List<PassoRequest> passos;

    @NotNull(message = "Por favor, insira a dificuldade.")
    private Integer dificuldade;

    @NotNull(message = "Por favor, insira as categorias.")
    private List<Integer> idTags;
}