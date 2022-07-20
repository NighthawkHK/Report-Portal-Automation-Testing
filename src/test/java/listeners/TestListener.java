package listeners;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH-mm-ss");

    public void onTestStart(ITestResult result) {
        logger.info("Starting Test: " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("Test Passed: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        logger.info("Test Failed: " + result.getName());
        File srcFile = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("./screenshots-failure/" + dateFormatter.format(new Date()) + ".png"));
        } catch (IOException e) {
            logger.error("Exception occurred while copying file: ", e);
        }
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("Test Skipped: " + result.getName());
    }
}
