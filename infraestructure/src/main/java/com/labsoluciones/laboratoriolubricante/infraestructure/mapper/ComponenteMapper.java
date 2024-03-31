package com.labsoluciones.laboratoriolubricante.infraestructure.mapper;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.ComponenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ComponenteMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public ComponenteDTO mapToDto(ComponenteEntity entity) {
        return modelMapper.map(entity, ComponenteDTO.class);
    }
}
