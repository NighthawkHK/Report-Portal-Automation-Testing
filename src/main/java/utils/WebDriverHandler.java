package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverHandler {

    private static WebDriver driver;
    private static PropertyReader propertyReader;

    private WebDriverHandler() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            propertyReader = new PropertyReader();
            WebDriverType driverType = WebDriverType.valueOf(propertyReader.getBrowserType());
            driver = DriverFactory.initializeWebDriver(driverType);
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

        private static WebDriver initializeWebDriver(WebDriverType driverType) {
            switch (driverType) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case EDGE:
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new RuntimeException("Unrecognized driver type: " + driverType);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(propertyReader.getImplicitlyWait()));
            return driver;
        }
    }

    private enum WebDriverType {
        CHROME, EDGE, FIREFOX
    }
}
