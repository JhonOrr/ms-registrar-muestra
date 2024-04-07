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
    public ComponenteDTO crearComponenteIn(RequestComponente requestComponente, String username) {
        return componenteServiceOut.crearComponenteOut(requestComponente, username);
    }

    @Override
    public Optional<ComponenteDTO> obtenerComponenteIn(Long id) {
        return componenteServiceOut.obtenerComponenteOut(id);
    }

    @Override
    public ComponenteDTO actualizarIn(Long id, RequestComponente requestComponente, String username) {
        return componenteServiceOut.actualizarOut(id, requestComponente, username);
    }

    @Override
    public String deleteIn(Long id, String username) {
        return componenteServiceOut.deleteOut(id, username);
    }

    @Override
    public List<ComponenteDTO> obtenerComponentesPorEquipo(Long idEquipo, String username) {
        return componenteServiceOut.obtenerComponentesPorEquipo(idEquipo, username);
    }
}
