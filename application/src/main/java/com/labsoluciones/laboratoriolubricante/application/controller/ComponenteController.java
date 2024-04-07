package com.labsoluciones.laboratoriolubricante.application.controller;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestComponente;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.ComponenteServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ComponenteController {
    private final ComponenteServiceIn componenteServiceIn;
    //crear componente
    @PostMapping("/componentes/crear")
    public ResponseEntity<?> registrar(@RequestBody RequestComponente requestComponente,
                                                   @RequestHeader("loggedInUser") String username) {
        try{
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(componenteServiceIn.crearComponenteIn(requestComponente, username));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Equipo Incorrecto");
        }
    }
    //obtener todos los componentes de un equipo
    @GetMapping("equipos/{idEquipo}/componentes")
    public ResponseEntity<?> obtenerComponentesPorEquipo(@PathVariable Long idEquipo,
                                                                           @RequestHeader("loggedInUser") String username) {
        try{
            List<ComponenteDTO> componentes = componenteServiceIn.obtenerComponentesPorEquipo(idEquipo, username);
            return ResponseEntity.ok(componentes);
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }

    //editar un componente
    @PutMapping("componentes/{id}/update")
    public ResponseEntity<?>actualizarComponente(@PathVariable Long id,
                                                   @RequestBody RequestComponente requestComponente,
                                                   @RequestHeader("loggedInUser") String username){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(componenteServiceIn.actualizarIn(id, requestComponente, username));
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }

    }

    @DeleteMapping("componentes/{id}/delete")
    public ResponseEntity<?> eliminar(@PathVariable Long id,
                                                  @RequestHeader("loggedInUser") String username) {
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(componenteServiceIn.deleteIn(id, username));
        } catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(e.getMessage());
        }

    }
}
