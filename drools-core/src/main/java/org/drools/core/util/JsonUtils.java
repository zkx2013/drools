package org.drools.core.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JsonUtils {



    //each thread has its own ObjectMapper instance
    private static ThreadLocal<ObjectMapper> objMapperLocal = ThreadLocal.withInitial(() -> {
        ObjectMapper mapper = new ObjectMapper();
        mapper.getFactory().disable(JsonFactory.Feature.INTERN_FIELD_NAMES);

        return mapper;
    });

    public static String toJSON(Object value) {
        String result = null;
        try {
            result = objMapperLocal.get().writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Fix null string
        if ("null".equals(result)) {
            result = null;
        }
        return result;
    }

    public static <T> T toT(String jsonString, Class<T> clazz) {
        try {
            return objMapperLocal.get().readValue(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static <T> T toT(String jsonString, TypeReference valueTypeRef) {
        try {
            return objMapperLocal.get().readValue(jsonString, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> toTList(String jsonString, Class<T> clazz) {
        try {
            return objMapperLocal.get().readValue(jsonString,
                    TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String jsonString) {
        return toT(jsonString, Map.class);
    }

    public static String prettyPrint(Object value) {
        String result = null;
        try {
            result = objMapperLocal.get().writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Fix null string
        if ("null".equals(result)) {
            result = null;
        }
        return result;
    }





}
