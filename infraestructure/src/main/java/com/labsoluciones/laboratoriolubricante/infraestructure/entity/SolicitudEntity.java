package com.labsoluciones.laboratoriolubricante.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "solicitud")
@Getter
@Setter
public class SolicitudEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;
    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name="fecha_arribo")
    private Timestamp fechaArribo;
    @Column(name = "fecha_fin")
    private Timestamp fechaFin;
    @Column(name = "date_create")
    private Timestamp dateCreate;
    @Column(name = "usua_modif", length = 45)
    private String usuaModif;
    @Column(name = "date_modif")
    private Timestamp dateModif;
    @Column(name = "usua_delete", length = 45)
    private String usuaDelet;
    @Column(name = "date_delete")
    private Timestamp dateDelet;

    // Relacion con Usuario
    @ManyToOne(optional = false)
    @JoinColumn(name = "usua_create", nullable = false)
    private UsuarioEntity usuario;
}
