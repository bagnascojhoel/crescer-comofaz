package br.com.cwi.crescer.api.validator.security;

import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.stereotype.Component;

@Component
public class UsuarioNaoExisteValidator {
    public void check(GoogleIdToken googleIdToken) {
        if (googleIdToken == null) throw new NaoEncontradoException("Usuário não encontrado.");
    }
}
