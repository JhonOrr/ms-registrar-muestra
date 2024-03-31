package com.labsoluciones.laboratoriolubricante.infraestructure.mapper;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.EquipoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EquipoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public EquipoDTO mapToDto(EquipoEntity entity) {
        EquipoDTO equipoDTO = new EquipoDTO();
        equipoDTO = modelMapper.map(entity, EquipoDTO.class);
        return equipoDTO;
    }
}
