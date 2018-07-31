package com.globantu.automation.luis_armando_martinez_bacha.pageObjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.globantu.automation.luis_armando_martinez_bacha.SelectOptions;

public abstract class BaseWebObject {

	private WebDriverWait wait;
	private WebDriver driver;
	
	public BaseWebObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
		this.driver = driver;
	}
	
	protected void clickElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	protected void typeInto(WebElement element, String value) {
		clearText(element);
		element.sendKeys(value);
	}
	
	protected void clearText(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
	}
		
	protected void selectOption(WebElement select, String value, SelectOptions option) {
		wait.until(ExpectedConditions.elementToBeClickable(select));
		switch (option) {
			case VISIBLE_TEXT:
				new Select(select).selectByVisibleText(value);
		}
	}
	
	protected String getAttributeValue(WebElement element, String attribute) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getAttribute(attribute);
	}
	
	protected String getText(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}
	
	protected void waitElementToDissapear(WebElement element) {
		try {
			this.waitElementToBeDisplayed(element);
			if(element != null && element.isDisplayed())
				wait.until(ExpectedConditions.invisibilityOf(element));
		} catch(StaleElementReferenceException | NoSuchElementException | TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	protected void waitElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	protected void waitElementsToBeDisplayed(List<WebElement> elements) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	protected void waitElementToBeDisplayed(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	protected boolean isElementDisplayed(WebElement element) {
		try {
			this.waitElementToBeDisplayed(element);
			return element.isDisplayed();
		} catch(NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected void pressTab(WebElement element) {
		element.sendKeys(Keys.TAB);
	}
	
	protected WebDriver getDriver() {
		return this.driver;
	}
	
}
