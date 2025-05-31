package ui.tests;


import api.model.Owner;
import common.reporting.ExtentTestManager;
import common.utils.TestDataLoader;
import org.testng.annotations.Test;
import ui.pages.HomePage;
import ui.pages.OwnerRegisterPage;
import ui.pages.OwnerSearchPage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static common.utils.Constants.OWNER_API_DATA;
import static common.utils.Constants.OWNER_UI_DATA;


/**
 * Implementation of the test cases for the Owners
 */

public class OwnerUIUITest extends BaseUITest {

    @Test
    public void shouldCreateNewPetOwnerSuccessfully() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ExtentTestManager.startTest("TC001 - New Owner Registration",
                "Verify the registration of a new valid owner.");

        // Navigate to register page
        page.getInstance(HomePage.class).gotoRegisterNewOwnerPage();

        // Load and modify test data
        Owner owner = TestDataLoader.getData(OWNER_API_DATA, "createNewOwner", Owner.class);
        owner.setFirstName(generateRandomString(owner.getFirstName()));

        // Fill and submit the form
        fillOwnerForm(owner);

        // Search and open the owner's details
        OwnerSearchPage searchPage = page.getInstance(OwnerSearchPage.class);
        searchPage.searchOwnerByName(owner);
        searchPage.openOwnerInformation(owner);

        // Expected full name
        String expectedFullName = String.format("%s %s", owner.getFirstName(), owner.getLastName());


        softAssert.get().assertEquals(searchPage.getFieldValue("Name"), expectedFullName, "Owner name mismatch");
        softAssert.get().assertEquals(searchPage.getFieldValue("Address"), owner.getAddress(), "Owner address mismatch");
        softAssert.get().assertEquals(searchPage.getFieldValue("City"), owner.getCity(), "Owner city mismatch");
        softAssert.get().assertEquals(searchPage.getFieldValue("Telephone"), owner.getTelephone(), "Owner phone mismatch");
        skipRetryAndSoftAssert();
    }

    @Test
    public void shouldNotSubmitWhenFirstNameMissing() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ExtentTestManager.startTest("TC002 - Missing First Name Validation",
                "Verify that form shouldn't submitted when 'First Name' is missing");

        String fieldName = "firstName";
        // Navigate to register page
        page.getInstance(HomePage.class).gotoRegisterNewOwnerPage();

        // Load and modify test data
        Owner owner = TestDataLoader.getData(OWNER_API_DATA, "createNewOwnerWithoutFN", Owner.class);
        owner.setFirstName("");

        // Fill and submit the form
        fillOwnerForm(owner);

        softAssert.get().assertTrue(page.getInstance(OwnerRegisterPage.class).isFieldRequired(fieldName), String.format("%s is not required field", fieldName));
        skipRetryAndSoftAssert();
    }

    @Test
    public void shouldNotSubmitWhenLastNameMissing() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ExtentTestManager.startTest("TC003 - Missing Last Name Validation",
                "Verify that form shouldn't submitted when 'Last Name' is missing");
        // Navigate to register page
        page.getInstance(HomePage.class).gotoRegisterNewOwnerPage();

        // Load and modify test data
        Owner owner = TestDataLoader.getData(OWNER_API_DATA, "createNewOwnerWithoutLN", Owner.class);
        owner.setLastName("");

        // Fill and submit the form
        fillOwnerForm(owner);

        String fieldName = "lastName";

        softAssert.get().assertTrue(page.getInstance(OwnerRegisterPage.class).isFieldRequired(fieldName), String.format("%s is not required field", fieldName));
        skipRetryAndSoftAssert();
    }


    @Test
    public void shouldViewPetOwnerNameSuccessfully() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ExtentTestManager.startTest("TC004 - View Owner by First Name",
                "Verify the search functionality to view owner by first name");

        // Navigate to register page
        page.getInstance(HomePage.class).gotoAllOwnersPage();

        // Load and modify test data
        Owner owner = TestDataLoader.getData(OWNER_UI_DATA, "viewOwner", Owner.class);

        // Search and open the owner's details
        OwnerSearchPage searchPage = page.getInstance(OwnerSearchPage.class);
        searchPage.searchOwnerByName(owner);
        searchPage.openOwnerInformation(owner);

        String expectedFullName = String.format("%s %s", owner.getFirstName(), owner.getLastName());

        // Assertion
        softAssert.get().assertEquals(searchPage.getFieldValue("Name"), expectedFullName, "Owner name mismatch");
        softAssert.get().assertEquals(searchPage.getFieldValue("Address"), owner.getAddress(), "Owner address mismatch");
        softAssert.get().assertEquals(searchPage.getFieldValue("City"), owner.getCity(), "Owner city mismatch");
        softAssert.get().assertEquals(searchPage.getFieldValue("Telephone"), owner.getTelephone(), "Owner phone mismatch");

        skipRetryAndSoftAssert();
    }


    @Test
    public void shouldEditPetOwnerNameSuccessfully() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ExtentTestManager.startTest("TC005 - Edit Owner First Name",
                "Verify the update of owner's first name.");

        // Navigate to register page
        page.getInstance(HomePage.class).gotoAllOwnersPage();

        // Load and modify test data
        Owner owner = TestDataLoader.getData(OWNER_UI_DATA, "updateOwner", Owner.class);
        String currentFirstName = owner.getFirstName();

        // Search and open the owner's details
        OwnerSearchPage searchPage = page.getInstance(OwnerSearchPage.class);
        searchPage.searchOwnerByName(owner);
        searchPage.openOwnerInformation(owner);

        String expectedFullName = String.format("%s %s", owner.getFirstName(), owner.getLastName());

        // Assert the exact user exists
        softAssert.get().assertEquals(searchPage.getFieldValue("Name"), expectedFullName, "Owner name mismatch");

        // Click the editOwner button
        searchPage.clickEditOwner();

        // Updating name for validation
        owner.setFirstName(generateRandomString(owner.getFirstName()));

        // Updating the name
        fillOwnerForm(owner);
        expectedFullName = String.format("%s %s", owner.getFirstName(), owner.getLastName());

        // Asserting if owner name is updated
        softAssert.get().assertEquals(searchPage.getFieldValue("Name"), expectedFullName, "updated Owner name mismatch");

        // Resetting the data to default name
        searchPage.clickEditOwner();
        owner.setFirstName(currentFirstName);

        fillOwnerForm(owner);

        expectedFullName = String.format("%s %s", owner.getFirstName(), owner.getLastName());
        softAssert.get().assertEquals(searchPage.getFieldValue("Name"), expectedFullName, "Default owner name mismatch");

        skipRetryAndSoftAssert();
    }

    private void fillOwnerForm(Owner owner) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        page.getInstance(OwnerRegisterPage.class).fillForm(
                owner.getFirstName(),
                owner.getLastName(),
                owner.getAddress(),
                owner.getCity(),
                owner.getTelephone()
        );
    }
}