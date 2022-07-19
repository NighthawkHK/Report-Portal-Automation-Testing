package tests;

import entities.User;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @DataProvider(name = "users")
    public Object[][] dataProv() {
        return new Object[][] {
                {new User("default", "1q2w3e")}
        };
    }

    @Test(dataProvider = "users")
    public void verifyLoginIsSuccessful(User user) {
        Assert.assertEquals(allPages.getLoginPage()
                .signIn(user)
                .getPageTitle(), "ALL DASHBOARDS");
    }
}
