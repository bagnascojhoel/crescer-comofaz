package br.com.cwi.crescer.api.security.dto;

import br.com.cwi.crescer.api.security.ProviderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FederationAuthenticationPrincipal {

    private final String userId;

    private final String token;

    private final ProviderType provider;

}
