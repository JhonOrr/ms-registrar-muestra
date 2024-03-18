package com.labsoluciones.laboratoriolubricante.application.controller;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestEquipo;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.EquipoServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/equipos")
@RequiredArgsConstructor
public class EquipoController {
    private final EquipoServiceIn equipoServiceIn;

    @PostMapping
    public ResponseEntity<EquipoDTO> registrar(@RequestBody RequestEquipo requestEquipo) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(equipoServiceIn.crearEquipoIn(requestEquipo));

    }

    @GetMapping
    public ResponseEntity<List<EquipoDTO>> obtenerTodos () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(equipoServiceIn.obtenerTodosIn());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipoDTO>actualizar(@PathVariable Long id,@RequestBody RequestEquipo requestEquipo){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(equipoServiceIn.actualizarIn(id,requestEquipo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EquipoDTO> eliminar(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(equipoServiceIn.deleteIn(id));
    }
}
