package com.labsoluciones.laboratoriolubricante.application.controller;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ClienteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestCliente;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.ClienteServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteServiceIn clienteServiceIn;

    @PostMapping
    public ResponseEntity<ClienteDTO> registrar(@RequestBody RequestCliente requestCliente) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clienteServiceIn.crearClienteIn(requestCliente));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteServiceIn.obtenerClienteIn(id).get());
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodos () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clienteServiceIn.obtenerTodosIn());
    }

}
