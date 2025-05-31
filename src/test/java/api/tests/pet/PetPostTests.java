package api.tests.pet;

import api.base.BaseApiTest;
import api.endpoints.PetClinic;
import api.model.Pet;
import common.reporting.ExtentTestManager;
import common.utils.TestDataLoader;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static common.utils.Constants.PET_API_DATA;
import static common.utils.Constants.PET_ENDPOINT;

public class PetPostTests extends BaseApiTest {

    @Test(groups = {"bug"})
    public void shouldAddNewPetSuccessfully() throws IOException {
        ExtentTestManager.startTest(
                "TC019 - Add New Pet",
                "Verify that a new pet is successfully added (Status 201)"
        );

        Pet newPet = TestDataLoader.getData(PET_API_DATA, "createNewPet", Pet.class);
        Response response = PetClinic.getInstance().petService.addPet(newPet);

        validateResponseStatusCode(response, PET_ENDPOINT, 201);
    }

    @Test(groups = {"bug"})
    public void shouldReturn400WhenAddingPetWithoutName() throws IOException {
        ExtentTestManager.startTest(
                "TC020 - Add Pet Without Name",
                "Verify 400 Bad Request when adding a pet without a name"
        );

        Pet invalidPet = TestDataLoader.getData(PET_API_DATA, "createNewPetWithoutName", Pet.class);
        Response response = PetClinic.getInstance().petService.addPet(invalidPet);

        validateResponseStatusCode(response, PET_ENDPOINT, 400);
    }
}
