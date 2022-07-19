package tests;

import entities.User;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    private User testUser;

    @Test
    public void verifyLoginIsSuccessful() {
        testUser = new User("default", "1q2w3e");
        Assert.assertEquals(allPages.getLoginPage()
                .signIn(testUser)
                .getPageTitle(), "ALL DASHBOARDS");
    }
}
