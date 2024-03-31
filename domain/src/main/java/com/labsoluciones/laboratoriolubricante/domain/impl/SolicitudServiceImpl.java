package com.labsoluciones.laboratoriolubricante.domain.impl;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.SolicitudDTO;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.SolicitudServiceIn;
import com.labsoluciones.laboratoriolubricante.domain.ports.out.SolicitudServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SolicitudServiceImpl implements SolicitudServiceIn {

    private final SolicitudServiceOut solicitudServiceOut;
    @Override
    public SolicitudDTO crearSolicitudIn(String username) {
        return solicitudServiceOut.crearSolicitudOut(username);
    }
}
