package br.com.cwi.crescer.api.validator.security;

import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;
import org.springframework.stereotype.Component;

@Component
public class HeaderProviderExisteValidator {
    public void check(String provider) {
        if (provider == null || provider.isEmpty())
            throw new ValidacaoNegocioException("Um request privado precisa do provedor da autenticação no header 'X-Authorization-Provider' para ser autenticado.");
    }
}
