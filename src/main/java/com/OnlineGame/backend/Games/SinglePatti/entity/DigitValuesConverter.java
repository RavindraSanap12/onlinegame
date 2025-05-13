package com.OnlineGame.backend.Games.SinglePatti.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.HashMap;

@Converter
public class DigitValuesConverter implements AttributeConverter<HashMap<String, HashMap<String, Integer>>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference<HashMap<String, HashMap<String, Integer>>> typeRef =
            new TypeReference<HashMap<String, HashMap<String, Integer>>>() {};

    @Override
    public String convertToDatabaseColumn(HashMap<String, HashMap<String, Integer>> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert digit values to JSON", e);
        }
    }

    @Override
    public HashMap<String, HashMap<String, Integer>> convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.isEmpty()) {
                return new HashMap<>();
            }
            return objectMapper.readValue(dbData, typeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse digit values JSON", e);
        }
    }
}