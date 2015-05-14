package com.epam.test.areas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by azhukovich on 3/26/2015.
 */
public class LoadPage {

    private final WebDriver driver;
    public Wait<WebDriver> wait;

    public LoadPage(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, 75);
    }

    public void pageRefreshedAndLoaded() {
        pageLoaded();
        //pageRefreshed();

    }
    public void pageLoaded() {

        try {
            if (driver.findElement(By.xpath("//div[(@id='loading')]")).isDisplayed())
            {
                wait.until(presenceOfElementLocated(By.xpath("//div[(@id='loading') and contains(@style,'display: none;')]")));
            }
        } catch (Exception ignored) {

        }

    }




}

