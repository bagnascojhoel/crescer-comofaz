package br.com.cwi.crescer.api.exception;

import org.springframework.http.HttpStatus;

public class NaoEncontradoException extends ErroAbstratoException {

    public NaoEncontradoException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}