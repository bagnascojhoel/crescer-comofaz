package br.com.cwi.crescer.api.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FacebookFederationResponse {

    private String id;

    private String name;

    private String email;

    private String profilePic;

}
