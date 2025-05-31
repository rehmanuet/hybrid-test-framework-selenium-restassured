package api.base;

import com.relevantcodes.extentreports.LogStatus;
import common.reporting.ExtentTestManager;
import common.reporting.TestListener;
import common.utils.BaseTest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Listeners;


/**
 * Implementation of the Base Class for the generic methods used for test cases
 *
 * @author Abdur.Rehman
 */

@Listeners(TestListener.class)
@Slf4j
public class BaseApiTest extends BaseTest {
    static {
        RestAssured.defaultParser = Parser.JSON;
    }

    public static void validateResponseStatusCode(Response response, String endpoint, int expectedCode) {
        int actualCode = response.getStatusCode();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Endpoint: " + endpoint + " Status Code: " + actualCode);
        if (actualCode != expectedCode) {
            ExtentTestManager.getTest().log(LogStatus.INFO, "Response:\n" + response.asPrettyString());
        }
        Assert.assertEquals(actualCode, expectedCode, "Response: status code NOT correct");
    }

    public static <T> T getResponsePath(Response response, String path) {
        T result = null;
        try {
            result = response.path(path);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return result;
    }

}
