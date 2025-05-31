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

public class OwnerPutTests extends BaseApiTest {

    @Test
    public void shouldUpdateOwnerSuccessfully() throws IOException {
        ExtentTestManager.startTest("TC011 - Update Existing Pet Owner",
                "Verify successful update of an existing pet owner with valid data");

        Owner owner = TestDataLoader.getData(OWNER_API_DATA, "updateOwner", Owner.class);
        owner.setFirstName(generateRandomString("Abdur"));
        owner.setLastName(generateRandomString("Rehman"));
        owner.setAddress(generateRandomString("Netherlands"));
        owner.setCity(generateRandomString("Amsterdam"));
        owner.setTelephone(String.valueOf(generateRandomNumericString(10)));

        Response response = putValidator(owner, 200);

        Owner updatedOwner = response.as(Owner.class);
        softAssert.get().assertEquals(updatedOwner.getFirstName(), owner.getFirstName(), "First Name mismatch");
        softAssert.get().assertEquals(updatedOwner.getLastName(), owner.getLastName(), "Last Name mismatch");
        softAssert.get().assertEquals(updatedOwner.getAddress(), owner.getAddress(), "Address mismatch");
        softAssert.get().assertEquals(updatedOwner.getCity(), owner.getCity(), "City mismatch");
        softAssert.get().assertEquals(updatedOwner.getTelephone(), owner.getTelephone(), "Telephone mismatch");

        skipRetryAndSoftAssert();
    }


    @Test
    public void shouldReturn400WhenFirstNameMissing() throws IOException {
        ExtentTestManager.startTest("TC012 - Missing First Name Validation",
                "Verify 400 error when first name is missing during update");

        Owner owner = TestDataLoader.getData(OWNER_API_DATA, "createNewOwnerWithoutFN", Owner.class);
        Response response = putValidator(owner, 400);
        errorValidation(response, "firstName", "must not be empty");
        skipRetryAndSoftAssert();
    }

    @Test
    public void shouldReturn400WhenLastNameMissing() throws IOException {
        ExtentTestManager.startTest("TC013 - Missing Last Name Validation",
                "Verify 400 error when last name is missing during update");

        Owner owner = TestDataLoader.getData(OWNER_API_DATA, "createNewOwnerWithoutLN", Owner.class);
        Response response = putValidator(owner, 400);

        errorValidation(response, "lastName", "must not be empty");
        skipRetryAndSoftAssert();
    }


    @Test
    public void shouldReturn400WhenAddressMissing() throws IOException {
        ExtentTestManager.startTest("TC014 - Missing Address Validation",
                "Verify 400 error when address is missing during update");
        Owner owner = TestDataLoader.getData(OWNER_API_DATA, "createNewOwnerWithoutAddress", Owner.class);
        Response response = putValidator(owner, 400);

        errorValidation(response, "address", "must not be empty");
        skipRetryAndSoftAssert();
    }


    private void errorValidation(Response response, String expectedField, String expectedMessage) {
        Error errorResponse = response.as(Error.class);
        boolean found = errorResponse.getErrors().stream().anyMatch(err -> expectedField.equals(err.getField()) && expectedMessage.equals(err.getDefaultMessage()));
        ExtentTestManager.getTest().log(LogStatus.INFO, String.format("Validating error for field '%s' with expected message: '%s'", expectedField, expectedMessage));

        softAssert.get().assertTrue(found, String.format("Expected validation error for field '%s' with message '%s' was not found", expectedField, expectedMessage));
    }


    private Response putValidator(String testDataKey, int expectedStatus) throws IOException {
        Owner owner = TestDataLoader.getData(OWNER_API_DATA, testDataKey, Owner.class);
        return putValidator(owner, expectedStatus);
    }

    private Response putValidator(Owner owner, int expectedStatus) {
        Response response = PetClinic.getInstance().ownerService.updateOwner(owner);
        validateResponseStatusCode(response, OWNER_ENDPOINT, expectedStatus);
        return response;
    }
}

