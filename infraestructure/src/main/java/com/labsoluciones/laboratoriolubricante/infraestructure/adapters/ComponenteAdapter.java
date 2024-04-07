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
import java.util.*;
import java.util.stream.Collectors;

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
            componenteRepository.save(getEntity(requestComponente, username));
            return componenteMapper.mapToDto(getEntity(requestComponente, username));
        } else {
            throw  new RuntimeException("Equipo incorrecto");
        }
    }

    @Override
    public Optional<ComponenteDTO> obtenerComponenteOut(Long id) {
        return Optional.ofNullable(componenteMapper.mapToDto(componenteRepository.findById(id).get()));
    }

    @Override
    public List<ComponenteDTO> obtenerComponentesPorEquipo(Long idEquipo, String username) {
        List<EquipoEntity> equiposPermitidos = obtenerEquiposPermitidos(username);
        List<ComponenteDTO> componenteDTOList = new ArrayList<>();
        EquipoEntity equipo = equipoRepository.findById(idEquipo).
                orElseThrow(() -> new NoSuchElementException("Equipo " + idEquipo + " no encontrado"));
        if(equiposPermitidos.contains(equipo)){
            List<ComponenteEntity> componentesPermitidos = obtenerComponentesPermitidos(equipo);
            for(ComponenteEntity componente : componentesPermitidos){
                ComponenteDTO componenteDTO = componenteMapper.mapToDto(componente);
                componenteDTOList.add(componenteDTO);
            }
            return componenteDTOList;
        } else {
            throw new RuntimeException("Equipo " + idEquipo + " no encontrado");
        }
    }

    @Override
    public ComponenteDTO actualizarOut(Long id, RequestComponente requestComponente, String username) {
        ComponenteEntity componente = componenteRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Componente " + id + " no encontrado"));
        List<EquipoEntity> equiposPermitidos = obtenerEquiposPermitidos(username);
        boolean permitido = equiposPermitidos.contains(componente.getEquipo());
        if(!permitido){
            throw new RuntimeException("Componente " + id + " no encontrado");
        }
        EquipoEntity equipoNuevo = equipoRepository.findById(requestComponente.getIdEquipo()).
                orElseThrow(()-> new NoSuchElementException("Equipo " + requestComponente.getIdEquipo() + " incorrecto"));
        if(!equiposPermitidos.contains(equipoNuevo)){
            throw new RuntimeException("Equipo " + equipoNuevo.getIdEquipo() + " Incorrecto");
        }
        ComponenteEntity componenteActualizado = componenteRepository.save(
                getEntityUpdate(
                        componente,
                        requestComponente,
                        equipoNuevo,
                        username)
        );
        return componenteMapper.mapToDto(componenteActualizado);
    }

    @Override
    public String deleteOut(Long id, String username) {
        ComponenteEntity componente = componenteRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Componente " + id + " no encontrado"));
        List<EquipoEntity> equiposPermitidos = obtenerEquiposPermitidos(username);
        boolean permitido = equiposPermitidos.contains(componente.getEquipo());
        if(!permitido){
            throw new RuntimeException("Componente " + id + " no encontrado");
        }
        ComponenteEntity componenteEliminado = componenteRepository.save(
                getEntityDelete(
                        componente,
                        username)
        );
        return "Componente " + id + " eliminado con éxito";
    }

    private ComponenteEntity getEntity(RequestComponente requestComponente, String username) {
//        EquipoEntity equipo = equipoRepository.findByNomEquipo(requestComponente.getNombreEquipo());
        UsuarioEntity usuario = usuarioRepository.findByEmail(username).get();
        ComponenteEntity entity = new ComponenteEntity();
        entity.setNomComponente(requestComponente.getNombreComponente());
//        entity.setEquipo(equipo);
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(usuario.getIdUsuario().toString());
        entity.setDateCreate(getTimestamp());
        entity.setEquipo(equipoRepository.findById(requestComponente.getIdEquipo()).get());
        return entity;
    }

    private ComponenteEntity getEntityUpdate(ComponenteEntity componenteActualizar,
                                             RequestComponente requestComponente,
                                             EquipoEntity equipoNuevo,
                                             String username) {
        ComponenteEntity entity = new ComponenteEntity();
        entity.setNomComponente(requestComponente.getNombreComponente());
        entity.setEquipo(equipoNuevo);
        entity.setUsuaModif(obtenerUsuario(username).getIdUsuario().toString());
        entity.setDateModif(getTimestamp());
        return entity;
    }

    private ComponenteEntity getEntityDelete (ComponenteEntity componenteEliminar, String username) {
        componenteEliminar.setEstado(0);
        componenteEliminar.setUsuaDelet(obtenerUsuario(username).getIdUsuario().toString());
        return componenteEliminar;
    }



    private List<EquipoEntity> obtenerEquiposPermitidos(String username){
        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmail(username);
        ClienteEntity cliente = usuario.get().getCliente();
        List<EquipoEntity> equiposCliente = equipoRepository.findByCliente(cliente);
        return equiposCliente.stream()
                .filter(equipo -> equipo.getEstado()==1)
                .toList();
    }

    private List<ComponenteEntity> obtenerComponentesPermitidos(EquipoEntity equipo){
        List<ComponenteEntity> componentesEquipo = componenteRepository.findByEquipo(equipo);
        return componentesEquipo.stream()
                .filter(componente -> componente.getEstado() == 1)
                .toList();
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

    private UsuarioEntity obtenerUsuario(String username){
        return usuarioRepository.findByEmail(username).get();
    }
}
