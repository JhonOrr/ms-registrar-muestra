package com.labsoluciones.laboratoriolubricante.application.controller;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.SolicitudDTO;
import com.labsoluciones.laboratoriolubricante.domain.ports.in.SolicitudServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/solicitud")
public class SolicitudController {
    private final SolicitudServiceIn solicitudServiceIn;
    @PostMapping("/crear")
    public ResponseEntity<SolicitudDTO> crearSolicitud(@RequestHeader("loggedInUser") String usenname){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitudServiceIn.crearSolicitudIn(usenname));
    }
}
