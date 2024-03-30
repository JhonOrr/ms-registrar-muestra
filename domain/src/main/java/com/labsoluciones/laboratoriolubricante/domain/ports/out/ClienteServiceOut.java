package com.labsoluciones.laboratoriolubricante.domain.ports.out;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ClienteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestCliente;

import java.util.List;
import java.util.Optional;

public interface ClienteServiceOut {
    ClienteDTO crearClienteOut(RequestCliente requestCliente);
    Optional<ClienteDTO> obtenerClienteOut(Long id);
    List<ClienteDTO> obtenerTodosOut();
    ClienteDTO actualizarOut(Long id, RequestCliente requestCliente);
    ClienteDTO deleteOut(Long id);
}
