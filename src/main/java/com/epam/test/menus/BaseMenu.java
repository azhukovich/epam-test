package com.epam.test.menus;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BaseMenu {

	private final WebDriver driver;
	public Wait<WebDriver> wait;

	

	public BaseMenu(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 30);

	}




}
