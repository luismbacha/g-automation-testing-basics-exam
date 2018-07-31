package com.globantu.automation.luis_armando_martinez_bacha.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalendarPicker extends BaseWebObject {
	
	private String findDayXpath = "//button[@data-day='%d' and @data-month='%d']";
	
	@FindBy(xpath="//div[@class='hero-banner-gradient']")
	private WebElement calendar;
	
	@FindBy(xpath="//button[contains(@class,'datepicker-paging datepicker-next btn-paging btn-secondary next')]")
	private WebElement nextMonthButton;
	
	public CalendarPicker(WebDriver driver) {
		super(driver);
	}
	
	public boolean isNextMonthButtonDisplayed() {
		return isElementDisplayed(nextMonthButton);
	}
	
	public void clickOnNextMonthButton(){
		this.clickElement(nextMonthButton);
	}
	
	public void clickOnDay(int day, int month) {
		WebElement dayPicker = calendar.findElement(By.xpath(String.format(findDayXpath, day, month)));
		this.clickElement(dayPicker);
	}
	
}
