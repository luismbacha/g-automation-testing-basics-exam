package com.globantu.automation.luis_armando_martinez_bacha.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookHotelPage extends BasePage {
	
	@FindBy(xpath="//*[text()='Getting your room options']")
	private WebElement gettingOptionsMessage;
	
	@FindBy(id="hotel-name")
	private WebElement hotelName;
	
	@FindBy(xpath="//div[@id='license-plate']/div/strong/span[contains(@class,'stars-grey')]")
	private WebElement hotelStars;
	
	@FindBy(xpath="(//a[.//span[text()='Select']])[1]")
	private WebElement firstOptionSelectButton;
	
	public BookHotelPage(WebDriver driver) {
		super(driver);
		String mainWindowHandle = driver.getWindowHandle();
		for(String windowHandle : driver.getWindowHandles()) {
			if(!windowHandle.equals(mainWindowHandle))
				driver.switchTo().window(windowHandle);
		}
	}
	
	public void waitForGettingOptionsMessageToDissapear() {
		this.waitElementToDissapear(gettingOptionsMessage);
	}
	
	public String getHotelName() {
		return this.getText(hotelName);
	}
	
	public String getHotelStars() {
		return this.getAttributeValue(hotelStars, "title");
	}
	
	public void clickFirstRoomOptionSelectButton() {
		this.clickElement(firstOptionSelectButton);
	}
	
}
