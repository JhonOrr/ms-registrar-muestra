package com.labsoluciones.laboratoriolubricante.domain.ports.in;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestEquipo;

import java.util.List;
import java.util.Optional;

public interface EquipoServiceIn {
    EquipoDTO crearEquipoIn(RequestEquipo requestEquipo, String username);
    Optional<EquipoDTO> obtenerEquipoIn(Long id, String username);
    List<EquipoDTO> obtenerTodosIn(String username);
    EquipoDTO actualizarIn(Long id, RequestEquipo requestEquipo, String username);
    String deleteIn(Long id, String username);
}
