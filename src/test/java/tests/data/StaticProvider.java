package tests.data;

import entities.User;
import org.testng.annotations.DataProvider;

public class StaticProvider {

    @DataProvider(name = "users")
    public static Object[][] testUsers() {
        return new Object[][] {
                {new User("default", "1q2w3e")}
        };
    }
}
