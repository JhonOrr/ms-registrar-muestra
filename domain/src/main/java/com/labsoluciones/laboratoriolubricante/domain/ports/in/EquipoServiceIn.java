package com.labsoluciones.laboratoriolubricante.domain.ports.in;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestEquipo;

import java.util.List;
import java.util.Optional;

public interface EquipoServiceIn {
    EquipoDTO crearEquipoIn(RequestEquipo requestEquipo);
    Optional<EquipoDTO> obtenerEquipoIn(Long id);
    List<EquipoDTO> obtenerTodosIn();
    EquipoDTO actualizarIn(Long id, RequestEquipo requestEquipo);
    EquipoDTO deleteIn(Long id);

    List<EquipoDTO> obtenerEquiposPorCliente(Long idCliente);
}
