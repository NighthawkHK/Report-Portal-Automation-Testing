package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final Properties configProperties = readConfigFile();

    private PropertyReader() { }

    private static Properties readConfigFile() {
        String propertyFilePath = "src/main/resources/webdriver.properties";
        try (BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath))) {
            Properties properties = new Properties();
            properties.load(reader);
            return properties;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Configuration file was not found at " + propertyFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(final String propertyName) {
        String property = configProperties.getProperty(propertyName);
        if (property != null) {
            return property;
        } else {
            throw new RuntimeException(String.format("Property with name '%s' is not specified in the configuration file.", propertyName));
        }
    }
}
