package com.zhenhui.pandect.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public final class JsonUtils {

    private final static ObjectMapper DEFAULT_MAPPER = new ObjectMapper();

    static {
        DEFAULT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DEFAULT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        DEFAULT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        DEFAULT_MAPPER.setVisibility(DEFAULT_MAPPER.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
    }

    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        try {
            return DEFAULT_MAPPER.readValue(jsonString, clazz);
        } catch (Exception e) {
            log.warn(String.format("JsonUtils.fromJsonString jsonString = %s exception", jsonString), e);
        }

        return null;
    }

    public static <T> T fromJsonString(String jsonString, TypeReference<T> typeReference) {
        try {
            return DEFAULT_MAPPER.readValue(jsonString, typeReference);
        } catch (Exception e) {
            log.warn(String.format("JsonUtils.fromJsonString jsonString = %s exception", jsonString), e);
        }

        return null;
    }

    public static String toJsonString(Object object) {
        try {
            return DEFAULT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            log.warn("JsonUtils.toJsonString exception", e);
        }

        return null;
    }

    public static <T extends Map> T toMap(String jsonString) {
        return (T) fromJsonString(jsonString, Map.class);
    }


}
