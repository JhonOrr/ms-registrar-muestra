package com.labsoluciones.laboratoriolubricante.domain.aggregates.response;

import lombok.Data;

@Data
public class ResponseSunat {
    public String razonSocial;
    public String tipoDocumento;
    public String numeroDocumento;
    public String estado;
    public String condicion;
    public String direccion;
    public String ubigeo;
    public String viaTipo;
    public String viaNombre;
    public String zonaCodigo;
    public String zonaTipo;
    public String numero;
    public String interior;
    public String lote;
    public String dpto;
    public String manzana;
    public String kilometro;
    public String distrito;
    public String provincia;
    public String departamento;
    public boolean esAgenteRetencion;
}
