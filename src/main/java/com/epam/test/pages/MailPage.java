package com.epam.test.pages;

import com.epam.test.framework.CustomExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import tests.utils.TestBase;


public class MailPage extends BasePage {

    private final WebDriver driver;
    private final String lnkLastMail = "//div[@gh='tl']//tr[1]";
    private final String lnkWithText(String text) {return  "//*[contains(@href,'"+text+"')][1]";}
    private final String lnkMore = "//span[@gh='mll']";
    private final String lnkInbox = "//a[contains(@href,'inbox')]";
    private final String lnkTrash = "//a[contains(@href,'trash')]";
    private final String lnkSender = "//h3[@class='iw']//span[contains(@email,'@')]";
    private final String lnkAttachment = "//span[contains(.,'test.txt') and @class='aV3 a6U']";
    private final String trashMain = "//div[@role='main']//div[@class='ya']";

    public MailPage(WebDriver driver) {

        super(driver);
        this.driver = driver;


    }

    public WebElement getLastMail() {

        return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(lnkLastMail)));
    }

    public WebElement getLinkWithText(String text) {

        return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(lnkWithText(text))));
    }

    public WebElement getLinkMore() {

        return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(lnkMore)));
    }

    public WebElement getLinkTrash() {

        return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(lnkTrash)));
    }
    public WebElement getLinkInbox() {

        return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(lnkInbox)));
    }
    public String getSender() {
        System.out.print(wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(lnkSender))).getAttribute("email"));
        return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(lnkSender))).getAttribute("email");

    }

    public Boolean getAttachment() throws InterruptedException {
        Thread.sleep(5000);
        if(CustomExpectedConditions.isElementPresent(driver,By.xpath(lnkAttachment)))
         {
             return true;
         }
         else
            return false;

    }

    public void openLastMail() {
        getLastMail().click();
    }
    public void clickLinkWithText(String text) throws InterruptedException {
        wait.until(CustomExpectedConditions.pageLoaded());
        getLinkWithText(text).click();
        if (TestBase.browser.equals("CH"))
        {
            closeAlert();
        }
    }
    public void openTrash() throws InterruptedException {
        getLinkMore().click();
        if (TestBase.browser.equals("CH"))
        {
            WebElement we = wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath("//*[@gh='ns']")));
            (new Actions(driver)).dragAndDropBy(we, 0, 50).perform();
        }
        getLinkTrash().click();
        wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(trashMain)));

    }

    public void openInbox() {
        getLinkInbox().click();
    }

    public void validateMail(String mail,Boolean attachment) throws InterruptedException {
        Assert.assertEquals(getSender(),mail);
        Assert.assertEquals(getAttachment(),attachment);
    }
}
