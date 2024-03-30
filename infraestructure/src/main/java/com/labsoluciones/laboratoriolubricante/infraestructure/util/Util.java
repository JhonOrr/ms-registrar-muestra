package com.labsoluciones.laboratoriolubricante.infraestructure.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labsoluciones.laboratoriolubricante.domain.aggregates.dto.ClienteDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Util {
    public static String convertToJson(ClienteDTO clienteDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(clienteDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertFromJson (String json, Class<T> valueType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json,valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
