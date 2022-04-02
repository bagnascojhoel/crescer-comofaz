package br.com.cwi.crescer.api.validator.security;

import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UsuarioNaoAutorizadoValidator {
    public void check(String expectedId, String actualId) {
        if (!expectedId.equals(actualId))
            throw new NaoAutorizadoException("Usuário não autorizado.");
    }

    public void check(HttpStatus status) {
        if (!status.equals(HttpStatus.OK))
            throw new NaoAutorizadoException("Usuário não autorizado.");
    }
}
