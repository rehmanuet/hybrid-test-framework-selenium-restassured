package ui.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends BasePage {

    By homeButton = By.cssSelector("[title='home page']");
    By allOwnersButton = By.cssSelector("[ui-sref='owners']");
    By ownersButton = By.xpath("//a[text()='Owners']");
    By registerNewOwnerButton = By.cssSelector("[ui-sref='ownerNew']");
    By veterinariansButton = By.cssSelector("[title='veterinarians']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void checkHomeButton() {
        Assert.assertTrue(isVisible(homeButton), "Home button is not displayed");
    }

    public void gotoHomePage() {
        checkHomeButton();
        click(ownersButton);
    }

    public void gotoAllOwnersPage() {
        Assert.assertTrue(isVisible(ownersButton), "owners button is not displayed");
        click(ownersButton);
        Assert.assertTrue(isVisible(allOwnersButton), "all button is not displayed");
        click(allOwnersButton);
    }

    public void gotoRegisterNewOwnerPage() {
        Assert.assertTrue(isVisible(ownersButton), "owners button is not displayed");
        click(ownersButton);
        Assert.assertTrue(isVisible(registerNewOwnerButton), "register owner is not displayed");
        click(registerNewOwnerButton);
    }

    public void gotoVeterinariansPage() {
        Assert.assertTrue(isVisible(veterinariansButton), "veterinarians button is not displayed");
        click(veterinariansButton);
    }
}