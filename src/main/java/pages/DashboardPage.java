package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ActionHandler;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//span[text()='Add new widget']")
    private WebElement newWidgetButton;

    private static final String WIDGET_HEADER_LOCATOR = "//div[text()='%s']";
    private static final String WIDGET_RESIZABLE_ICON_LOCATOR = "//div[text()='%s']//ancestor::div[contains(@class, 'widgetsGrid')]//span[contains(@class, 'resizable-handle')]";

    @Step
    public DashboardPage moveWidgetByOffset(String widgetName, int xOffset, int yOffset) {
        ActionHandler.moveElementByOffset(findElementByLocator(By.xpath(String.format(WIDGET_HEADER_LOCATOR, widgetName))),
                xOffset, yOffset);
        return this;
    }

    @Step
    public DashboardPage resizeWidget(String widgetName, int xOffset, int yOffset) {
        ActionHandler.resizeElement(findElementByLocator(By.xpath(String.format(WIDGET_RESIZABLE_ICON_LOCATOR, widgetName))),
                xOffset, yOffset);
        return this;
    }

    @Step
    public Dimension getWidgetSize(String widgetName) {
        return findElementByLocator(By.xpath(String.format(WIDGET_HEADER_LOCATOR, widgetName))).getSize();
    }

    @Step
    public Point getWidgetLocation(String widgetName) {
        return findElementByLocator(By.xpath(String.format(WIDGET_HEADER_LOCATOR, widgetName))).getLocation();
    }

    @Step
    public WidgetPage clickAddNewWidget() {
        newWidgetButton.click();
        return new WidgetPage();
    }

    @Step
    public boolean isWidgetDisplayed(String widgetName) {
        return findElementByLocator(By.xpath(String.format(WIDGET_HEADER_LOCATOR, widgetName))).isDisplayed();
    }
}
