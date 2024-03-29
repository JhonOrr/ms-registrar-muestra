package com.labsoluciones.laboratoriolubricante.infraestructure.repository;

import com.labsoluciones.laboratoriolubricante.infraestructure.entity.ComponenteEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComponenteRepository extends JpaRepository<ComponenteEntity, Long> {
    List<ComponenteEntity> findByEquipo(EquipoEntity equipo);
}
