import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.AllPages;
import utils.PropertyReader;
import utils.WebDriverManager;

public abstract class BaseTest {

    protected AllPages allPages;

    @BeforeClass
    public void setUp() {
        WebDriver driver = WebDriverManager.getDriver();
        allPages = new AllPages(driver);
        PropertyReader configFileReader = new PropertyReader();
        String url = configFileReader.getApplicationUrl();
        driver.navigate().to(url);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        WebDriverManager.closeDriver();
    }
}
