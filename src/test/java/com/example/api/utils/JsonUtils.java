package com.example.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtils {
    private static final ObjectWriter prettyPrinter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    public static String prettyPrint(String jsonString) {
        try {
            Object json = new ObjectMapper().readValue(jsonString, Object.class);
            return prettyPrinter.writeValueAsString(json);
        } catch (Exception e) {
            return jsonString;
        }
    }
}

