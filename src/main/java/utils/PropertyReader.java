package utils;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertyReader {

    private static final String FILE_PATH = "src/main/resources/application.properties";
    private static final Properties CONFIG_PROPERTIES = readConfigFile();

    private PropertyReader() {
        throw new IllegalStateException("This is utility class.");
    }

    private static Properties readConfigFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            Properties properties = new Properties();
            properties.load(reader);
            return properties;
        } catch (FileNotFoundException e) {
            log.error("Configuration file was not found at " + FILE_PATH, e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("Something went wrong while reading file " + FILE_PATH, e);
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(final String propertyName) {
        String property = CONFIG_PROPERTIES.getProperty(propertyName);
        if (property != null) {
            return property;
        } else {
            throw new RuntimeException(String.format("Property with name '%s' is not specified in the configuration file.", propertyName));
        }
    }
}
