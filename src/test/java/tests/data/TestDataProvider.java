package tests.data;

import entities.User;
import entities.models.Launch;
import org.testng.annotations.DataProvider;
import utils.JsonParser;

import java.io.File;

public class TestDataProvider {

    @DataProvider(name = "users")
    public static Object[][] testUsers() {
        return new Object[][] {
                {new User("default", "1q2w3e")}
        };
    }

    @DataProvider(name = "launches")
    public static Object[][] launchesResults() {
        String filepath = "src/test/java/tests/data/jsons/launches_result.json";
        Launch[] launches = JsonParser.readSource(new File(filepath), Launch[].class);
        Object[][] objArray = new Object[launches.length][];
        for (int i = 0; i < launches.length; i++) {
            objArray[i] = new Object[1];
            objArray[i][0] = launches[i];
        }
        return objArray;
    }
}
