package com.labsoluciones.laboratoriolubricante.infraestructure.adapters;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.constansts.Constants;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.SolicitudDTO;
import com.labsoluciones.laboratoriolubricante.domain.ports.out.SolicitudServiceOut;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.SolicitudEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.UsuarioEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.mapper.SolicitudMapper;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.SolicitudRepository;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;

@Service
@RequiredArgsConstructor

public class SolicitudAdapters implements SolicitudServiceOut {

    private final UsuarioRepository usuarioRepository;
    private final SolicitudRepository solicitudRepository;
    private final SolicitudMapper solicitudMapper;
    @Override
    public SolicitudDTO crearSolicitudOut(String username) {
        SolicitudEntity solicitud = getEntity(username);
        solicitudRepository.save(solicitud);
        return solicitudMapper.mapToDto(solicitud);
    }

    private SolicitudEntity getEntity(String username) {

        SolicitudEntity entity = new SolicitudEntity();

        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setDateCreate(getTimestamp());
        UsuarioEntity user = usuarioRepository.findByEmail(username).get();
        entity.setUsuario(user);
        return entity;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
