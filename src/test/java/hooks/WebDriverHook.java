package hooks;

import core.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

@Log4j2
public class WebDriverHook {

    private static final String URL = PropertyReader.getProperty("url");

    @Before(order = 1)
    public void onTestStart(Scenario scenario) {
        log.info("Test started: " + scenario.getName());
    }

    @Before(order = 2)
    public void setup() {
        WebDriver driver = WebDriverManager.getDriver();
        driver.navigate().to(URL);
    }

    @After(order = 1)
    public void onTestFinish(Scenario scenario) {
        log.info("Test finished with result: " + scenario.getName() + " - " + scenario.getStatus());
    }

    @After(order = 2)
    public void tearDown() {
        WebDriverManager.closeDriver();
    }
}
