package com.labsoluciones.laboratoriolubricante.domain.ports.out;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.MuestraDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestMuestra;

public interface MuestraServiceOut {
    MuestraDTO crearMuestraOut(String solicitud, RequestMuestra requestMuestra, String username);
}
