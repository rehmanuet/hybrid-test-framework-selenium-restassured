package common.utils;

import common.reporting.ExtentTestManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class BaseTest {


    public static String BASE_URL;
    protected ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    public static String generateRandomString(String base) {
        return base + UUID.randomUUID().toString().substring(0, 6);
    }

    public static Long generateRandomNumericString(int digits) {
        if (digits <= 0 || digits > 18) {
            throw new IllegalArgumentException("Digits must be between 1 and 18");
        }

        long min = (long) Math.pow(10, digits - 1);
        long max = (long) Math.pow(10, digits) - 1;

        return ThreadLocalRandom.current().nextLong(min, max + 1);
    }

    @AfterSuite(alwaysRun = true)
    public void endTestSuite() {
        ExtentTestManager.flush();
    }

    @Parameters({"baseURI"})
    @BeforeSuite(alwaysRun = true)
    public void startTestSuite(String baseURI) {
        BASE_URL = baseURI;
    }

    protected void skipRetryAndSoftAssert() {
        softAssert.get().assertAll();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTestMethod() {
        this.softAssert.set(new SoftAssert());
    }
}
