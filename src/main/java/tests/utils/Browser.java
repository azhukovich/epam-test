package tests.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class Browser {

    private JavascriptExecutor js;
    private WebDriver driver;

    public Browser(String BROWSER) throws MalformedURLException {


        if (BROWSER.equals("FF")) {

            System.out.println("FF is selected");
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
            capability.setBrowserName("firefox");

        } else if (BROWSER.equals("IE")) {

            System.out.println("IE is selected");
            System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();

        } else if (BROWSER.equals("CH")) {

            System.out.println("Google Chrome is selected");
             DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            capabilities.setBrowserName("chrome");
            capabilities.setCapability("webdriver.chrome.logfile", "chromedriverlog.log");
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);



        } else {
            System.out.println("Remote driver is selected by default");
            DesiredCapabilities capability = DesiredCapabilities.firefox();
            WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
            capability.setBrowserName("firefox");



        }

        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        System.out.println("Browser initiated successfully.");
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void ExecuteScript(String script) {
        js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }

    public void openURL(String url) {
        driver.get(url);
    }

    public void DeleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public WebDriver.Options manage() {
        return driver.manage();
    }




}

