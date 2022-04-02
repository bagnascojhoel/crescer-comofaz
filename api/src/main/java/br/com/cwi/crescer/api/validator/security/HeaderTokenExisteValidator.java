package br.com.cwi.crescer.api.validator.security;

import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;
import org.springframework.stereotype.Component;

@Component
public class HeaderTokenExisteValidator {
    public void check(String token) {
        if (token == null || token.isEmpty())
            throw new ValidacaoNegocioException("Não é possível realizar um request privado sem um token.");
    }
}
