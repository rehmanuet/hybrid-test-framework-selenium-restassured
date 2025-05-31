package ui.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetPage extends BasePage {

    By nameField = By.cssSelector("[ng-model*='name']");
    By birthField = By.cssSelector("[ng-model*='birthDate']");
    By typeField = By.cssSelector("[ng-model*='petTypeId']");
    By formSubmitButton = By.cssSelector("[type='submit']");

    public PetPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void fillForm(String name, String birth, String type) {
        isVisible(nameField);
        doSendKeys(nameField, name);
        doSendKeys(birthField, birth);
        selectByText(typeField, type);
        click(formSubmitButton);
    }

    public String getFieldValue(String fieldLabel) {
        By fieldLocator = By.cssSelector(String.format("[ng-model*='%s']", fieldLabel));
        isVisible(fieldLocator);
        return doGetDomAttribute(fieldLocator);
    }

    public String getDropDownSelectedValue(String fieldLabel) {
        By fieldLocator = By.cssSelector(String.format("[ng-model*='%s']", fieldLabel));
        isVisible(fieldLocator);
        return getSelectedValue(fieldLocator);
    }
}