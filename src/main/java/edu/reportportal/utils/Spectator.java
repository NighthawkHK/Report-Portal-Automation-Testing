package edu.reportportal.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class Spectator {

    private static final long DEFAULT_EXPLICIT_WAITER = Long.parseLong(PropertyReader.getProperty("explicitlyWait"));

    private Spectator() {
        throw new IllegalStateException("This is Utility class.");
    }

    public static WebDriverWait getWebDriverWait(final WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_EXPLICIT_WAITER), Duration.ofMillis(500));
    }

    public static void waitForPageToLoad(final WebDriver driver) {
        Function<WebDriver, Boolean> loadCondition =
                x -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        getWebDriverWait(driver).until(loadCondition);
    }

    public static void waitForAjaxProcessed(final WebDriver driver) {
        Function<WebDriver, Boolean> loadCondition =
                x -> ((JavascriptExecutor) driver).executeScript("return jQuery.active").equals("0");
        getWebDriverWait(driver).until(loadCondition);
    }
}
