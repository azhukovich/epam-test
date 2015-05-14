package tests;


import org.testng.annotations.Test;
import tests.utils.TestBase;

/**
 * Created by azhukovich on 3/27/2015.
 */
public class TestStart extends TestBase {

    @Test
    public void testOpenURLAndLoginIntoApplication() throws Exception {

        browser.openURL(TestBase.serverURL);
        // Log in as user2
        ui.loginPage().loginAs(TestBase.username2,TestBase.userpass);

        // Open settings
        ui.settings()
                .open()
                .settings();

        //Add forwarding to user 3
        ui.settingsPage().openForwardingTab().
                addForwardingTo(TestBase.username3 + "@gmail.com");

        //Log out
        ui.accountPage().logOut();

        //Log in as user 3
        ui.loginPage().loginAs(TestBase.username3,TestBase.userpass);
        //Open last received email from inbox
        ui.mailPage().openLastMail();
        //Click confirmation link
        ui.mailPage().clickLinkWithText("https://isolated.mail.google.com");
        //log out
        ui.accountPage().logOut();

        //Log in as user 2
        ui.loginPage().loginAs(TestBase.username2,TestBase.userpass);
        //Open settings
        ui.settings()
                .open()
                .settings();
        //Set forwarding to user3 and save
        ui.settingsPage().openForwardingTab().
                setForwardingTo(username3)
                .save();

        //Open settings
        ui.settings()
                .open()
                .settings();

        //Open filers and set from=user1, hasAttachment=true,deleteIt=true,markAsImportant=true
        ui.settingsPage()
                .openFilterTab()
                .createFilter()
                .setFilterParameters(username1,true,true,true);

        //Logout
        ui.accountPage().logOut();

        //Log in as user1
        ui.loginPage().loginAs(TestBase.username1,TestBase.userpass);

        //Send email to user2 with attachment from googledrive
        ui.sendMail().sendMail(TestBase.username2+"@gmail.com","attachment");
        //Send email to user2 without attachment
        ui.sendMail().sendMail(TestBase.username2+"@gmail.com","");
        //Logout
        ui.accountPage().logOut();

        //Validations
        ui.loginPage().loginAs(TestBase.username2,TestBase.userpass);
        ui.mailPage().openTrash();
        ui.mailPage().openLastMail();
        ui.mailPage().validateMail(username1+"@gmail.com",true);
        ui.mailPage().openInbox();
        ui.mailPage().openLastMail();
        ui.mailPage().validateMail(username1 + "@gmail.com", false);
        ui.accountPage().logOut();
        ui.loginPage().loginAs(TestBase.username3,TestBase.userpass);
        ui.mailPage().openLastMail();
        ui.mailPage().validateMail(username1 + "@gmail.com", false);

    }

}
