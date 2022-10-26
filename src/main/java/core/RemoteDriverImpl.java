package core;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.PropertyReader;

import java.net.MalformedURLException;
import java.net.URL;

@Log4j2
class RemoteDriverImpl implements DriverFactory {

    @Override
    public WebDriver createDriver() {
        WebDriver webDriver;
        try {
            URL remoteUrl = new URL(PropertyReader.getProperty("sauceLabsUrl"));
            webDriver = new RemoteWebDriver(remoteUrl, getCapabilities());
        } catch (MalformedURLException e) {
            log.error(e);
            throw new RuntimeException();
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
