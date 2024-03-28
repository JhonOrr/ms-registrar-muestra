package com.labsoluciones.laboratoriolubricante.infraestructure.adapters;

import com.labsoluciones.laboratoriolubricante.domain.aggregates.constansts.Constants;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ClienteDTO;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.response.ResponseSunat;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.request.RequestCliente;
import com.labsoluciones.laboratoriolubricante.domain.ports.out.ClienteServiceOut;
import com.labsoluciones.laboratoriolubricante.infraestructure.entity.ClienteEntity;
import com.labsoluciones.laboratoriolubricante.infraestructure.mapper.ClienteMapper;
import com.labsoluciones.laboratoriolubricante.infraestructure.repository.ClienteRepository;
import com.labsoluciones.laboratoriolubricante.infraestructure.rest.SunatClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteAdapters implements ClienteServiceOut {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final SunatClient sunatClient;

    @Value("${token.api}")
    private String tokenApi;

    public ClienteDTO crearClienteOut(RequestCliente requestCliente){
        ResponseSunat datosSunat = getExecutionSunat(requestCliente.getNumRuc());
        return clienteMapper.mapToDto(clienteRepository.save(getEntity(datosSunat, requestCliente)));
    }

    @Override
    public Optional<ClienteDTO> obtenerClienteOut(String ruc) {
        return null;
    }

    @Override
    public List<ClienteDTO> obtenerTodosOut() {
        List<ClienteDTO> clienteDtoList = new ArrayList<>();
        List<ClienteEntity> entities = clienteRepository.findByEstado(1);
        for(ClienteEntity clienteEntity:entities){
            ClienteDTO clienteDTO = clienteMapper.mapToDto(clienteEntity);
            clienteDtoList.add(clienteDTO);
        }
        return clienteDtoList;
    }

    @Override
    public ClienteDTO actualizarOut(Long id, RequestCliente requestCliente) {
        return null;
    }

    @Override
    public ClienteDTO deleteOut(Long id) {
        return null;
    }

    public ResponseSunat getExecutionSunat(String numero){
        String authorization = "Bearer " + tokenApi;
        ResponseSunat responseSunat = sunatClient.getInfo(numero, authorization);
        return responseSunat;
    }

    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }

    private ClienteEntity getEntity(ResponseSunat sunat, RequestCliente requestCliente){

        ClienteEntity entity = new ClienteEntity();
        entity.setRuc(sunat.getNumeroDocumento());
        entity.setRazonSocial(sunat.getRazonSocial());
        entity.setEstado(Constants.STATUS_ACTIVE);
        entity.setUsuaCrea(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimestamp());
        return entity;

    }




}
