package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    public void onTestStart(ITestResult result) {
        logger.info("Starting new Test: " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("Test Succeeded: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        logger.info("Test Failed: " + result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("Test Skipped: " + result.getName());
    }
}
