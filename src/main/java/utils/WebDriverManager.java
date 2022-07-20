package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.time.Duration;

public class WebDriverManager {

    private static WebDriver driver;

    private WebDriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverType driverType = WebDriverType.valueOf(PropertyReader.getProperty("browserType"));
            driver = new EventFiringDecorator(new WebDriverEventHandler()).decorate(DriverFactory.initializeWebDriver(driverType));
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null)
            driver.quit();
    }

    private static class DriverFactory {

        private DriverFactory() {
        }

        private static WebDriver initializeWebDriver(final WebDriverType driverType) {
            switch (driverType) {
                case CHROME:
                    io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case EDGE:
                    io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new RuntimeException("Unrecognized driver type: " + driverType);
            }
            driver.manage().window().maximize();
            long implicitlyWait = Long.parseLong(PropertyReader.getProperty("implicitlyWait"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
            return driver;
        }
    }

    private enum WebDriverType {
        CHROME, EDGE, FIREFOX
    }
}
