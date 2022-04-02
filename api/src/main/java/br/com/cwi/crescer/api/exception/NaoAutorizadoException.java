package br.com.cwi.crescer.api.exception;

import org.springframework.http.HttpStatus;

public class NaoAutorizadoException extends ErroAbstratoException {
    public NaoAutorizadoException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
