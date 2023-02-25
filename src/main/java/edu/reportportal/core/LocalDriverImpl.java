package edu.reportportal.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
class LocalDriverImpl implements DriverFactory {

    @Override
    public WebDriver createDriver(Browser browser) {
        WebDriver webDriver;
        MutableCapabilities capabilities = Config.getDefaultCapabilities(browser);
        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setBrowserVersion("95").addArguments("--incognito");
                WebDriverManager.chromedriver().capabilities(capabilities).setup();
                webDriver = new ChromeDriver(chromeOptions);
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
