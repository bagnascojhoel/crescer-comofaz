package br.com.cwi.crescer.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FederationAuthenticationDetails {

    private final String name;

    private final String email;

    private final String picture;

}
