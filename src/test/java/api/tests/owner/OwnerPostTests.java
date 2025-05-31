package api.tests.owner;

import api.base.BaseApiTest;
import api.endpoints.PetClinic;
import api.model.Error;
import api.model.Owner;
import com.relevantcodes.extentreports.LogStatus;
import common.reporting.ExtentTestManager;
import common.utils.TestDataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static common.utils.Constants.OWNER_ENDPOINT;
import static common.utils.Constants.OWNER_API_DATA;

public class OwnerPostTests extends BaseApiTest {


    @Test(groups = {"bug"})
    public void shouldCreateNewPetOwnerSuccessfully() throws IOException {
        ExtentTestManager.startTest("TC005 - Create New Pet Owner", "Verify successful creation of a new pet owner (Status 201)");
        Response response = postValidator("createNewOwner", 201);
        softAssert.get().assertNotNull(getResponsePath(response, "id"), "Owner ID should not be null");
        skipRetryAndSoftAssert();
    }

    @Test
    public void shouldFailWhenFirstNameMissing() throws IOException {
        ExtentTestManager.startTest("TC006 - Missing First Name Validation", "Verify 400 error when first name is missing");
        Response response = postValidator("createNewOwnerWithoutFN", 400);
        errorValidation(response, "firstName", "must not be empty");
        skipRetryAndSoftAssert();
    }


    @Test
    public void shouldFailWhenLastNameMissing() throws IOException {
        ExtentTestManager.startTest("TC007 - Missing Last Name Validation", "Verify 400 error when last name is missing");
        Response response = postValidator("createNewOwnerWithoutLN", 400);
        errorValidation(response, "lastName", "must not be empty");
        skipRetryAndSoftAssert();
    }

    @Test
    public void shouldFailWhenAddressMissing() throws IOException {
        ExtentTestManager.startTest("TC008 - Missing Address Validation", "Verify 400 error when address is missing");
        Response response = postValidator("createNewOwnerWithoutAddress", 400);
        errorValidation(response, "address", "must not be empty");
        skipRetryAndSoftAssert();
    }

    @Test
    public void shouldFailWhenCityMissing() throws IOException {
        ExtentTestManager.startTest("TC009 - Missing City Validation", "Verify 400 error when city is missing");
        Response response = postValidator("createNewOwnerWithoutCity", 400);
        errorValidation(response, "city", "must not be empty");
        skipRetryAndSoftAssert();
    }

    @Test
    public void shouldFailWhenTelephoneMissing() throws IOException {
        ExtentTestManager.startTest("TC010 - Missing Telephone Validation", "Verify 400 error when telephone is missing");
        Response response = postValidator("createNewOwnerWithoutTelephone", 400);
        errorValidation(response, "telephone", "must not be empty");
        skipRetryAndSoftAssert();
    }


    private Response postValidator(String testDataKey, int expectedStatus) throws IOException {
        Owner owner = TestDataLoader.getData(OWNER_API_DATA, testDataKey, Owner.class);
        Response response = PetClinic.getInstance().ownerService.createOwner(owner);
        validateResponseStatusCode(response, OWNER_ENDPOINT, expectedStatus);
        return response;
    }

    private void errorValidation(Response response, String expectedField, String expectedMessage) {
        Error errorResponse = response.as(Error.class);
        boolean found = errorResponse.getErrors().stream()
                .anyMatch(err -> expectedField.equals(err.getField()) && expectedMessage.equals(err.getDefaultMessage()));
        ExtentTestManager.getTest().log(LogStatus.INFO,String.format("Validating error for field '%s' with expected message: '%s'", expectedField, expectedMessage));

        softAssert.get().assertTrue(found,
                String.format("Expected validation error for field '%s' with message '%s' was not found", expectedField, expectedMessage));
    }
}

