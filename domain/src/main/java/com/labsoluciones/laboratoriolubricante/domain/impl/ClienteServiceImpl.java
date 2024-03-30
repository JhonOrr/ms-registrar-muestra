package com.labsoluciones.laboratoriolubricante.domain.impl;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ClienteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestCliente;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.ClienteServiceIn;
import com.labsoluciones.laboratoriolubricante.domain.ports.out.ClienteServiceOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteServiceIn {

    private final ClienteServiceOut clienteServiceOut;
    @Override
    public ClienteDTO crearClienteIn(RequestCliente requestCliente) {
        return clienteServiceOut.crearClienteOut(requestCliente);
    }

    @Override
    public Optional<ClienteDTO> obtenerClienteIn(Long id) {
        return clienteServiceOut.obtenerClienteOut(id);
    }

    @Override
    public List<ClienteDTO> obtenerTodosIn() {
        return clienteServiceOut.obtenerTodosOut();
    }

    @Override
    public ClienteDTO actualizarIn(Long id, RequestCliente requestCliente) {
        return clienteServiceOut.actualizarOut(id, requestCliente);
    }

    @Override
    public ClienteDTO deleteIn(Long id) {
        return clienteServiceOut.deleteOut(id);
    }
}
