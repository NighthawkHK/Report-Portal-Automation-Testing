package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.AllPages;
import utils.PropertyReader;
import utils.WebDriverManager;

public abstract class BaseTest {

    protected AllPages allPages;
    private static final String URL = PropertyReader.getProperty("url");

    @BeforeClass
    public void setUp() {
        WebDriver driver = WebDriverManager.getDriver();
        allPages = new AllPages(driver);
        driver.navigate().to(URL);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriverManager.closeDriver();
    }
}
