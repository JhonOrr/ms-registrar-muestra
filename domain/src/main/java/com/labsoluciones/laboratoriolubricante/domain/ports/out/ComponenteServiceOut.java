package com.labsoluciones.laboratoriolubricante.domain.ports.out;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestComponente;

import java.util.List;
import java.util.Optional;

public interface ComponenteServiceOut {
    ComponenteDTO crearComponenteOut(RequestComponente requestComponente);
    Optional<ComponenteDTO> obtenerComponenteOut(Long id);

    List<ComponenteDTO> obtenerComponentesPorEquipo(Long idEquipo);

    List<ComponenteDTO> obtenerTodosOut();
    ComponenteDTO actualizarOut(Long id, RequestComponente requestComponente);
    ComponenteDTO deleteOut(Long id);
}
