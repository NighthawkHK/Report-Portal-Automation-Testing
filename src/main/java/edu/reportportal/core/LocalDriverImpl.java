package edu.reportportal.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;

@Slf4j
class LocalDriverImpl implements DriverFactory {

    @Override
    public WebDriver createDriver(Browser browser) {
        WebDriver webDriver;
        MutableCapabilities capabilities = Config.getDefaultCapabilities(browser);
        switch (browser) {
            case CHROME:
                webDriver = WebDriverManager.chromedriver().capabilities(capabilities).create();
                break;
            case FIREFOX:
                webDriver = WebDriverManager.firefoxdriver().capabilities(capabilities).create();
                break;
            case EDGE:
                webDriver = WebDriverManager.edgedriver().capabilities(capabilities).create();
                break;
            default:
                log.error("Unrecognized browser type: {}", browser);
                throw new IllegalArgumentException();
        }
        return webDriver;
    }
}
