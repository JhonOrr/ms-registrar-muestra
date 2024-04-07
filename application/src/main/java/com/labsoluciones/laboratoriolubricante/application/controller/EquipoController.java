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
    //crear un equipo
    @PostMapping("/crear")
    public ResponseEntity<EquipoDTO> registrar(@RequestBody RequestEquipo requestEquipo,
                                               @RequestHeader("loggedInUser") String username) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(equipoServiceIn.crearEquipoIn(requestEquipo, username));
    }
    //obtener todos los equiops
    @GetMapping("/all")
    public ResponseEntity<List<EquipoDTO>> obtenerTodos (@RequestHeader("loggedInUser") String username) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(equipoServiceIn.obtenerTodosIn(username));
    }

    //obtener un equipo
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEquipo(@PathVariable Long id, @RequestHeader("loggedInUser") String username){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(equipoServiceIn.obtenerEquipoIn(id, username));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }

    }

    //Actualizar Equipo
    @PutMapping("/{id}/update")
    public ResponseEntity<?>actualizar(@PathVariable Long id,
                                               @RequestBody RequestEquipo requestEquipo,
                                               @RequestHeader("loggedInUser") String username){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(equipoServiceIn.actualizarIn(id, requestEquipo, username));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("El equipo " + id + " es incorrecto");
        }

    }

    //Eliminar Equipo
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminar(@PathVariable Long id, @RequestHeader("loggedInUser") String username) {
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(equipoServiceIn.deleteIn(id, username));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Equipo Incorrecto");
        }
    }
}
