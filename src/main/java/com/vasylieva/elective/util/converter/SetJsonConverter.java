package com.vasylieva.elective.util.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Set;

@Converter(autoApply = true)
public class SetJsonConverter implements AttributeConverter<Set<String>, String> {
    private final static ObjectMapper MAPPER = new ObjectMapper();
    private final static JavaType SET_TYPE = MAPPER.getTypeFactory().constructCollectionType(Set.class, String.class);


    @Override
    public String convertToDatabaseColumn(Set<String> meta) {
        try {
            return MAPPER.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Unexpected IOEx serializing set to json: " + meta, ex);
        }
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        try {
            return MAPPER.readValue(dbData, SET_TYPE);
        } catch (IOException ex) {
            throw new RuntimeException("Unexpected IOEx decoding json from database: " + dbData, ex);
        }
    }
}
