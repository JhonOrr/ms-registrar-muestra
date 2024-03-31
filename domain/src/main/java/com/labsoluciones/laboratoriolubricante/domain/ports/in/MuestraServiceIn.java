package com.labsoluciones.laboratoriolubricante.domain.ports.in;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.MuestraDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestMuestra;

public interface MuestraServiceIn {
    MuestraDTO crearMuestraIn(String solicitud, RequestMuestra requestMuestra, String username);
}
