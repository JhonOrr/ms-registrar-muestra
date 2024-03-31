package com.labsoluciones.laboratoriolubricante.infraestructure.mapper;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.SolicitudDTO;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.EquipoEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.SolicitudEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SolicitudMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public SolicitudDTO mapToDto(SolicitudEntity entity) {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        solicitudDTO = modelMapper.map(entity, SolicitudDTO.class);
        return solicitudDTO;
    }
}
