package core;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

@Log4j2
public class WebDriverEventHandler implements WebDriverListener {

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        log.info("Looking for web element: {}", locator);
    }

    @Override
    public void beforeTo(WebDriver.Navigation navigation, String url) {
        log.info("Navigating to {}", url);
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        log.info("Successfully sent keys {} for web element {}", keysToSend, element);
    }

    @Override
    public void afterClick(WebElement element) {
        log.info("Successfully clicked: {}", element);
    }

    @Override
    public void beforeQuit(WebDriver driver) {
        log.info("Trying to shut down driver: {}", driver);
    }

    @Override
    public void afterQuit(WebDriver driver) {
        log.info("Driver has successfully turned off");
    }
}
