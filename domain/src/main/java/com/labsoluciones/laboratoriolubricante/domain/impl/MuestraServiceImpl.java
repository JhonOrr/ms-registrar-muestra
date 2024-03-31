package com.labsoluciones.laboratoriolubricante.domain.impl;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.MuestraDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestMuestra;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.MuestraServiceIn;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.SolicitudServiceIn;
import com.labsoluciones.laboratoriolubricante.domain.ports.out.MuestraServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
@RequiredArgsConstructor
public class MuestraServiceImpl implements MuestraServiceIn {
    private final MuestraServiceOut muestraServiceOut;

    @Override
    public MuestraDTO crearMuestraIn(String solicitud, RequestMuestra requestMuestra, String username) {
        return muestraServiceOut.crearMuestraOut(solicitud, requestMuestra, username);
    }
}
