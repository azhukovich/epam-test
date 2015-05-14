package com.epam.test.pages;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;



import com.epam.test.menus.Settings;
import org.openqa.selenium.WebDriver;
import tests.utils.Browser;


public class PageManager {


    private Browser browser;
    private final WebDriver driver;



    private SettingsPage settingsPage;
    private Settings settings;
    private LoginPage loginPage;
    private static Map<Locale, LoginPage> loginPages = new HashMap<Locale, LoginPage> ();
    private static Locale currentLocale = Locale.US;

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(Locale locale) {
        currentLocale = locale;
    }

    public PageManager(Browser browser) {

        this.browser = browser;
        this.driver = browser.getWebDriver();

    }


    public LoginPage loginPage() {

        LoginPage navigationMenu = new LoginPage(driver);

        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public AccountPage accountPage() {

        AccountPage accountPage = new AccountPage(driver);

        if (accountPage == null) {
            accountPage = new AccountPage(driver);
        }
        return accountPage;
    }

    public MailPage mailPage() {

        MailPage mailPage = new MailPage(driver);

        if (mailPage == null) {
            mailPage = new MailPage(driver);
        }
        return mailPage;
    }

/*	public LoginPage getLoginPage() {

    	if (loginPage == null) {
    		loginPage = new LoginPage(driver);
    	}
    	else if ( (loginPage != null)&&(loginPage.locale != Locale.US) ) {
    		
    		loginPage = new LoginPage(driver);
    	}
        return loginPage;
	}

	public LoginPage getLoginPage(Locale locale) {
    	
		if ( loginPage == null ) {
    		
			loginPage = new LoginPage(driver, locale);
    	}
    	else if ( (loginPage != null)&&(locale != Locale.US) ) {
    		
    		loginPage = new LoginPage(driver, locale);
    	}

        return loginPage;
	}
	*/

    public Browser getBrowser() {
        return browser;
    }


    public Settings settings() {

        Settings settings = new Settings(driver);

        if (settings == null) {
            settings = new Settings(driver);
        }
        return settings;
    }

    public SettingsPage settingsPage() {

        SettingsPage settingsPage = new SettingsPage(driver);

        if (settingsPage == null) {
            settingsPage = new SettingsPage(driver);
        }
        return settingsPage;
    }




    public SendMail sendMail() {

        SendMail sendMail = new SendMail(driver);

        if (sendMail == null) {
            sendMail = new SendMail(driver);
        }
        return sendMail;
    }
}
