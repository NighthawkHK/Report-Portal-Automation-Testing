package edu.reportportal.core;

import edu.reportportal.listeners.DriverEventListener;
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
        switch (DriverConfig.HOST) {
            case "LOCAL":
                driver = new LocalDriverImpl(DriverConfig.BROWSER).createDriver();
                break;
            case "REMOTE":
                driver = new RemoteDriverImpl().createDriver();
                break;
            default:
                log.error("Unexpected host parameter: {}", DriverConfig.HOST);
                throw new IllegalArgumentException();
        }
        setupDriverOptions();
        decorateDriver();
    }

    private static void setupDriverOptions() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DriverConfig.WAIT_IMPLICITLY));
    }

    private static void decorateDriver() {
        driver = new EventFiringDecorator(new DriverEventListener()).decorate(driver);
    }
}
