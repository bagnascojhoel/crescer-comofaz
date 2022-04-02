package br.com.cwi.crescer.api.security;

import br.com.cwi.crescer.api.exception.ErroAbstratoException;
import br.com.cwi.crescer.api.exception.NaoAutorizadoException;
import br.com.cwi.crescer.api.exception.NaoEncontradoException;
import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.api.security.dto.FacebookFederationResponse;
import br.com.cwi.crescer.api.security.dto.FederationAuthenticationPrincipal;
import br.com.cwi.crescer.api.validator.security.UsuarioNaoAutorizadoValidator;
import br.com.cwi.crescer.api.validator.security.UsuarioNaoExisteValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;

@Component
public class FederationAuthenticationProvider implements AuthenticationProvider {

    private static final String FACEBOOK_API_BASE_URL = "https://graph.facebook.com/me";

    private static final String FACEBOOK_REQUESTED_FIELDS = "?fields=id,email,name";

    @Autowired
    private UsuarioNaoExisteValidator usuarioNaoExisteValidator;

    @Autowired
    private UsuarioNaoAutorizadoValidator usuarioNaoAutorizadoValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(FederationAuthentication.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        FederationAuthenticationPrincipal request = (FederationAuthenticationPrincipal) authentication.getPrincipal();

        Authentication response = authentication;

        if (request.getProvider() == ProviderType.GOOGLE)
            response = authenticateWithGoogle(request.getToken(), request.getUserId());
        else if (request.getProvider() == ProviderType.FACEBOOK)
            response = authenticateWithFacebook(request.getToken());

        return response;
    }

    private Authentication authenticateWithGoogle(String token, String expectedUserId) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(new ApacheHttpTransport(), new JacksonFactory());

        try {
            GoogleIdToken verifiedToken = verifier.verify(token);

            usuarioNaoExisteValidator.check(verifiedToken);

            GoogleIdToken.Payload payload = verifiedToken.getPayload();

            String actualUserId = payload.getSubject();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String picture = (String) payload.get("picture");

            usuarioNaoAutorizadoValidator.check(expectedUserId, actualUserId);

            return new FederationAuthentication(actualUserId, token, ProviderType.GOOGLE, name, email, picture);

        } catch (IOException | GeneralSecurityException exception){
            throw new NaoAutorizadoException("Não foi possível validar o token" + exception.getMessage());
        }
    }

    private Authentication authenticateWithFacebook(String token) {
        String accessTokenForRequest = "&access_token=".concat(token);

        try {
            ResponseEntity<FacebookFederationResponse> response = new RestTemplate().getForEntity(
                    FACEBOOK_API_BASE_URL + FACEBOOK_REQUESTED_FIELDS + accessTokenForRequest,
                    FacebookFederationResponse.class
            );

            usuarioNaoAutorizadoValidator.check(response.getStatusCode());

            FacebookFederationResponse body = response.getBody();

                if (Objects.isNull(body))
                    throw new ErroAbstratoException("Ocorreu um erro inesperado ao requisitar dados do facebook.", HttpStatus.INTERNAL_SERVER_ERROR);

            return new FederationAuthentication(body.getId(), token, ProviderType.FACEBOOK, body.getName(), body.getEmail(), null);

        } catch (HttpClientErrorException httpClientErrorException) {
            if (httpClientErrorException.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {

                try {
                    AuthenticationException authException = new ObjectMapper().readValue(httpClientErrorException.getResponseBodyAsString(), AuthenticationException.class);
                    throw new ValidacaoNegocioException(authException.getMessage());
                } catch (JsonProcessingException jsonProcessingException) {
                    throw new ValidacaoNegocioException("Não foi possível autenticar o token.");
                }
            } else if (httpClientErrorException.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new ValidacaoNegocioException("Não foi possível autenticar o token.");
            } else if (httpClientErrorException.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new NaoEncontradoException("Usuário não encontrado.");
            } else {
                throw new ErroAbstratoException(httpClientErrorException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


}
