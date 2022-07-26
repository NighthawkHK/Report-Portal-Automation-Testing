package tests;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.PropertyReader;

public abstract class BaseTest {

    private static final String URL = PropertyReader.getProperty("url");

    @BeforeClass
    public void setUp() {
        WebDriver driver = WebDriverManager.getDriver();
        driver.navigate().to(URL);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriverManager.closeDriver();
    }
}
