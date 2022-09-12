package tests.data;

import entities.User;
import org.testng.annotations.DataProvider;
import utils.JsonReader;

public class StaticDataProvider {

    @DataProvider(name = "users")
    public static Object[][] testUsers() {
        return new Object[][] {
                {new User("default", "1q2w3e")}
        };
    }

    @DataProvider(name = "launches")
    public static Object[][] launchesResults() {
        return new Object[][] {
                {JsonReader.getLaunchResults(0).get("launchNumber"), JsonReader.getLaunchResults(0).get("results")},
                {JsonReader.getLaunchResults(1).get("launchNumber"), JsonReader.getLaunchResults(1).get("results")},
                {JsonReader.getLaunchResults(2).get("launchNumber"), JsonReader.getLaunchResults(2).get("results")},
                {JsonReader.getLaunchResults(3).get("launchNumber"), JsonReader.getLaunchResults(3).get("results")},
                {JsonReader.getLaunchResults(4).get("launchNumber"), JsonReader.getLaunchResults(4).get("results")}
        };
    }
}
