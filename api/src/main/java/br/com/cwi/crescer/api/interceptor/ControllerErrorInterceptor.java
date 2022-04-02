package br.com.cwi.crescer.api.interceptor;

import br.com.cwi.crescer.api.controller.response.ErrorResponse;
import br.com.cwi.crescer.api.exception.ErroAbstratoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@Slf4j
public class ControllerErrorInterceptor {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Ocorreu um erro", exception);

        ErrorResponse response = ErrorResponse.builder()
                .error("Ocorreu um erro inesperado")
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ResponseStatusException exception) {
        ErrorResponse response = ErrorResponse.builder()
                .error(exception.getMessage())
                .build();

        return new ResponseEntity<>(response, exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException exception) {
        ErrorResponse response = ErrorResponse.builder()
                .error("JSON incorreto: " + exception.getMostSpecificCause().getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ErroAbstratoException exception) {
        ErrorResponse response = ErrorResponse.builder()
                .error(exception.getMessage())
                .build();

        return new ResponseEntity<>(response, exception.getStatus());
    }
}