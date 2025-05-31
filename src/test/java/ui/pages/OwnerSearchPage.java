package ui.pages;


import api.model.Owner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OwnerSearchPage extends BasePage {

    By searchFilterTextBox = By.cssSelector("[placeholder='Search Filter']");
    By ownerInformationTitle = By.xpath("//h2[normalize-space(text())='Owner Information']");
    By ownerEditButton = By.cssSelector("[ui-sref*='ownerEdit']");
    By ownerAddPetButton = By.cssSelector("[ui-sref*='petNew']");


    public OwnerSearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getFieldValue(String fieldLabel) {
        By fieldLocator = By.xpath(String.format("//th[text()='%s']/following-sibling::td", fieldLabel));
        isVisible(fieldLocator);
        return doGetText(fieldLocator).trim();
    }

    public void checkSearchFilterVisible() {
        Assert.assertTrue(isVisible(searchFilterTextBox), "search filter is not displayed");
    }

    public void searchOwnerByName(Owner owner) {
        checkSearchFilterVisible();
        doSendKeys(searchFilterTextBox, owner.getFirstName());
    }

    public void openOwnerInformation(Owner owner) {
        String fullName = String.format("%s %s", owner.getFirstName(), owner.getLastName()).trim();
        By dynamicFind = By.xpath("//a[normalize-space(text())='" + fullName + "']");
        click(dynamicFind);
        Assert.assertTrue(isVisible(ownerInformationTitle), "Owner information is not displayed");
    }

    public void clickEditOwner() {
        Assert.assertTrue(isVisible(ownerEditButton), "owner edit button is not displayed");
        click(ownerEditButton);
    }

    public void clickAddPetButton() {
        Assert.assertTrue(isVisible(ownerAddPetButton), "Add Pet button is not displayed");
        click(ownerAddPetButton);
    }

    public void viewPet(String petName) {
        By petEntry = By.xpath("//owner-details//dd[1]/a[text()='" + petName + "']");
        Assert.assertTrue(isVisible(petEntry), "Pet is not displayed");
        click(petEntry);
    }
}