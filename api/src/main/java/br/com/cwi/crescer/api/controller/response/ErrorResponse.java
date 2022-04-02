package br.com.cwi.crescer.api.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private final String error;

}