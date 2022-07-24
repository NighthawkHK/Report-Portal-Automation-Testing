package listeners;

import core.WebDriverManager;
import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
public class TestListener implements ITestListener {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH-mm-ss");

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] takeScreenshotForAllure() {
        return ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public void onTestStart(ITestResult result) {
        log.info("Starting Test: " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        log.info("Test Passed: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        log.info("Test Failed: " + result.getName());
        File srcFile = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("./target/screenshots-failure/" + dateFormatter.format(new Date()) + ".png"));
        } catch (IOException e) {
            log.error("Exception occurred while copying file: ", e);
        }
        takeScreenshotForAllure();
    }

    public void onTestSkipped(ITestResult result) {
        log.info("Test Skipped: " + result.getName());
    }
}
