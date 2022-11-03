package edu.reportportal.tests.listeners;

import edu.reportportal.core.DriverSingleton;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TestNGListener implements ITestListener {

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH-mm-ss");

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting Test: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test Passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Test Failed: {}", result.getName());
        File srcFile = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("./target/screenshots-failure/" + dateFormatter.format(new Date()) + ".png"));
        } catch (IOException e) {
            log.error("Exception occurred while copying file: ", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test Skipped: {}", result.getName());
    }
}
