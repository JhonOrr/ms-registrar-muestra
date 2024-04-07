package com.labsoluciones.laboratoriolubricante.domain.ports.out;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestEquipo;

import java.util.List;
import java.util.Optional;

public interface EquipoServiceOut {
    EquipoDTO crearEquipoOut(RequestEquipo requestEquipo, String username);
    Optional<EquipoDTO> obtenerEquipoOut(Long id, String username);
    List<EquipoDTO> obtenerTodosOut(String username);
    EquipoDTO actualizarOut(Long id, RequestEquipo requestEquipo, String username);
    String deleteOut(Long id, String username);
}
