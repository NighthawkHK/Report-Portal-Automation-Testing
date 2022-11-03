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

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd.MM.yyyy HH-mm-ss");
    private static final String ROOT_FOLDER = "./target/screenshots-failure/";
    private static final String FILE_EXTENSION = ".png";

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
            copyFileToDestination(srcFile, ROOT_FOLDER + DATE_FORMATTER.format(new Date()) + FILE_EXTENSION);
        } catch (IOException e) {
            log.error("Exception occurred while copying file: ", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test Skipped: {}", result.getName());
    }

    private void copyFileToDestination(File sourceFile, String pathName) throws IOException {
        FileUtils.copyFile(sourceFile, new File(pathName));
    }
}
