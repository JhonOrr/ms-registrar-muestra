package com.labsoluciones.laboratoriolubricante.domain.impl;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestComponente;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.ComponenteServiceIn;
import com.labsoluciones.laboratoriolubricante.domain.ports.out.ComponenteServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComponenteServiceImpl implements ComponenteServiceIn {
    private final ComponenteServiceOut componenteServiceOut;

    @Override
    public ComponenteDTO crearComponenteIn(RequestComponente requestComponente) {
        return componenteServiceOut.crearComponenteOut(requestComponente);
    }

    @Override
    public Optional<ComponenteDTO> obtenerComponenteIn(Long id) {
        return componenteServiceOut.obtenerComponenteOut(id);
    }

    @Override
    public List<ComponenteDTO> obtenerTodosIn() {
        return componenteServiceOut.obtenerTodosOut();
    }

    @Override
    public ComponenteDTO actualizarIn(Long id, RequestComponente requestComponente) {
        return componenteServiceOut.actualizarOut(id, requestComponente);
    }

    @Override
    public ComponenteDTO deleteIn(Long id) {
        return componenteServiceOut.deleteOut(id);
    }

    @Override
    public List<ComponenteDTO> obtenerComponentesPorEquipo(Long idEquipo) {
        return componenteServiceOut.obtenerComponentesPorEquipo(idEquipo);
    }
}
