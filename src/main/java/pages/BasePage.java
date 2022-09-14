package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @Step
    protected void sendKeysToWebElement(final WebElement element, final CharSequence... keysToSend) {
        element.clear();
        element.sendKeys(keysToSend);
    }

    @Step
    protected WebElement findElementByLocator(final By locator) {
        return driver.findElement(locator);
    }

    @Step
    protected List<WebElement> findElementsByLocator(final By locator) {
        return driver.findElements(locator);
    }
}