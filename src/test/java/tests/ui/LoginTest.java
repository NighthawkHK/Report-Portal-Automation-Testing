package tests.ui;

import entities.User;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.data.StaticDataProvider;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user login functionality")
    @Test(description="Valid Login Scenario with correct username and password.",
            dataProvider = "users", dataProviderClass = StaticDataProvider.class)
    public void verifyLoginIsSuccessful(User user) {
        Assert.assertEquals(new LoginPage().signIn(user).getPageTitle(), "ALL DASHBOARDS");
    }
}
