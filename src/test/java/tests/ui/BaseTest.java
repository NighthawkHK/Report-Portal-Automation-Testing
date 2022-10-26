package tests.ui;

import core.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.PropertyReader;

public abstract class BaseTest {

    private static final String URL = PropertyReader.getProperty("url");

    @BeforeClass
    public void setUp() {
        WebDriver driver = DriverSingleton.getDriver();
        driver.navigate().to(URL);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}
