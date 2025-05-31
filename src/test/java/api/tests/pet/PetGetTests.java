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

public class PetGetTests extends BaseApiTest {


    @Test
    public void shouldReturnPetWhenValidOwnerAndPetId() throws IOException {
        ExtentTestManager.startTest("TC015 - Get Pet by Valid IDs",
                "Verify 200 OK response when retrieving a pet with valid owner ID and pet ID");


        Pet expectedPet = TestDataLoader.getData(PET_API_DATA, "validOwnerWithPet", Pet.class);

        Response response = PetClinic.getInstance().petService.getPetByOwnerIdAndPetId(expectedPet.getOwnerId(), expectedPet.getId());
        validateResponseStatusCode(response, PET_ENDPOINT, 200);

        Pet actualPet = response.as(Pet.class);
        softAssert.get().assertEquals(expectedPet.getId(), actualPet.getId(), "Id mismatch");
        softAssert.get().assertEquals(expectedPet.getName(), actualPet.getName(), "Name mismatch");
        softAssert.get().assertEquals(expectedPet.getBirthDate(), actualPet.getBirthDate(), "Birth Date mismatch");

        softAssert.get().assertEquals(expectedPet.getType().getId(), actualPet.getType().getId(), "Type Id mismatch");
        softAssert.get().assertEquals(expectedPet.getType().getName(), actualPet.getType().getName(), "Type Name mismatch");
        skipRetryAndSoftAssert();
    }


    @Test
    public void shouldReturnPetForAnotherValidPet() throws IOException {
        ExtentTestManager.startTest("TC016 - Get Another Pet by Valid IDs",
                "Verify 200 OK response when retrieving a different valid pet");
        Pet expectedPet = TestDataLoader.getData(PET_API_DATA, "validOwnerWithPet2", Pet.class);

        Response response = PetClinic.getInstance().petService.getPetByOwnerIdAndPetId(expectedPet.getOwnerId(), expectedPet.getId());
        validateResponseStatusCode(response, PET_ENDPOINT, 200);
        Pet actualPet = response.as(Pet.class);
        softAssert.get().assertEquals(expectedPet.getId(), actualPet.getId(), "Id mismatch");
        softAssert.get().assertEquals(expectedPet.getName(), actualPet.getName(), "Name mismatch");
        softAssert.get().assertEquals(expectedPet.getBirthDate(), actualPet.getBirthDate(), "Birth Date mismatch");
        softAssert.get().assertEquals(expectedPet.getType().getId(), actualPet.getType().getId(), "Type Id mismatch");
        softAssert.get().assertEquals(expectedPet.getType().getName(), actualPet.getType().getName(), "Type Name mismatch");
        skipRetryAndSoftAssert();
    }


    @Test(groups = {"bug"})
    public void shouldReturn404ForInvalidPetId() throws IOException {
        ExtentTestManager.startTest("TC017 - Pet Not Found",
                "Verify 404 Not Found when retrieving a pet with an invalid pet ID");
        Pet invalidPet = TestDataLoader.getData(PET_API_DATA, "petNotFound", Pet.class);
        Response response = PetClinic.getInstance().petService.getPetByOwnerIdAndPetId(invalidPet.getOwnerId(), invalidPet.getId());
        validateResponseStatusCode(response, PET_ENDPOINT, 404);
    }

    @Test(groups = {"bug"})
    public void shouldReturn404ForInvalidOwnerId() throws IOException {
        ExtentTestManager.startTest("TC018 - Owner Not Found",
                "Verify 404 Not Found when retrieving a pet with an invalid owner ID");

        Pet invalidPet = TestDataLoader.getData(PET_API_DATA, "petNotFoundInvalidOwnerId", Pet.class);
        Response response = PetClinic.getInstance().petService.getPetByOwnerIdAndPetId(invalidPet.getOwnerId(), invalidPet.getId());
        validateResponseStatusCode(response, PET_ENDPOINT, 404);
    }

}
