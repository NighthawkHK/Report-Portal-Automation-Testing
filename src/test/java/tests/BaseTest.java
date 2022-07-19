package tests;

import utils.WebDriverEventHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.AllPages;
import utils.PropertyReader;
import utils.WebDriverManager;

public abstract class BaseTest {

    protected AllPages allPages;

    @BeforeClass
    public void setUp() {
        WebDriver originalDriver = WebDriverManager.getDriver();
        WebDriverListener listener = new WebDriverEventHandler();
        WebDriver decoratedDriver = new EventFiringDecorator(listener).decorate(originalDriver);
        allPages = new AllPages(decoratedDriver);
        PropertyReader configFileReader = new PropertyReader();
        String url = configFileReader.getApplicationUrl();
        decoratedDriver.navigate().to(url);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriverManager.closeDriver();
    }
}
