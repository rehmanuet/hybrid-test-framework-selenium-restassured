package common.reporting;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.utils.Constants;
import common.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


/**
 * Standard TestNG Listener Class
 */
@Slf4j
public class TestListener implements ITestListener {

    private static synchronized String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    public void onStart(ITestContext iTestContext) {
        log.info(FileUtils.getPropertyValue(Constants.MESSAGES_PROPERTIES_PATH,
                Constants.ON_START_MESSAGE) + iTestContext.getName());
    }

    public void onFinish(ITestContext iTestContext) {
        log.info(FileUtils.getPropertyValue(Constants.MESSAGES_PROPERTIES_PATH,
                Constants.ON_FINISH_MESSAGE) + iTestContext.getName());
        ExtentTestManager.endTest();
        ExtentTestManager.flush();
    }

    public void onTestStart(ITestResult iTestResult) {
        log.info(FileUtils.getPropertyValue(Constants.MESSAGES_PROPERTIES_PATH,
                Constants.ON_TEST_START_MESSAGE) + getTestMethodName(iTestResult) + " start");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.info(FileUtils.getPropertyValue(Constants.MESSAGES_PROPERTIES_PATH,
                Constants.ON_TEST_SUCCESS_MESSAGE) + getTestMethodName(iTestResult) + " succeed");

        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(LogStatus.PASS, "Test passed");
        } else {
            log.error("ExtentTestManager.getTest() returned null in onTestSuccess!");
        }
    }

    public void onTestFailure(ITestResult iTestResult) {
        log.info(FileUtils.getPropertyValue(Constants.MESSAGES_PROPERTIES_PATH,
                Constants.ON_TEST_FAILURE_MESSAGE) + getTestMethodName(iTestResult) + " failed");

        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(LogStatus.FAIL, "Test failed: " + iTestResult.getThrowable());
        } else {
            log.error("ExtentTestManager.getTest() returned null in onTestFailure!");
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {
        log.info(FileUtils.getPropertyValue(Constants.MESSAGES_PROPERTIES_PATH,
                Constants.ON_TEST_SKIPPED_MESSAGE) + getTestMethodName(iTestResult) + " skipped");

        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(LogStatus.SKIP, "Test skipped");
        } else {
            log.error("ExtentTestManager.getTest() returned null in onTestSkipped!");
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info(FileUtils.getPropertyValue(Constants.MESSAGES_PROPERTIES_PATH,
                Constants.ON_SUCCESS_RATIO_MESSAGE) + getTestMethodName(iTestResult));
    }
}