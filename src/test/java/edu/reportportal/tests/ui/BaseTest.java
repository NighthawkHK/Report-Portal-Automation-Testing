package edu.reportportal.tests.ui;

import edu.reportportal.core.DriverSingleton;
import edu.reportportal.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    private static final String URL = PropertyReader.getProperty("app.base.url");

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
