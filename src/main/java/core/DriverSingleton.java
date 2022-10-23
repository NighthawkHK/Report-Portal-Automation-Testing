package core;

import listeners.DriverEventListener;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import utils.PropertyReader;

@Log4j2
public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                Browser browser = Browser.valueOf(PropertyReader.getProperty("browserType"));
                driver = new EventFiringDecorator(new DriverEventListener()).decorate(DriverFactory.initializeDriver(browser));
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
}
