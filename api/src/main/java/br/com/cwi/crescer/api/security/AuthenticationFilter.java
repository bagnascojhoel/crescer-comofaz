package br.com.cwi.crescer.api.security;

import br.com.cwi.crescer.api.validator.security.HeaderProviderExisteValidator;
import br.com.cwi.crescer.api.validator.security.HeaderTokenExisteValidator;
import br.com.cwi.crescer.api.validator.security.HeaderUserIdExisteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String AUTHORIZATION_PROVIDER = "X-Authorization-Provider";

    private static final String USER_ID = "X-Authorization-User-Id";

    private static final String PRIVATE_REQUEST_MATCHER = "/privado/**";

    @Autowired
    private HeaderProviderExisteValidator headerProviderExisteValidator;

    @Autowired
    private HeaderUserIdExisteValidator headerUserIdExisteValidator;

    @Autowired
    private HeaderTokenExisteValidator headerTokenExisteValidator;

    protected AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(PRIVATE_REQUEST_MATCHER);
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        String token = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        String providerString = httpServletRequest.getHeader(AUTHORIZATION_PROVIDER);
        String userId = httpServletRequest.getHeader(USER_ID);

        headerProviderExisteValidator.check(providerString);
        headerUserIdExisteValidator.check(userId);
        headerTokenExisteValidator.check(token);

        ProviderType provider = ProviderType.valueOf(providerString);

        Authentication request = new FederationAuthentication(userId, token, provider, null, null, null);

        return getAuthenticationManager().authenticate(request);
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}
