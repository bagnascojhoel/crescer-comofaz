package br.com.cwi.crescer.api.exception;

import org.springframework.http.HttpStatus;

public class ValidacaoNegocioException extends ErroAbstratoException {

    public ValidacaoNegocioException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}