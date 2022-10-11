package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",
        glue = {"steps", "hooks"},
        plugin = {"pretty", "html:target/cucumber-reports/results.html"},
        monochrome = true)
public class TestRunner {
}
