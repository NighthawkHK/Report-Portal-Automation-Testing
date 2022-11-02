package core;

import listeners.DriverEventListener;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.time.Duration;

@Slf4j
public final class DriverSingleton {

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
                log.error("Unexpected host parameter: {}", DriverConfig.HOST);
                throw new IllegalArgumentException();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
        driver = new EventFiringDecorator(new DriverEventListener()).decorate(driver);
    }
}
