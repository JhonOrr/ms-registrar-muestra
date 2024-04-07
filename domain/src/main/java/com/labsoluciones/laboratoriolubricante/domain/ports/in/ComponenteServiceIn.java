package com.labsoluciones.laboratoriolubricante.domain.ports.in;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestComponente;

import java.util.List;
import java.util.Optional;

public interface ComponenteServiceIn {
    ComponenteDTO crearComponenteIn(RequestComponente requestComponente, String username);
    Optional<ComponenteDTO> obtenerComponenteIn(Long id);
    ComponenteDTO actualizarIn(Long id, RequestComponente requestComponente, String username);
    String deleteIn(Long id, String username);

    List<ComponenteDTO> obtenerComponentesPorEquipo(Long idEquipo, String username);
}
