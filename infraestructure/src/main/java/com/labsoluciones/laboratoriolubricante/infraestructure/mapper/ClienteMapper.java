package com.labsoluciones.laboratoriolubricante.infraestructure.mapper;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ClienteDTO;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.ClienteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public ClienteDTO mapToDto(ClienteEntity entity){
        return modelMapper.map(entity, ClienteDTO.class);
    }
    public ClienteEntity mapToEntity(ClienteDTO personaDto){
        return modelMapper.map(personaDto, ClienteEntity.class);
    }
}
