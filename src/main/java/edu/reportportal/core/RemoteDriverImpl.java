package edu.reportportal.core;

import edu.reportportal.utils.PropertyReader;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
class RemoteDriverImpl implements DriverFactory {

    @Override
    public WebDriver createDriver() {
        WebDriver webDriver;
        try {
            URL remoteUrl = new URL(PropertyReader.getProperty("sauceLabsUrl"));
            webDriver = new RemoteWebDriver(remoteUrl, getCapabilities());
        } catch (MalformedURLException e) {
            log.error("Something went wrong: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return webDriver;
    }

    private MutableCapabilities getCapabilities() {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", PropertyReader.getProperty("browserType").toLowerCase());
        capabilities.setCapability("browserVersion", "latest");
        capabilities.setCapability("platformName", "Windows 11");
        capabilities.setCapability("sauce:options", getSauceOptions());

        return capabilities;
    }

    private MutableCapabilities getSauceOptions() {
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", PropertyReader.getProperty("username"));
        sauceOptions.setCapability("accessKey", PropertyReader.getProperty("accessKey"));
        sauceOptions.setCapability("name", PropertyReader.getProperty("name"));
        sauceOptions.setCapability("tunnelName", PropertyReader.getProperty("tunnelName"));

        return sauceOptions;
    }
}
