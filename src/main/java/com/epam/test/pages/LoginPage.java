package com.epam.test.pages;

import com.epam.test.areas.LoginForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends BasePage {

    private WebDriver driver;

    public LoginPage(WebDriver driver/*, Locale locale*/) {

        super(driver);
        this.driver = driver;

    }

    public void loginAs(String user, String passw) throws InterruptedException {

        getLoginForm().loginAs(user, passw);

    }




    public LoginForm getLoginForm() {

        LoginForm loginForm = new LoginForm(driver);
        return loginForm;

    }

}
