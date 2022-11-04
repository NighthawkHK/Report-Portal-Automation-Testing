package edu.reportportal.core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
class RemoteDriverImpl implements DriverFactory {

    @Override
    public WebDriver createDriver(Browser browser) {
        WebDriver webDriver;
        try {
            URL remoteUrl = new URL(Config.SAUCE_URL);
            webDriver = new RemoteWebDriver(remoteUrl, Config.getSauceLabsCapabilities(browser));
        } catch (MalformedURLException e) {
            log.error("Something went wrong: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return webDriver;
    }

//    private MutableCapabilities getCapabilities(Browser browser) {
//        MutableCapabilities capabilities = new MutableCapabilities();
//        capabilities.setCapability("browser.name", browser.name());
//        capabilities.setCapability("browserVersion", "latest");
//        capabilities.setCapability("platformName", "Windows 11");
//        capabilities.setCapability("sauce:options", getSauceOptions());
//        return capabilities;
//    }
//
//    private MutableCapabilities getSauceOptions() {
//        MutableCapabilities sauceOptions = new MutableCapabilities();
//        sauceOptions.setCapability("username", Config.SAUCE_USER);
//        sauceOptions.setCapability("accessKey", Config.SAUCE_KEY);
//        sauceOptions.setCapability("name", Config.SAUCE_NAME);
//        sauceOptions.setCapability("tunnelName", Config.SAUCE_TUNNEL);
//        return sauceOptions;
//    }
}
