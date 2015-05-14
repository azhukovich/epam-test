package com.epam.test.menus;

/**
 * Created by azhukovich on 30.04.2015.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.test.framework.CustomExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;



/**
 * Created by azhukovich on 3/30/2015.
 */
public class  Settings extends BaseMenu{

    private final WebDriver driver;
    private final static String btnSettings = "//div[@gh='s']/div";
    private final static String linkSettingsId = "ms";



    public Settings(WebDriver driver)  {
        super(driver);
        this.driver = driver;

    }

    public Settings open () throws InterruptedException {
        getBtnSettings(driver).click();
        return this;
    }

    public Settings settings () throws InterruptedException {
        getLinkSettings(driver).click();
        return this;
    }



    public static WebElement getBtnSettings(final WebDriver driver) throws InterruptedException {

        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        WebElement element = wait.until(elementToBeClickable( By.xpath(btnSettings)));
        return element;

    }

    public static WebElement getLinkSettings(final WebDriver driver) throws InterruptedException {

        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        WebElement element = wait.until(elementToBeClickable( By.id(linkSettingsId)));
        return element;

    }
}
