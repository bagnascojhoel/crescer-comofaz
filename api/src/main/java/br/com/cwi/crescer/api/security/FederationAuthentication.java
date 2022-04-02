package br.com.cwi.crescer.api.security;

import br.com.cwi.crescer.api.security.dto.FederationAuthenticationDetails;
import br.com.cwi.crescer.api.security.dto.FederationAuthenticationPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class FederationAuthentication implements Authentication {

    private final String userId;

    private final ProviderType provider;

    private final transient FederationAuthenticationPrincipal principal;

    private final String name;

    private final String email;

    private final String picture;

    private final transient FederationAuthenticationDetails details;

    public FederationAuthentication(
            String userId,
            String token,
            ProviderType provider,
            String name,
            String email,
            String picture
    ) {
        this.userId = userId;
        this.provider = provider;
        this.principal = new FederationAuthenticationPrincipal(userId, token, provider);

        this.name = name;
        this.email = email;
        this.picture = picture;
        this.details = new FederationAuthenticationDetails(name, email, picture);
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserId() {
        return this.userId;
    }

    public ProviderType getProvider() {
        return this.provider;
    }

    public String getPicture() {
        return this.picture;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return this.details;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean b) {
        //Método não necessário
    }

    @Override
    public String getName() {
        return this.name;
    }
}
