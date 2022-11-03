package edu.reportportal.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class PropertyReader {

    private static final String FILE_PATH = "src/main/resources/application.properties";
    private static final Properties APP_PROPERTIES = readConfigFile();

    private PropertyReader() {
        throw new IllegalStateException("This is utility class.");
    }

    private static Properties readConfigFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            return getApplicationProperties(reader);
        } catch (FileNotFoundException e) {
            log.error("Configuration file was not found at {}", FILE_PATH);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("Something went wrong while reading file {}", FILE_PATH);
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(final String propertyName) {
        String property = APP_PROPERTIES.getProperty(propertyName);
        if (property != null) {
            return property;
        } else {
            log.error("'{}' property is not specified in the configuration file", propertyName);
            throw new RuntimeException();
        }
    }

    private static Properties getApplicationProperties(BufferedReader reader) throws IOException {
        Properties properties = new Properties();
        properties.load(reader);
        return properties;
    }
}
