package tests;

import core.WebDriverManager;
import entities.User;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    private static final LoginPage loginPage = new LoginPage(WebDriverManager.getDriver());

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user login functionality")
    @Test(description="Valid Login Scenario with correct username and password.",
            dataProvider = "users", dataProviderClass = tests.data.StaticProvider.class)
    public void verifyLoginIsSuccessful(User user) {
        Assert.assertEquals(loginPage.signIn(user).getPageTitle(), "ALL DASHBOARDS");
    }
}
