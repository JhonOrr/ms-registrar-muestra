package com.labsoluciones.laboratoriolubricante.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "muestra")
@Getter
@Setter
public class MuestraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_muestra")
    private Long idMuestra;
    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "viscocidad")
    private Double viscosidad;
    @Column(name = "oxidacion")
    private Double oxidacion;
    @Column(name = "nitracion")
    private Double nitracion;
    @Column(name = "agua")
    private Double agua;


    @Column(name = "usua_create", length = 45)
    private String usuaCrea;
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

    // Rleacion con Solicitud, Componente, Lubricante
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_solicitud")
    private SolicitudEntity solicitud;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_componente")
    private ComponenteEntity componente;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_lubricante")
    private LubricanteEntity lubricante;

}
