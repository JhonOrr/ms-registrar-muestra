package com.labsoluciones.laboratoriolubricante.domain.aggregates.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
    private String token;
}