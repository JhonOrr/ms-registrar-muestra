package com.labsoluciones.laboratoriolubricante.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolicitudDTO {
    private Long idSolicitud;
    private Integer estado;
    private String usuaCrea;
    private Timestamp fechaArribo;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;
    private int usuario;
}
