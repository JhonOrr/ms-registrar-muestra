package com.labsoluciones.laboratoriolubricante.domain.impl;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestEquipo;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.EquipoServiceIn;
import com.labsoluciones.laboratoriolubricante.domain.ports.out.EquipoServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipoServiceImpl implements EquipoServiceIn {
    private final EquipoServiceOut equipoServiceOut;

    @Override
    public EquipoDTO crearEquipoIn(RequestEquipo requestEquipo, String username) {
        return equipoServiceOut.crearEquipoOut(requestEquipo, username);
    }

    @Override
    public Optional<EquipoDTO> obtenerEquipoIn(Long id, String username) {
        return equipoServiceOut.obtenerEquipoOut(id, username);
    }


    @Override
    public List<EquipoDTO> obtenerTodosIn(String username) {
        return equipoServiceOut.obtenerTodosOut(username);
    }

    @Override
    public EquipoDTO actualizarIn(Long id, RequestEquipo requestEquipo, String username) {
        return equipoServiceOut.actualizarOut(id, requestEquipo, username);
    }

    @Override
    public String deleteIn(Long id, String username) {
        return equipoServiceOut.deleteOut(id, username);
    }

}
