package core;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.PropertyReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Log4j2
class WebDriverFactory {

    private WebDriverFactory() {
    }

    public static WebDriver initializeWebDriver(final BrowserType driverType) {
        WebDriver webDriver;
        switch (driverType) {
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
            case REMOTE:
                try {
                    URL remoteUrl = new URL(PropertyReader.getProperty("sauceLabsUrl"));
                    webDriver = new RemoteWebDriver(remoteUrl, getCapabilities());
                } catch (MalformedURLException e) {
                    log.error(e);
                    throw new RuntimeException();
                }
                break;
            default:
                log.error("Unrecognized driver type: " + driverType);
                throw new IllegalArgumentException();
        }
        webDriver.manage().window().maximize();
        long implicitlyWait = Long.parseLong(PropertyReader.getProperty("implicitlyWait"));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
        return webDriver;
    }

    private static MutableCapabilities getCapabilities() {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", PropertyReader.getProperty("browserType").toLowerCase());
        capabilities.setCapability("browserVersion", "latest");
        capabilities.setCapability("platformName", "Windows 11");
        capabilities.setCapability("sauce:options", getSauceOptions());

        return capabilities;
    }

    private static MutableCapabilities getSauceOptions() {
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", PropertyReader.getProperty("username"));
        sauceOptions.setCapability("accessKey", PropertyReader.getProperty("accessKey"));
        sauceOptions.setCapability("name", PropertyReader.getProperty("name"));
        sauceOptions.setCapability("tunnelName", PropertyReader.getProperty("tunnelName"));

        return sauceOptions;
    }
}
