package com.labsoluciones.laboratoriolubricante.infraestructure.adapters;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.constansts.Constants;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.EquipoDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestEquipo;
import com.labsoluciones.laboratoriolubricante.domain.ports.out.EquipoServiceOut;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.ClienteEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.ComponenteEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.EquipoEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.UsuarioEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.mapper.EquipoMapper;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.ClienteRepository;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.ComponenteRepository;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.EquipoRepository;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipoAdapters implements EquipoServiceOut {

    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;
    private final UsuarioRepository usuarioRepository;
    private final ComponenteRepository componenteRepository;

    @Override
    public EquipoDTO crearEquipoOut(RequestEquipo requestEquipo, String username) {
        EquipoEntity equipoCreado = equipoRepository.save(getEntity(requestEquipo, username));
        return equipoMapper.mapToDto(equipoCreado);
    }

    @Override
    public Optional<EquipoDTO> obtenerEquipoOut(Long id, String username) {
        List<EquipoEntity> equiposPermitidos = obtenerEquiposPermitidos(username);
        EquipoEntity equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Equipo " + id + " no encontrado"));
        if(equiposPermitidos.contains(equipo)){
            return Optional.ofNullable(equipoMapper.mapToDto(equipo));
        } else {
            throw new RuntimeException("Equipo " + id + " no encontrado");
        }

    }

    @Override
    public List<EquipoDTO> obtenerTodosOut(String username) {
        List<EquipoDTO> equipoDTOList = new ArrayList<>();
        List<EquipoEntity> entities = obtenerEquiposPermitidos(username);
        for (EquipoEntity equipo : entities) {
            EquipoDTO equipoDTO = equipoMapper.mapToDto(equipo);
            equipoDTOList.add(equipoDTO);
        }
        return equipoDTOList;
    }

    @Override
    public EquipoDTO actualizarOut(Long id, RequestEquipo requestEquipo, String username) {
        EquipoEntity equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Equipo " + id + " no encontrado"));

        List<EquipoEntity> equiposPermitidosList = obtenerEquiposPermitidos(username);
        boolean permitido = equiposPermitidosList.contains(equipo);

        if(!permitido){
            throw new RuntimeException("El equipo no existe");
        }
        EquipoEntity equipoActualizado = equipoRepository.save(getEntityUpdate(equipo,requestEquipo,username));
        return equipoMapper.mapToDto(equipoActualizado);
    }

    @Override
    public String deleteOut(Long id, String username) {
        EquipoEntity equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Equipo " + id + " no encontrado"));

        List<EquipoEntity> equiposPermitidosList = obtenerEquiposPermitidos(username);
        boolean permitido = equiposPermitidosList.contains(equipo);

        if(!permitido){
            throw new RuntimeException("El equipo no existe");
        }
        EquipoEntity equipoEliminado = equipoRepository.save(getEntityDelete(equipo, username));
        List<ComponenteEntity> componentesEliminar = componenteRepository.findByEquipo(equipoEliminado);
        for(ComponenteEntity componente : componentesEliminar){
            componente.setEstado(0);
            componente.setUsuaDelet(obtenerUsuario(username).getIdUsuario().toString());
            componenteRepository.save(componente);
        }
        return "Equipo " + id + " eliminado con exito";
    }

    private EquipoEntity getEntity(RequestEquipo requestEquipo, String username) {
        EquipoEntity entity = new EquipoEntity();
        entity.setNomEquipo(requestEquipo.getNombreEquipo());
        entity.setMarca(requestEquipo.getMarcaEquipo());
        entity.setModelo(requestEquipo.getModeloEquipo());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(obtenerUsuario(username).getIdUsuario().toString());
        entity.setDateCreate(getTimestamp());
        entity.setCliente(usuarioRepository.findByEmail(username).get().getCliente());
        return entity;
    }

    private EquipoEntity getEntityUpdate (EquipoEntity equipoActualizar, RequestEquipo requestEquipo, String username) {
        equipoActualizar.setNomEquipo(requestEquipo.getNombreEquipo());
        equipoActualizar.setMarca(requestEquipo.getMarcaEquipo());
        equipoActualizar.setModelo(requestEquipo.getModeloEquipo());
        equipoActualizar.setUsuaModif(obtenerUsuario(username).getIdUsuario().toString());
        equipoActualizar.setDateModif(getTimestamp());
        return equipoActualizar;
    }

    private EquipoEntity getEntityDelete (EquipoEntity equipoEliminar, String username) {
        equipoEliminar.setEstado(0);
        equipoEliminar.setUsuaDelet(obtenerUsuario(username).getIdUsuario().toString());
        return equipoEliminar;
    }


    //Metodos auxiliares
    private UsuarioEntity obtenerUsuario(String username){
        return usuarioRepository.findByEmail(username).get();
    }
    private List<EquipoEntity> obtenerEquiposPermitidos(String username){
        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmail(username);
        ClienteEntity cliente = usuario.get().getCliente();
        List<EquipoEntity> equiposCliente = equipoRepository.findByCliente(cliente);
        List<EquipoEntity> equiposPermitidosList = equiposCliente.stream()
                .filter(equipo -> equipo.getEstado()==1)
                .collect(Collectors.toList());
        return equiposPermitidosList;
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

}
