package pages.sidebars;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.DashboardPage;

public class AllDashboardsPage extends BasePage {

    @FindBy(xpath = "//span[@title='All Dashboards']")
    private WebElement titlePage;

    private static final String DASHBOARD_LOCATOR = "//div[contains(@class, 'dashboardTable')]//a[text()='%s']";

    public AllDashboardsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get page title")
    public String getPageTitle() {
        return titlePage.getText();
    }

    @Step("Select dashboard with name {0}")
    public DashboardPage selectDashboard(String dashboardName) {
        findElementByLocator(By.xpath(String.format(DASHBOARD_LOCATOR, dashboardName.toUpperCase()))).click();
        return new DashboardPage(driver);
    }
}
