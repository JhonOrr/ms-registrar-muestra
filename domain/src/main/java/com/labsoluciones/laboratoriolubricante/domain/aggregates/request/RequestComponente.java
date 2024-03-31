package com.labsoluciones.laboratoriolubricante.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestComponente {
    private String nombreComponente;
    private Long idEquipo;
}
