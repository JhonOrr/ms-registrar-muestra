package com.labsoluciones.laboratoriolubricante.domain.ports.in;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ClienteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestCliente;

import java.util.List;
import java.util.Optional;

public interface ClienteServiceIn {
    ClienteDTO crearClienteIn(RequestCliente requestCliente);
    Optional<ClienteDTO> obtenerClienteIn(String ruc);
    List<ClienteDTO> obtenerTodosIn();
    ClienteDTO actualizarIn(Long id, RequestCliente requestCliente);
    ClienteDTO deleteIn(Long id);
}
