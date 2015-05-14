package com.epam.test.pages;


import com.epam.test.framework.CustomExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SettingsPage extends BasePage {

    private final WebDriver driver;

	private final String tabList = "//tr[@role='tablist']";

	public final String cmbLocaleXpath = "//div[starts-with(@id,'js_deploy_widgets_security_EditUserPreferences_')]/div/div/div/div[1]/div/div/div[2]/span/span/table";
    private final String btnSaveXpath = ".//*[@id='userPreferences']/div/div/div/div/div[2]/span/span";
	private final String btnForwardXpath = ".//div/input[@act='add']";
	private final String inputForwardAddressXpath = ".//div[@class='PN']/input";

	private final String tabForwarding = ".//div[@class='nH fY']/div[6]";
	private final String btnNextName = "next";
	private final String btnContinue = ".//input[@type='submit']";
	private final String iframeAlertDialog = ".//div[@role='alertdialog']/div/iframe";
	private final String enableForwardingFor (String mail) {return "//tr[contains(.,'"+mail+"')]/td/input[@name='sx_em']";};
	private final String btnSave="//div[contains(@class,'nH') and not(@style='display: none;')]/div/table//*[@guidedhelpid='save_changes_row']//*[@guidedhelpid='save_changes_button']";
	private final String btnOk = "//button[@name='ok']";

	//Filters tab
	private final String tabFilter = ".//div[@class='nH fY']/div[5]";
	private final String btnCreateNewFilter = "//div[contains(@class,'nH') and not(@style='display: none;')]/div/table//*[@class='sA'][1]";
	private final String fldFilterFrom = "//div[@class='w-Nw'][1]//input";
	private final String chbAttachments = "//div[@class='w-Nw'][6]/span[1]/input";
	private final String lnkCreateFilter = "//div[@class='acM']";
	private final String chbDeleteIt = "//div[@class='nH lZ'][6]/input";
	private final String chbMarkAsImportant = "//div[@class='nH lZ']/input";
	private final String btnAddFilter = "//div[@class='ZZ']/div/div[@role='button']";
	private final String alertFilterCreated = "//a[contains(@href,'answer/6579')]";


	public SettingsPage(WebDriver driver) {

		super(driver);
		this.driver = driver;
		

 
	}
	

	public WebElement getBtnForward() {

		return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(btnForwardXpath)));
	}
	public WebElement getForwardTab() {

		return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(tabForwarding)));
	}



	public WebElement getInputForwardAddress() {

		return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(inputForwardAddressXpath)));
	}

	public WebElement getBtnNext() {

		return wait.until(CustomExpectedConditions.elementToBeClickable(By.name(btnNextName)));
	}

	public WebElement getBtnContinue() {

		return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(btnContinue)));
	}

	public WebElement getBtnOk() {

		return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(btnOk)));
	}

	public WebElement getFrameAlert() {

		return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(iframeAlertDialog)));
	}

	public WebElement getRdbAddForwarding(String mail) {

		return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(enableForwardingFor(mail))));
	}

	public SettingsPage setForwardingTo(String email) {

		getRdbAddForwarding(email).click();
		return this;
	}

	public void save() {

		wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(btnSave))).click();
		wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(tabList)));
	}

	public void addForwardingTo(String email) {
		getBtnForward().click();
		getInputForwardAddress().sendKeys(email);
		getBtnNext().click();
		driver.switchTo().frame(getFrameAlert());
		getBtnContinue().click();

		getBtnOk().click();

	}

	public SettingsPage openForwardingTab() {
		getForwardTab().click();
		return this;

	}




	//Filter tab methods

	public WebElement getFilterTab() {
		wait.until(CustomExpectedConditions.pageLoaded());
		return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(tabFilter)));
	}



	public WebElement getBtnCreateNewFilter() {
		wait.until(CustomExpectedConditions.visibilityOfElementLocated(By.xpath(btnCreateNewFilter)));
		return wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(btnCreateNewFilter)));
	}

	public SettingsPage openFilterTab() {
		getFilterTab().click();
		return this;

	}

	public SettingsPage createFilter() {
		getBtnCreateNewFilter().click();
		return this;
	}



	public SettingsPage setFilterParameters(String from, Boolean hasAttachment,Boolean deleteIt,Boolean markAsImportant) {
		wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(fldFilterFrom))).sendKeys(from);
		if (hasAttachment) wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(chbAttachments))).click();
		wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(lnkCreateFilter))).click();
		if (deleteIt) wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(chbDeleteIt))).click();
		if (markAsImportant) wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(chbMarkAsImportant))).click();
		wait.until(CustomExpectedConditions.elementToBeClickable(By.xpath(btnAddFilter))).click();
		wait.until(CustomExpectedConditions.visibilityOfElementLocated(By.xpath(alertFilterCreated)));
		return this;
	}
}
