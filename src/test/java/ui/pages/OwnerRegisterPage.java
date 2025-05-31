package ui.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OwnerRegisterPage extends BasePage {

    By firstNameField = By.cssSelector("[name='firstName']");
    By lastNameField = By.cssSelector("[name='lastName']");
    By addressField = By.cssSelector("[name='address']");
    By cityField = By.cssSelector("[name='city']");
    By telephoneField = By.cssSelector("[name='telephone']");
    By submitButton = By.cssSelector("[type='submit']");
    By form = By.cssSelector("[ng-submit*='$ctrl.submitOwn']");

    public OwnerRegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void checkFormVisible() {
        Assert.assertTrue(isVisible(form), "register form is not displayed");
    }

    public void fillForm(String firstName, String lastName, String address, String city, String telephone) {
        checkFormVisible();
        doSendKeys(firstNameField, firstName);
        doSendKeys(lastNameField, lastName);
        doSendKeys(addressField, address);
        doSendKeys(cityField, city);
        doSendKeys(telephoneField, telephone);
        click(submitButton);
    }

    public boolean isFieldRequired(String fieldName) {
        return isVisible(By.cssSelector("[class*='ng-empty'][name='" + fieldName + "']"));
    }

}