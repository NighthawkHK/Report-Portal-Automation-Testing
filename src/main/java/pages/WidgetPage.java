package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WidgetPage extends BasePage {

    @FindBy(xpath = "//span[text()='Next step']")
    private WebElement nextStepButton;

    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addButton;

    @FindBy(xpath = "//span[contains(@class, 'filterName') and text()='DEMO_FILTER']")
    private WebElement demoFilterName;

    @FindBy(xpath = "//input[@placeholder='Enter widget name']")
    private WebElement widgetNameInput;

    private static final String WIDGET_TYPE_SELECTOR = "//div[text()='%s']";

    @Step
    public WidgetPage selectType(String widgetType) {
        findElementByLocator(By.xpath(String.format(WIDGET_TYPE_SELECTOR, widgetType))).click();
        return this;
    }

    @Step
    public WidgetPage chooseFilter() {
        demoFilterName.click();
        return this;
    }

    @Step
    public WidgetPage setWidgetName(String widgetName) {
        sendKeysToWebElement(widgetNameInput, widgetName);
        return this;
    }

    @Step
    public WidgetPage clickNextStepButton() {
        nextStepButton.click();
        return this;
    }

    @Step
    public DashboardPage clickAddButton() {
        addButton.click();
        return new DashboardPage();
    }
}
