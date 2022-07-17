package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait explicitWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        PropertyReader propertyReader = new PropertyReader();
        explicitWait = new WebDriverWait(this.driver, Duration.ofSeconds(propertyReader.getImplicitlyWait()));
    }

    public WebDriverWait getCustomExplicitWaiter(final long secondsToWait) {
        return new WebDriverWait(this.driver, Duration.ofSeconds(secondsToWait));
    }
}
