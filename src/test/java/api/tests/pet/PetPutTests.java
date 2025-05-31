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

public class PetPutTests extends BaseApiTest {

    @Test(groups = {"bug"})
    public void shouldUpdatePetSuccessfully() throws IOException {
        ExtentTestManager.startTest("TC021 - Update Existing Pet",
                "Verify successful update of an existing pet with valid data");

        Pet pet = TestDataLoader.getData(PET_API_DATA, "updatePet", Pet.class);
        Response response = PetClinic.getInstance().petService.addPet(pet);

        validateResponseStatusCode(response, PET_ENDPOINT, 201);

        Pet actualResponse = response.as(Pet.class);

        softAssert.get().assertEquals(pet.getId(), actualResponse.getId(), "Pet ID mismatch after update");
        softAssert.get().assertEquals(pet.getName(), actualResponse.getName(), "Pet Name mismatch");
        softAssert.get().assertEquals(pet.getBirthDate(), actualResponse.getBirthDate(), "Pet BirthDate mismatch");
        softAssert.get().assertEquals(pet.getType().getName(), actualResponse.getType().getName(), "Pet Type mismatch");
        skipRetryAndSoftAssert();
    }

}

