package com.labsoluciones.laboratoriolubricante.domain.aggregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MuestraDTO {
    private Long idMuestra;
    private Double viscosidad;
    private Double oxidacion;
    private Double nitracion;
    private Double agua;
    private Integer estado;
    private String usuaCrea;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelet;
    private Timestamp dateDelet;
    private Long solicitudId;
    private Long componenteId;
    private Long lubricanteId;
}
