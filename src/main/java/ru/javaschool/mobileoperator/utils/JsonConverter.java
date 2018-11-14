package ru.javaschool.mobileoperator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonConverter {
    private static Logger logger = LogManager.getLogger(JsonConverter.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    private JsonConverter(){

    }

    public static String toJsonString(Object object){
        if(object == null){
            throw new IllegalArgumentException("Can not parse nullable object");
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("Parse error on " + object.toString() + Arrays.toString(e.getStackTrace()));
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static String toJsonString(List<Object> objects){
        if(objects == null){
            throw new IllegalArgumentException("Can not parse nullable object");
        }
        try {
            return objectMapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("Parse error on " + objects.toString() + Arrays.toString(e.getStackTrace()));
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
