package utils;

import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Log4j2
public class JsonReader {

    private JsonReader() {
        throw new IllegalStateException("This is utility class.");
    }

    private static final String FILE_PATH = "src/test/java/tests/data/jsons/launches_result.json";

    public static Map<String, Object> getLaunchResults(final int arrayIndex) {
        JSONObject obj = new JSONObject(readFileAsString());
        return obj.getJSONArray("launches")
                .getJSONObject(arrayIndex)
                .toMap();
    }

    private static String readFileAsString() {
        try {
            return new String(Files.readAllBytes(Paths.get(FILE_PATH)));
        } catch (IOException e) {
            log.error("Something went wrong while reading JSON file as string " + FILE_PATH, e);
            throw new RuntimeException(e);
        }
    }
}