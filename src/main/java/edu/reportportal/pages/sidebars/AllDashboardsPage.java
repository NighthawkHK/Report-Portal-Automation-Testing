package edu.reportportal.pages.sidebars;

import edu.reportportal.pages.BasePage;
import edu.reportportal.pages.DashboardPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AllDashboardsPage extends BasePage {

    @FindBy(xpath = "//span[@title='All Dashboards']")
    private WebElement titlePage;

    private static final String DASHBOARD_LOCATOR = "//div[contains(@class, 'dashboardTable')]//a[text()='%s']";

    @Step("Get page title")
    public String getPageTitle() {
        return titlePage.getText();
    }

    @Step("Select dashboard with name {0}")
    public DashboardPage selectDashboard(String dashboardName) {
        findElementByLocator(By.xpath(String.format(DASHBOARD_LOCATOR, dashboardName.toUpperCase()))).click();
        return new DashboardPage();
    }
}
