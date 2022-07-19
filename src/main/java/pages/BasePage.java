package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final Logger log = LogManager.getLogger(this.getClass());

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public WebDriverWait getExplicitWaiter(final long secondsToWait) {
        return new WebDriverWait(this.driver, Duration.ofSeconds(secondsToWait));
    }
}