package br.com.cwi.crescer.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErroAbstratoException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public ErroAbstratoException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}