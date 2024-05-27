package com.example.mailservice.util;

import com.example.shared.exception.SerializationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonConverter {

    public String serializeToJson(Object o) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("Can't convert obj to json: " + e.getMessage(), e);
            throw new SerializationException(e);
        }
    }

    public <T> T deserializeFromJson(String json, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Can't convert json to obj: " + e.getMessage(), e);
            throw new SerializationException(e);
        }
    }
}
