package com.home.service;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * @author ashar61
 */
public class JacksonUtil {
    private static final ObjectMapper oMapper = new ObjectMapper();

    public static String toJson(Object o) throws IOException {
        return oMapper.writeValueAsString(o);
    }

    public static <T> T toObject(String str, Class<T> type) throws IOException {
        return oMapper.readValue(str, type);
    }
}
