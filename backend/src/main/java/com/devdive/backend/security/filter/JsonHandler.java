package com.devdive.backend.security.filter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonHandler {

    String getValue(String jsonString, String key) throws IOException {

        if (jsonString.length() == 0) {
            throw new JsonParseException("");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> jsonMap = objectMapper.readValue(jsonString, new TypeReference<Map<String, String>>() {
        });
        return jsonMap.get(key);

    }
}
