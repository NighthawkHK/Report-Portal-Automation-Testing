package utils;

import java.io.*;
import java.util.Properties;

public class PropertyReader {

    private final Properties properties;

    public PropertyReader() {
        String propertyFilePath = "src/main/resources/configs/webdriver.properties";
        try (BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath))) {
            properties = new Properties();
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration properties was not found at " + propertyFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBrowserType() {
        String browserType = properties.getProperty("browserType");
        if (browserType != null) {
            return browserType;
        } else {
            throw new RuntimeException("browserType is not specified in property file.");
        }
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (implicitlyWait != null) {
            return Long.parseLong(implicitlyWait);
        } else {
            throw new RuntimeException("implicitlyWait is not specified in property file.");
        }
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url != null) {
            return url;
        } else {
            throw new RuntimeException("url is not specified in property file.");
        }
    }
}
