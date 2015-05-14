package com.epam.test.pages;




import com.epam.test.framework.CustomExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.awt.*;



public class SendMail extends BasePage {

    private final String btnSendMail= "//div[@gh='cm']";
    private final String txtTo= "//textarea[@name='to']";
    private final String attachFiles= "//div[@command='docs']";
    private final String attachFilesIframe="//iframe[@class='KA-JQ']";
    private final String fileTxt1="//*[@aria-label='test.txt']";
    private final String addFile="//div[@guidedhelpid='ghid-psc']";
    private final String btnSendMailLast="//td[contains(@class,'Up')]/div/div[2]";
    private final String btnAttached="//div[@value='attach']";
    private final String inputSubject="//input[@name='subject']";
    private final String alertSent="//span[@id='link_vsm']";
    private final String attachmentCompleted = "//input[@name='attach']";

    private final WebDriver driver;

    public SendMail(WebDriver driver) {

        super(driver);
        this.driver = driver;
    }


    public void sendMail(String to,String attachmentPath) throws InterruptedException, AWTException {

        wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(btnSendMail))).click();
        wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(txtTo))).sendKeys(to);
        if (attachmentPath.length()>0) {
            wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(attachFiles))).click();
            System.out.print(attachmentPath);
            driver.switchTo().frame(wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(attachFilesIframe))));
            wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(fileTxt1))).click();
            wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(btnAttached))).click();
            wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(addFile))).click();
            driver.switchTo().defaultContent();
            wait.until(CustomExpectedConditions.presenceOfElementLocated(By.xpath(attachmentCompleted)));
        }

        wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(btnSendMailLast))).click();
        //Alert handling
        wait.until(CustomExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(alertSent)));

    }


}

