package pages;

import org.openqa.selenium.WebDriver;

public class AllPages extends BasePage {

    private final LoginPage loginPage;
    private final DashboardPage dashboardPage;

    public AllPages(WebDriver driver) {
        super(driver);
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public DashboardPage getDashboardPage() {
        return dashboardPage;
    }
}
