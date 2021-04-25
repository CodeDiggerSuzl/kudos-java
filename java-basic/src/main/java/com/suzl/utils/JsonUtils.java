package com.suzl.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author suzailong
 * @date 2021/3/11 4:19 下午
 */

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> void registerModule(Class<T> clazz) {
        SimpleModule module = new SimpleModule();
        module.addSerializer(clazz, new JsonSerializer<T>() {
            @Override
            public void serialize(T arg0, JsonGenerator jgen,
                                  SerializerProvider arg2) throws IOException {
                jgen.writeString(clazz.getCanonicalName());
            }
        });
        objectMapper.registerModule(module);
    }

    public static <T> T readValue(String jsonStr, Class<T> clazz) throws IOException {
        return objectMapper.readValue(jsonStr, clazz);
    }

    public static <T> T readValue(String jsonStr, JavaType javaType) throws IOException {
        return objectMapper.readValue(jsonStr, javaType);
    }

    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String jsonStr, JavaType javaType) {
        try {
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String writeJsonStr(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static String writePrettyJsonStr(Object obj) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    public static void writeJsonStream(OutputStream out, Object obj) throws IOException {
        objectMapper.writeValue(out, obj);
    }

    public static <T> List<T> readListValue(String jsonStr, Class<T> clazz) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        return objectMapper.readValue(jsonStr, javaType);
    }

    public static <T> List<T> fromListValue(String jsonStr, Class<T> clazz) {
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> Map<K, V> readMapValue(String jsonStr, Class<K> kclazz, Class<V> vclazz) throws IOException {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(HashMap.class, kclazz, vclazz);
        return objectMapper.readValue(jsonStr, javaType);
    }

    public static ArrayNode readArray(String jsonStr) throws IOException {
        JsonNode node = objectMapper.readTree(jsonStr);
        if (node.isArray()) {
            return (ArrayNode) node;
        }
        return null;
    }

    public static JsonNode readNode(String jsonStr) throws IOException {
        return objectMapper.readTree(jsonStr);
    }

}
