package com.labsoluciones.laboratoriolubricante.domain.ports.in;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.SolicitudDTO;

public interface SolicitudServiceIn {
    SolicitudDTO crearSolicitudIn(String username);
}
