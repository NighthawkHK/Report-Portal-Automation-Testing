package pages.sidebars;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//span[@title='All Dashboards']")
    private WebElement titlePage;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return titlePage.getText();
    }
}
