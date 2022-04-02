package br.com.cwi.crescer.api.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutenticarUsuarioResponse {

    private Integer idUsuario;

    private boolean isPrimeiroAcesso;

}
