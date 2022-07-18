package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public WebDriverWait getExplicitWaiter(final long secondsToWait) {
        return new WebDriverWait(this.driver, Duration.ofSeconds(secondsToWait));
    }
}