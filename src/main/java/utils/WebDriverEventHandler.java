package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebDriverEventHandler implements WebDriverListener {

    private static final Logger logger = LogManager.getLogger(WebDriverEventHandler.class);

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        logger.info("Looking for web element: {}", locator);
    }

    @Override
    public void beforeTo(WebDriver.Navigation navigation, String url) {
        logger.info("Navigating to {}", url);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        logger.info("Successfully sent keys {} for web element {}", keysToSend, element);
    }

    @Override
    public void afterClick(WebElement element) {
        logger.info("Successfully clicked: {}", element);
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        logger.info("Trying to shut down driver: {}", driver);
    }

    @Override
    public void afterQuit(WebDriver driver) {
        logger.info("Driver has successfully turned off");
    }
}
