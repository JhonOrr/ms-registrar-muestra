package com.labsoluciones.laboratoriolubricante.domain.ports.in;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestComponente;

import java.util.List;
import java.util.Optional;

public interface ComponenteServiceIn {
    ComponenteDTO crearComponenteIn(RequestComponente requestComponente, String username);
    Optional<ComponenteDTO> obtenerComponenteIn(Long id);
    List<ComponenteDTO> obtenerTodosIn();
    ComponenteDTO actualizarIn(Long id, RequestComponente requestComponente);
    ComponenteDTO deleteIn(Long id);

    List<ComponenteDTO> obtenerComponentesPorEquipo(Long idEquipo);
}
