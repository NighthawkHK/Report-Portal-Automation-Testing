package core;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import utils.PropertyReader;

import java.time.Duration;

@Log4j2
public class WebDriverManager {

    private static WebDriver driver;

    private WebDriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                WebDriverType driverType = WebDriverType.valueOf(PropertyReader.getProperty("browserType"));
                driver = new EventFiringDecorator(new WebDriverEventHandler()).decorate(DriverFactory.initializeWebDriver(driverType));
            } catch (IllegalArgumentException e) {
                log.error("Invalid value of enum " + PropertyReader.getProperty("browserType"), e);
                throw new RuntimeException(e);
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static class DriverFactory {

        private DriverFactory() {
        }

        private static WebDriver initializeWebDriver(final WebDriverType driverType) {
            WebDriver webDriver;
            switch (driverType) {
                case CHROME:
                    io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
                    break;
                case FIREFOX:
                    io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    break;
                case EDGE:
                    io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unrecognized driver type: " + driverType);
            }
            webDriver.manage().window().maximize();
            long implicitlyWait = Long.parseLong(PropertyReader.getProperty("implicitlyWait"));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
            return webDriver;
        }
    }

    private enum WebDriverType {
        CHROME, EDGE, FIREFOX
    }
}
