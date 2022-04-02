package br.com.cwi.crescer.api.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltroRequest {

    private String titulo;

    private Integer dificuldade = 0;
}