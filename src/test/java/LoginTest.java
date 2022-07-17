import entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;

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
