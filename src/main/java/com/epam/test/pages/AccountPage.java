package com.epam.test.pages;

import com.epam.test.framework.CustomExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Predicate;


public class AccountPage extends BasePage {

    private final WebDriver driver;
    private final String btnUserOptions = "//div[@id='gb']//a[contains(@href,'profiles')]";
    private final String btnUserLogout = ".//div[@id='gb']//a[contains(@href,'logout')]";

    public AccountPage(WebDriver driver) {

        super(driver);
        this.driver = driver;


    }

    public WebElement getBtnUserOptions() {

        return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(btnUserOptions)));
    }

    public WebElement getBtnUserLogOut() {

        return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(btnUserLogout)));
    }

    public void logOut() throws InterruptedException {

        wait.until(CustomExpectedConditions.pageLoaded());
        Thread.sleep(2000);

        getBtnUserOptions().click();
        getBtnUserLogOut().click();
    }

}
