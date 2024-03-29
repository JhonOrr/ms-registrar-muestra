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
    public EquipoDTO crearEquipoIn(RequestEquipo requestEquipo) {
        return equipoServiceOut.crearEquipoOut(requestEquipo);
    }

    @Override
    public Optional<EquipoDTO> obtenerEquipoIn(Long id) {
        return equipoServiceOut.obtenerEquipoOut(id);
    }


    @Override
    public List<EquipoDTO> obtenerTodosIn() {
        return equipoServiceOut.obtenerTodosOut();
    }

    @Override
    public EquipoDTO actualizarIn(Long id, RequestEquipo requestEquipo) {
        return equipoServiceOut.actualizarOut(id, requestEquipo);
    }

    @Override
    public EquipoDTO deleteIn(Long id) {
        return equipoServiceOut.deleteOut(id);
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorCliente(Long idCliente) {
        return equipoServiceOut.obtenerEquiposPorCliente(idCliente);
    }
}
