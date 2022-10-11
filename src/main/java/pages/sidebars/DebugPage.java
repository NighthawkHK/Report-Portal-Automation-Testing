package pages.sidebars;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.BasePage;

public class DebugPage extends BasePage {

    private static final String COLUMN_LOCATOR = "//span[contains(@class, 'title-full') and text()='%s']";

    @Step("Is column displayed with name '{0}'")
    public boolean isColumnDisplayed(String columnName) {
        return findElementByLocator(By.xpath(String.format(COLUMN_LOCATOR, columnName))).isDisplayed();
    }
}
