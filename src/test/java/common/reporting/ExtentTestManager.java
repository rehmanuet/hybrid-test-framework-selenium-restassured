package common.reporting;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Standard class for Extent Reports Manager
 */
public class ExtentTestManager {

    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final ExtentReports extent = ExtentManager.getReporter();

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTest.set(test);
        return test;
    }

    public static void endTest() {
        ExtentTest test = extentTest.get();
        if (test != null) {
            extent.endTest(test);
        }
    }

    public static void flush() {
        extent.flush();
    }
}