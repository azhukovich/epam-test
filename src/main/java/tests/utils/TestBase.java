package tests.utils;

import java.io.*;
import java.util.Properties;
import com.epam.test.pages.PageManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;





public class TestBase {


    public static PageManager ui;

    public static Browser browser;
    public static WebDriver driver;
    public static Wait<WebDriver> wait;
    public static String sbrowser;
    public static String serverURL;
    public static String currentRunOutputFolder;
    public static String username1;
    public static String username2;
    public static String username3;
    public static String userpass;



    @BeforeSuite
    @Parameters( {"browserType", "user1", "user2", "user3", "pass"} )
    public void setUp(@Optional String browserType,String user1,String user2,String user3,String pass) throws Exception {

        username1=user1;
        username2=user2;
        username3=user3;
        userpass=pass;


        Properties props = new Properties();

        serverURL = "https://mail.google.com/mail";
      sbrowser = browserType;
       if (browser == null) {
            browser = new Browser(sbrowser);
        }
        if (driver == null) {
            driver = browser.getWebDriver();
        }
        if (wait == null) {
            wait = new WebDriverWait(driver, 30);

        }
        if (ui == null) {
            ui = new PageManager(browser);
        }
    }

    //	@AfterSuite
    public void tearDown() throws Exception {


        //	browser.getWebDriver().quit();
        if (browser.getWebDriver() != null) {
            try {
                browser.getWebDriver().quit();

            } catch (Exception e) {

            } finally {
                browser.setWebDriver(null);

            }
        }




    }



    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(currentRunOutputFolder + "\\Failure_Printscreens\\" + result.getMethod().getMethodName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }




}
