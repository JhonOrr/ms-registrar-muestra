package com.labsoluciones.laboratoriolubricante.infraestructure.repository;

import com.labsoluciones.laboratoriolubricante.infraestructure.entity.ClienteEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<EquipoEntity, Long> {
    EquipoEntity findByNomEquipo(String nombreEquipo);
    List<EquipoEntity> findByCliente(ClienteEntity cliente);
}
