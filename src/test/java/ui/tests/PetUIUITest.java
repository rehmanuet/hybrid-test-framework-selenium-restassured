package ui.tests;


import api.model.Owner;
import api.model.Pet;
import common.reporting.ExtentTestManager;
import common.utils.TestDataLoader;
import org.testng.annotations.Test;
import ui.pages.HomePage;
import ui.pages.OwnerSearchPage;
import ui.pages.PetPage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static common.utils.Constants.OWNER_UI_DATA;
import static common.utils.Constants.PET_UI_DATA;


/**
 * Implementation of the test cases for the Pets
 */

public class PetUIUITest extends BaseUITest {

    @Test(groups = {"bug"})
    public void shouldEditPetToExistingOwner() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ExtentTestManager.startTest("TC001 - Add new Pet to owner",
                "Verify the new pet is added for existing owner.");

        // Navigate to All owner page
        page.getInstance(HomePage.class).gotoAllOwnersPage();

        // Load and modify test data
        Pet pet = TestDataLoader.getData(PET_UI_DATA, "validPet", Pet.class);
        Owner owner = TestDataLoader.getData(OWNER_UI_DATA, "ownerNewPet", Owner.class);

        // Search and open the owner's details
        OwnerSearchPage searchPage = page.getInstance(OwnerSearchPage.class);
        searchPage.searchOwnerByName(owner);
        searchPage.openOwnerInformation(owner);

        // Add new Pet
        searchPage.clickAddPetButton();
        page.getInstance(PetPage.class).fillForm(pet.getName(), pet.getBirthDate(), pet.getType().getName());

        // View Added Pet
        searchPage.viewPet(pet.getName());

        PetPage actualPet = page.getInstance(PetPage.class);
        String expectedBirthDate = LocalDate.parse(pet.getBirthDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        // Assertion
        softAssert.get().assertEquals(pet.getName(), actualPet.getFieldValue("name"), "Pet name mismatch");
        softAssert.get().assertEquals(pet.getType().getName(), actualPet.getDropDownSelectedValue("petTypeId"), "pet type mismatch");
        softAssert.get().assertEquals(expectedBirthDate, actualPet.getFieldValue("birthDate"), "Birth date mismatch");

        skipRetryAndSoftAssert();
    }
}