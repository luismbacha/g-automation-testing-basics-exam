package com.globantu.automation.luis_armando_martinez_bacha.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HotelsSearchResultsPage extends BasePage {
	
	@FindBy(xpath="//*[contains(text(), 'Hurry! Prices and inventory are limited')]")
	private WebElement waitMessage;
	
	@FindBy(xpath="//h1[@class='section-header-main']")
	private WebElement sectionHeader;
	
	public HotelsSearchResultsPage(WebDriver driver) {
		super(driver);
	}
	
	public void waitForWaitMessageToDissapear() {
		waitElementToDissapear(waitMessage);
	}
	
	public void waitForSectionHeader() {
		waitElementToBeDisplayed(sectionHeader);
	}
	
	public boolean isResultPresent(String text) {
		WebElement result = getDriver()
				.findElement(By.xpath("//*[@class!='visuallyhidden' "
						+ "and contains(text(),'" + text + "')]"));
		waitElementToBeDisplayed(result);
		return (result.isDisplayed());
	}
	
}
