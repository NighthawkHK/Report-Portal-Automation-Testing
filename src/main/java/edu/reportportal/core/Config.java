package edu.reportportal.core;

import edu.reportportal.utils.PropertyReader;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

class Config {

    private Config() {}

    public static final String HOST = PropertyReader.getProperty("host");
    public static final Browser BROWSER_NAME = Browser.valueOf(PropertyReader.getProperty("browser.name"));
    public static final String BROWSER_VERSION = PropertyReader.getProperty("browser.version");
    public static final String SAUCE_USER = System.getenv("SAUCE_USER");
    public static final String SAUCE_KEY = System.getenv("SAUCE_ACCESS_KEY");
    public static final String SAUCE_URL = System.getenv("SAUCE_URL");
    public static final String SAUCE_TUNNEL = System.getenv("SAUCE_TUNNEL");
    public static final String SAUCE_NAME = System.getProperty("SAUCE_NAME", "SauceLab Integration run");
    public static final String PLATFORM = System.getProperty("PLATFORM_NAME", "Windows 10");

    public static MutableCapabilities getDefaultCapabilities(Browser browser) {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", BROWSER_NAME);
        capabilities.setCapability("browserVersion", BROWSER_VERSION);
        switch (browser) {
            case CHROME:
            case EDGE:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private");
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                break;
        }
        return capabilities;
    }

    public static MutableCapabilities getSauceLabsCapabilities(Browser browser) {
        MutableCapabilities sauceLabsCaps = getDefaultCapabilities(browser);
        sauceLabsCaps.setCapability("platformName", PLATFORM);

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", Config.SAUCE_USER);
        sauceOptions.setCapability("accessKey", Config.SAUCE_KEY);
        sauceOptions.setCapability("name", Config.SAUCE_NAME);
        sauceOptions.setCapability("tunnelName", Config.SAUCE_TUNNEL);
        sauceLabsCaps.setCapability("sauce:options", sauceOptions);
        return sauceLabsCaps;
    }
}
