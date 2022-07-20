package tests;

import entities.User;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @Test(dataProvider = "users", dataProviderClass = tests.data.StaticProvider.class)
    public void verifyLoginIsSuccessful(User user) {
        Assert.assertEquals(allPages.getLoginPage()
                .signIn(user)
                .getPageTitle(), "ALL DASHBOARDS");
    }
}
