package com.labsoluciones.laboratoriolubricante.infraestructure.mapper;


import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.MuestraDTO;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.MuestraEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MuestraMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public MuestraDTO mapToDto(MuestraEntity entity) {
        MuestraDTO muestraDTO = new MuestraDTO();
        return modelMapper.map(entity, MuestraDTO.class);
    }
}
