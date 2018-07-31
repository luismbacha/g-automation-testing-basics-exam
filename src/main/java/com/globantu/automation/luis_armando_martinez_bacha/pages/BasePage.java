package com.globantu.automation.luis_armando_martinez_bacha.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.globantu.automation.luis_armando_martinez_bacha.pageObjects.BaseWebObject;

public abstract class BasePage extends BaseWebObject {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, 20);
		this.driver = driver;
	}
	
	public void waitForPageTitle(String title) {
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	public void waitForPageToLoad() {
		wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

}
