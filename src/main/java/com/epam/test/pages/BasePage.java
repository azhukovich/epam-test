package com.epam.test.pages;

import com.epam.test.areas.LoadPage;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {


    public static Wait<WebDriver> wait;
    private final WebDriver driver;
    private LoadPage loadPage;
    //protected static Locale currentLocale = PageManager.getCurrentLocale();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 60);

    }
    public LoadPage loadPage() {

        if (loadPage == null) {
            loadPage = new LoadPage(driver);
        }
        return loadPage;

    }

    public boolean closeAlert() throws InterruptedException {
        Thread.sleep(2000);
        try
        {
            driver.switchTo().alert().accept();
            return true;
        }   // try
        catch (NoAlertPresentException Ex)
        {
            return false;
        }   // catch
    }

}
