package com.coden.kantools.respronseBody;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class JsonWriter {
    private static ObjectMapper om = new ObjectMapper();
    private static JsonFactory jf = new JsonFactory();

    public static String toJson(Object pojo) {
        return toJson(pojo, false);
    }

    public static String toJson(Object pojo, boolean prettyPrint) {
        try {
            StringWriter sw = new StringWriter();
            JsonGenerator jg = jf.createGenerator(sw);

            if (prettyPrint) {
                jg.useDefaultPrettyPrinter();
            }
            om.writeValue(jg, pojo);
            return sw.toString();
        } catch (JsonParseException e) {
            throw new IllegalStateException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static void toJson(Object pojo, FileWriter fw, boolean prettyPrint) {
        try {
            JsonGenerator jg = jf.createGenerator(fw);
            if (prettyPrint) {
                jg.useDefaultPrettyPrinter();
            }
            om.writeValue(jg, pojo);
        } catch (JsonParseException e) {
            throw new IllegalStateException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

}