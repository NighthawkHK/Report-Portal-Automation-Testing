package hooks;

import core.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Log4j2
public class ScreenshotHook {

    @After
    public void takeScreenshotIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("Test failed: " + scenario.getName());
            byte[] screenshot = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }
}
