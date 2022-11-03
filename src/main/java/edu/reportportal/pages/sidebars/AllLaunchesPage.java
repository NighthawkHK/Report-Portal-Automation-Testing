package edu.reportportal.pages.sidebars;

import edu.reportportal.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllLaunchesPage extends BasePage {

    private static final String CELL_LOCATOR = "//span[text()='%d']//ancestor::div[contains(@class, 'grid-row')]//div[contains(@class, '%s')]//a";

    @Step("Get value for row with launch number {0} and column {1}")
    public int getValueFromSpecificColumn(int launchNumber, ColumnName columnName) {
        List<WebElement> elements = findElementsByLocator(By.xpath(String.format(CELL_LOCATOR, launchNumber, columnName.getName())));
        if (!elements.isEmpty())
            return Integer.parseInt(elements.get(0).getText());
        else return 0;
    }

    public enum ColumnName {

        TOTAL("total-col"),
        PASSED("passed-col"),
        FAILED("failed-col"),
        SKIPPED("skipped-col"),
        PRODUCT_BUG("pb-col"),
        AUTO_BUG("ab-col"),
        SYSTEM_ISSUE("si-col"),
        TO_INVESTIGATE("ti-col");

        private final String name;

        ColumnName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
