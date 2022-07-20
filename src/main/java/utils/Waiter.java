package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

    private Waiter() { }

    public static WebDriverWait getExplicitWaiter(final WebDriver driver, final long secondsToWait) {
        return new WebDriverWait(driver, Duration.ofSeconds(secondsToWait));
    }
}
