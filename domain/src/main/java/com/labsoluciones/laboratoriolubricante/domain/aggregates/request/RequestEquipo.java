package com.labsoluciones.laboratoriolubricante.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEquipo {
    private String nombreEquipo;
    private String marcaEquipo;
    private String modeloEquipo;
}
