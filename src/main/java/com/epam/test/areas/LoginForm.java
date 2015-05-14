package com.epam.test.areas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.epam.test.framework.CustomExpectedConditions.elementToBeClickable;

/**
 * Created by azhukovich on 3/26/2015.
 */
public class LoginForm {

    private WebDriver driver;
    public Wait<WebDriver> wait;



    public LoginForm(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, 30);


    }

    private final String txbUserEmailName = "Email";
    private final String txbUserPasswordName = "Passwd";
    private final String btnLoginName = "signIn";

     private final String accountchooser = ".//a[@id='account-chooser-link']";
    private final String accAddId = "account-chooser-add-account";
    private final String next = "//input[@id='next']";

    public WebElement getTxbUsername() {
        return wait.until(elementToBeClickable(By.name(txbUserEmailName)));
    }

    public WebElement getTxbPassword() {
        return wait.until(elementToBeClickable(By.name(txbUserPasswordName)));
    }

    public WebElement getBtnLogin() {
            return wait.until(elementToBeClickable(By.id(btnLoginName)));
    }



    public void loginAs(String user, String passw) throws InterruptedException {



        try {
            wait = new WebDriverWait(driver, 1);
            wait.until(elementToBeClickable(By.xpath(accountchooser))).click();
            wait.until(elementToBeClickable(By.id(accAddId))).click();

        } catch (Exception e) {
        }
        try {
            wait = new WebDriverWait(driver, 1);
            wait.until(elementToBeClickable(By.id(accAddId))).click();

        } catch (Exception e) {
        }

        try {
            wait = new WebDriverWait(driver, 5);
            WebElement nB = wait.until(elementToBeClickable(By.xpath(next)));
            getTxbUsername().sendKeys(user);
            nB.click();
            wait = new WebDriverWait(driver, 30);
            getTxbPassword().sendKeys(passw);
            getBtnLogin().click();

        } catch (Exception e) {
            wait = new WebDriverWait(driver, 30);
            getTxbUsername().sendKeys(user);
            getTxbPassword().sendKeys(passw);
            getBtnLogin().click();

        }


    }



}
