package edu.reportportal.tests.ui;

import edu.reportportal.entities.User;
import edu.reportportal.listeners.TestNGListener;
import edu.reportportal.pages.LoginPage;
import edu.reportportal.tests.data.TestDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class LoginTest extends BaseTest {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user login functionality")
    @Test(description="Valid Login Scenario with correct username and password.",
            dataProvider = "users", dataProviderClass = TestDataProvider.class)
    public void verifyLoginIsSuccessful(User user) {
        Assert.assertEquals(new LoginPage().signIn(user).getPageTitle(), "ALL DASHBOARDS");
    }
}
