package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.AllPages;
import utils.PropertyReader;
import utils.WebDriverManager;

public abstract class BaseTest {

    protected AllPages allPages;
    private final PropertyReader configFileReader = new PropertyReader();

    @BeforeClass
    public void setUp() {
        WebDriver driver = WebDriverManager.getDriver();
        allPages = new AllPages(driver);
        driver.navigate().to(configFileReader.getApplicationUrl());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriverManager.closeDriver();
    }
}
