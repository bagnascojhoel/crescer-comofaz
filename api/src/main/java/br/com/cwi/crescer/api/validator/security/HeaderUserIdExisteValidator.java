package br.com.cwi.crescer.api.validator.security;

import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;
import org.springframework.stereotype.Component;

@Component
public class HeaderUserIdExisteValidator {
    public void check(String userId) {
        if (userId == null || userId.isEmpty())
            throw new ValidacaoNegocioException("Um request privado precisa do id do usuário no header 'X-User-Id' para ser autenticado.");

    }

}
