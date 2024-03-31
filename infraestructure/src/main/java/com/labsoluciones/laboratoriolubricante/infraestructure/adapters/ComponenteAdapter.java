package com.labsoluciones.laboratoriolubricante.infraestructure.adapters;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.constansts.Constants;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ComponenteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestComponente;
import com.labsoluciones.laboratoriolubricante.domain.ports.out.ComponenteServiceOut;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.ClienteEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.ComponenteEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.EquipoEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.UsuarioEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.mapper.ComponenteMapper;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.ComponenteRepository;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.EquipoRepository;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComponenteAdapter implements ComponenteServiceOut {

    private final ComponenteRepository componenteRepository;
    private final EquipoRepository equipoRepository;
    private final ComponenteMapper componenteMapper;
    private final UsuarioRepository usuarioRepository;
    @Override
    public ComponenteDTO crearComponenteOut(RequestComponente requestComponente, String username) {
        EquipoEntity equipo = equipoRepository.findById(requestComponente.getIdEquipo()).get();
        List<EquipoEntity> equiposPermitidos = obtenerEquiposPermitidos(username);
        if(equiposPermitidos.contains(equipo)){
            componenteRepository.save(getEntity(requestComponente));
            return componenteMapper.mapToDto(getEntity(requestComponente));
        } else {
            throw  new RuntimeException("Equipo incorrecto");
        }

    }

    private List<EquipoEntity> obtenerEquiposPermitidos(String username){
        UsuarioEntity usuario = usuarioRepository.findByEmail(username).get();
        ClienteEntity cliente = usuario.getCliente();
        List<EquipoEntity> equiposPermitidos = equipoRepository.findByCliente(cliente);
        return equiposPermitidos;
    }
    @Override
    public Optional<ComponenteDTO> obtenerComponenteOut(Long id) {
        return Optional.ofNullable(componenteMapper.mapToDto(componenteRepository.findById(id).get()));
    }

    @Override
    public List<ComponenteDTO> obtenerComponentesPorEquipo(Long idEquipo) {
        List<ComponenteDTO> componenteDTOList = new ArrayList<>();
        EquipoEntity equipo = equipoRepository.findById(idEquipo).orElse(null);
        List<ComponenteEntity> entities = componenteRepository.findByEquipo(equipo);
        for (ComponenteEntity componente : entities) {
            ComponenteDTO componenteDTO = componenteMapper.mapToDto(componente);
            componenteDTOList.add(componenteDTO);
        }
        return componenteDTOList;
    }

    @Override
    public List<ComponenteDTO> obtenerTodosOut() {
        List<ComponenteDTO> componenteDTOList = new ArrayList<>();
        List<ComponenteEntity> entities = componenteRepository.findAll();
        for (ComponenteEntity componente : entities) {
            ComponenteDTO componenteDTO = componenteMapper.mapToDto(componente);
            componenteDTOList.add(componenteDTO);
        }
        return componenteDTOList;
    }

    @Override
    public ComponenteDTO actualizarOut(Long id, RequestComponente requestComponente) {
        boolean existe = componenteRepository.existsById(id);
        if (existe) {
            Optional<ComponenteEntity> entity = componenteRepository.findById(id);
            componenteRepository.save(getEntityUpdate(entity.get(), requestComponente));
        }
        return null;
    }

    @Override
    public ComponenteDTO deleteOut(Long id) {
        boolean existe = componenteRepository.existsById(id);
        if (existe) {
            Optional<ComponenteEntity> entity = componenteRepository.findById(id);
            entity.get().setEstado(0);
            entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            entity.get().setDateDelet(getTimestamp());
            componenteRepository.save(entity.get());
            return componenteMapper.mapToDto(entity.get());
        }
        return null;
    }

    private ComponenteEntity getEntity(RequestComponente requestComponente) {
//        EquipoEntity equipo = equipoRepository.findByNomEquipo(requestComponente.getNombreEquipo());

        ComponenteEntity entity = new ComponenteEntity();
        entity.setNomComponente(requestComponente.getNombreComponente());
//        entity.setEquipo(equipo);
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setEquipo(equipoRepository.findById(requestComponente.getIdEquipo()).get());
        return entity;
    }

    private ComponenteEntity getEntityUpdate(ComponenteEntity componenteActualizar,  RequestComponente requestComponente) {
//        EquipoEntity equipo = equipoRepository.findByNomEquipo(requestComponente.getNombreEquipo());

        ComponenteEntity entity = new ComponenteEntity();
        entity.setNomComponente(requestComponente.getNombreComponente());
//        entity.setEquipo(equipo);
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaModif(Constants.AUDIT_ADMIN);
        entity.setDateModif(getTimestamp());
        return entity;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
