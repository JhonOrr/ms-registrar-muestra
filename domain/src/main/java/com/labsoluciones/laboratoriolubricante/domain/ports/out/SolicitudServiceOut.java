package com.labsoluciones.laboratoriolubricante.domain.ports.out;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.SolicitudDTO;

public interface SolicitudServiceOut {
    SolicitudDTO crearSolicitudOut(String username);
}
