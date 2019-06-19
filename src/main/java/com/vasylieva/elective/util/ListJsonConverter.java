package com.vasylieva.elective.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.List;

@Converter(autoApply = true)
public class ListJsonConverter implements AttributeConverter<List<String>, String> {
    private final static ObjectMapper MAPPER = new ObjectMapper();
    private final static JavaType LIST_TYPE = MAPPER.getTypeFactory().constructCollectionType(List.class, String.class);


    @Override
    public String convertToDatabaseColumn(List<String> meta) {
        try {
            return MAPPER.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Unexpected IOEx serializing set to json: " + meta, ex);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        try {
            return MAPPER.readValue(dbData, LIST_TYPE);
        } catch (IOException ex) {
            throw new RuntimeException("Unexpected IOEx decoding json from database: " + dbData, ex);
        }
    }
}
