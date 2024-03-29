package com.labsoluciones.laboratoriolubricante.infraestructure.adapters;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.constansts.Constants;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestEquipo;
import com.labsoluciones.laboratoriolubricante.domain.ports.out.EquipoServiceOut;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.ClienteEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.EquipoEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.mapper.EquipoMapper;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.ClienteRepository;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipoAdapter implements EquipoServiceOut {

    private final EquipoRepository equipoRepository;
    private final ClienteRepository clienteRepository;
    private final EquipoMapper equipoMapper;

    @Override
    public EquipoDTO crearEquipoOut(RequestEquipo requestEquipo) {
        equipoRepository.save(getEntity(requestEquipo));
        return equipoMapper.mapToDto(getEntity(requestEquipo));
    }

    @Override
    public Optional<EquipoDTO> obtenerEquipoOut(Long id) {
        return Optional.ofNullable(equipoMapper.mapToDto(equipoRepository.findById(id).get()));
    }

    @Override
    public List<EquipoDTO> obtenerEquiposPorCliente(Long idCliente) {
        List<EquipoDTO> equipoDTOList = new ArrayList<>();
        ClienteEntity cliente = clienteRepository.findById(idCliente).orElse(null);
        List<EquipoEntity> entities = equipoRepository.findByCliente(cliente);
        for (EquipoEntity equipo : entities) {
            EquipoDTO equipoDTO = equipoMapper.mapToDto(equipo);
            equipoDTOList.add(equipoDTO);
        }
        return equipoDTOList;
    }

    @Override
    public List<EquipoDTO> obtenerTodosOut() {
        List<EquipoDTO> equipoDTOList = new ArrayList<>();
        List<EquipoEntity> entities = equipoRepository.findAll();
        for (EquipoEntity equipo : entities) {
            EquipoDTO equipoDTO = equipoMapper.mapToDto(equipo);
            equipoDTOList.add(equipoDTO);
        }
        return equipoDTOList;
    }

    @Override
    public EquipoDTO actualizarOut(Long id, RequestEquipo requestEquipo) {
        boolean existe = equipoRepository.existsById(id);
        if (existe) {
            Optional<EquipoEntity> entity = equipoRepository.findById(id);
            equipoRepository.save(getEntityUpdate(entity.get(),requestEquipo));
        }
        return null;
    }

    @Override
    public EquipoDTO deleteOut(Long id) {
        boolean existe = equipoRepository.existsById(id);
        if (existe) {
            Optional<EquipoEntity> entity = equipoRepository.findById(id);
            entity.get().setEstado(0);
            entity.get().setUsuaDelet(Constants.AUDIT_ADMIN);
            entity.get().setDateDelet(getTimestamp());
            equipoRepository.save(entity.get());
            return equipoMapper.mapToDto(entity.get());
        }
        return null;
    }

    private EquipoEntity getEntity(RequestEquipo requestEquipo) {

        ClienteEntity cliente = clienteRepository.findByRazonSocial(requestEquipo.getNombreCliente());

        EquipoEntity entity = new EquipoEntity();
        entity.setNomEquipo(requestEquipo.getNombreEquipo());
        entity.setMarca(requestEquipo.getMarcaEquipo());
        entity.setModelo(requestEquipo.getModeloEquipo());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        entity.setCliente(cliente);

        return entity;
    }

    private EquipoEntity getEntityUpdate (EquipoEntity equipoActualizar, RequestEquipo requestEquipo) {
        ClienteEntity cliente = clienteRepository.findByRazonSocial(requestEquipo.getNombreCliente());

        equipoActualizar.setNomEquipo(requestEquipo.getNombreEquipo());
        equipoActualizar.setMarca(requestEquipo.getMarcaEquipo());
        equipoActualizar.setModelo(requestEquipo.getModeloEquipo());
        equipoActualizar.setEstado(Constants.STATUS_ACTIVE);
        equipoActualizar.setUsuaModif(Constants.AUDIT_ADMIN);
        equipoActualizar.setDateModif(getTimestamp());
        equipoActualizar.setCliente(cliente);
        return equipoActualizar;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

}
