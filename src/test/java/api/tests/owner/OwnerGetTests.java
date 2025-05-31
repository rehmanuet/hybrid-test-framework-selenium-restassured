package api.tests.owner;

import api.base.BaseApiTest;
import api.endpoints.PetClinic;
import api.model.Owner;
import common.reporting.ExtentTestManager;
import common.utils.TestDataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Objects;

import static common.utils.Constants.OWNER_ENDPOINT;
import static common.utils.Constants.OWNER_API_DATA;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class OwnerGetTests extends BaseApiTest {

    @Test
    public void shouldReturnOwnerWhenValidIdIsProvided() throws IOException {
        ExtentTestManager.startTest("TC001 - Get Pet Owner by Valid ID", "Verify 200 OK response when retrieving owner by valid ID");

        Owner ownerData = TestDataLoader.getData(OWNER_API_DATA, "ownerValidId", Owner.class);

        Response response = PetClinic.getInstance().ownerService.getOwnerByID(ownerData.getId());
        validateResponseStatusCode(response, OWNER_ENDPOINT, 200);
        Owner ownerResponse = response.as(Owner.class);
        softAssert.get().assertEquals(ownerData.getId(), ownerResponse.getId(), "Id mismatch");
        softAssert.get().assertEquals(ownerData.getFirstName(), ownerResponse.getFirstName(), "First Name mismatch");
        softAssert.get().assertEquals(ownerData.getLastName(), ownerResponse.getLastName(), "Last Name mismatch");
        softAssert.get().assertEquals(ownerData.getAddress(), ownerResponse.getAddress(), "Address mismatch");
        softAssert.get().assertEquals(ownerData.getCity(), ownerResponse.getCity(), "City mismatch");
        softAssert.get().assertEquals(ownerData.getTelephone(), ownerResponse.getTelephone(), "Telephone mismatch");
        skipRetryAndSoftAssert();


    }

    @Test(groups = {"bug"})
    public void shouldReturn404WhenOwnerIdDoesNotExist() throws IOException {
        ExtentTestManager.startTest("TC002 - Get Pet Owner by Not Found ID", "Verify 404 Not Found when retrieving owner by invalid ID");

        Integer ownerNotFoundId = Objects.requireNonNull(TestDataLoader.getData(OWNER_API_DATA, "ownerNotFoundId", Owner.class)).getId();

        Response response = PetClinic.getInstance().ownerService.getOwnerByID(ownerNotFoundId);
        validateResponseStatusCode(response, OWNER_ENDPOINT, 404);
    }

    @Test
    public void shouldReturnListOfAllOwners() {
        ExtentTestManager.startTest("TC003 - Get All Pet Owners", "Verify 200 OK and non-empty list when retrieving all owners");

        Response response = PetClinic.getInstance().ownerService.getOwners();
        validateResponseStatusCode(response, OWNER_ENDPOINT + "/list", 200);

        Owner[] owners = response.as(Owner[].class);
        softAssert.get().assertTrue(owners.length > 0, "Owner list should not be empty");
        skipRetryAndSoftAssert();
    }


    @Test
    public void shouldMatchOwnerJsonSchema() throws IOException {
        ExtentTestManager.startTest("TC004 - JSON Schema Validation for Get Owner by ID", "Validate response matches expected Owner schema");
        Integer schemaUserId = Objects.requireNonNull(TestDataLoader.getData(OWNER_API_DATA, "ownerValidSchemaUserId", Owner.class)).getId();
        Response response = PetClinic.getInstance().ownerService.getOwnerByID(schemaUserId);
        response.then().assertThat().body(matchesJsonSchemaInClasspath("owners_schema.json"));
    }
}
