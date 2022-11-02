package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JsonParser {

    private JsonParser() {
        throw new IllegalStateException("This is utility class.");
    }

    private static final ObjectMapper OBJECT_MAPPER = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        return new ObjectMapper();
    }

    public static <T> T readSource(final File source, final Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(source, clazz);
        } catch (IOException e) {
            log.error("An error occurred while deserializing data from {}", source);
            throw new RuntimeException(e);
        }
    }
}