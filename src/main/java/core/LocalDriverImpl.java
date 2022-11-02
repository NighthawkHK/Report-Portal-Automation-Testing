package core;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Slf4j
class LocalDriverImpl implements DriverFactory {

    private final Browser browser;

    public LocalDriverImpl(Browser browser) {
        this.browser = browser;
    }

    @Override
    public WebDriver createDriver() {
        WebDriver webDriver;
        switch (browser) {
            case CHROME:
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case FIREFOX:
                io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case EDGE:
                io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            default:
                log.error("Unrecognized browser: {}", browser);
                throw new IllegalArgumentException();
        }
        return webDriver;
    }
}
