package com.labsoluciones.laboratoriolubricante.application.controller;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestComponente;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestEquipo;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.ComponenteServiceIn;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.EquipoServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/componentes")
@RequiredArgsConstructor
public class ComponenteController {
    private final ComponenteServiceIn componenteServiceIn;

    @PostMapping
    public ResponseEntity<ComponenteDTO> registrar(@RequestBody RequestComponente requestComponente) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(componenteServiceIn.crearComponenteIn(requestComponente));

    }

    @GetMapping
    public ResponseEntity<List<ComponenteDTO>> obtenerTodos () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(componenteServiceIn.obtenerTodosIn());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComponenteDTO>actualizar(@PathVariable Long id,@RequestBody RequestComponente requestComponente){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(componenteServiceIn.actualizarIn(id,requestComponente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ComponenteDTO> eliminar(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(componenteServiceIn.deleteIn(id));
    }
}