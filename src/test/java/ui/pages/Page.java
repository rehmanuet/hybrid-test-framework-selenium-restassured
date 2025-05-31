package ui.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;

/**
 * Implementation of the Java generic class for Page Object Model
 *
 * @author Abdur.Rehman
 */

public class Page {
    public WebDriver driver;
    public WebDriverWait wait;

    public Page(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Java Generics method for POM
     *
     * @return new page object
     */

    public <POM extends BasePage> POM getInstance(Class<POM> pageClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return pageClass.getDeclaredConstructor(WebDriver.class, WebDriverWait.class).newInstance(this.driver, this.wait);
    }
}