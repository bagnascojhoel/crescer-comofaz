package br.com.cwi.crescer.api.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaResponse {
    private Integer usuarioIdUsuario;
    private String usuarioNome;
    private String usuarioFoto;
    private String texto;
}