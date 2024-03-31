package com.labsoluciones.laboratoriolubricante.infraestructure.adapters;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.constansts.Constants;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.MuestraDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestMuestra;
import com.labsoluciones.laboratoriolubricante.domain.ports.out.MuestraServiceOut;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.*;
import com.labsoluciones.laboratoriolubricante.infraestructure.mapper.MuestraMapper;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MuestraAdapters implements MuestraServiceOut {
    private final MuestraMapper muestraMapper;
    private final SolicitudRepository solicitudRepository;
    private final ComponenteRepository componenteRepository;
    private final LubricanteRepository lubricanteRepository;
    private final MuestraRepository muestraRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public MuestraDTO crearMuestraOut(String solicitud, RequestMuestra requestMuestra, String username) {
        List<SolicitudEntity> solitudesPermitidas = obtenerSolicitudesPermitidas(username);
        if(solitudesPermitidas.contains(solicitudRepository.findById(Long.parseLong(solicitud)).get())){
            MuestraEntity muestra = getEntity(solicitud, requestMuestra,username);
            muestraRepository.save(muestra);
            return muestraMapper.mapToDto(muestra);
        } else {
            throw new RuntimeException("Solicitud Incorrecta");
        }
    }

    private List<SolicitudEntity> obtenerSolicitudesPermitidas(String username){
        UsuarioEntity usuario = usuarioRepository.findByEmail(username).get();
        ClienteEntity cliente = usuario.getCliente();
        List<UsuarioEntity> usuarioPermitidos = usuarioRepository.findByCliente(cliente);
        Set<SolicitudEntity> solicitudes = new HashSet<>();
        for(UsuarioEntity u:usuarioPermitidos){
            solicitudes.addAll(solicitudRepository.findByUsuario(u));
        }
        return new ArrayList<>(solicitudes);
    }

    private MuestraEntity getEntity(String solicitud, RequestMuestra requestMuestra, String username){
        MuestraEntity entity = new MuestraEntity();
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(usuarioRepository.findByEmail(username).get().getIdUsuario().toString());
        entity.setDateCreate(getTimestamp());
        entity.setSolicitud(solicitudRepository.findById(Long.parseLong(solicitud)).get());

        Long idComponente = Long.parseLong(requestMuestra.getComponente());
        ComponenteEntity componente = componenteRepository.findById(idComponente).get();
        entity.setComponente(componente);


        entity.setLubricante(lubricanteRepository.findById(Long.parseLong(requestMuestra.getLubricante())).get());
        return entity;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
