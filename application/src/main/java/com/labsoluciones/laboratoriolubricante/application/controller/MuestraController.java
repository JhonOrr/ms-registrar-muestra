package com.labsoluciones.laboratoriolubricante.application.controller;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.MuestraDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestMuestra;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.MuestraServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/{solicitud}/muestras")
@RequiredArgsConstructor
public class MuestraController {

    private final MuestraServiceIn muestraServiceIn;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarMuestra(@PathVariable String solicitud,
                                                       @RequestBody RequestMuestra requestMuestra,
                                                       @RequestHeader("loggedInUser") String username){
        try{
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(muestraServiceIn.crearMuestraIn(solicitud, requestMuestra,username));
        } catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("El Usuario no tiene permiso para registrar una muestra en esta solicitud");
        }
    }
}
