package core;

import listeners.DriverEventListener;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import utils.PropertyReader;

import java.time.Duration;

@Log4j2
public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static void initializeDriver() {
        String host = PropertyReader.getProperty("host");
        Browser browser = Browser.valueOf(PropertyReader.getProperty("browserType"));
        long implicitlyWait = Long.parseLong(PropertyReader.getProperty("implicitlyWait"));
        switch (host) {
            case "LOCAL":
                driver = new LocalDriverImpl(browser).createDriver();
                break;
            case "REMOTE":
                driver = new RemoteDriverImpl().createDriver();
                break;
            default:
                throw new IllegalArgumentException("Undefined host");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
        driver = new EventFiringDecorator(new DriverEventListener()).decorate(driver);
    }
}
