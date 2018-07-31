package com.globantu.automation.luis_armando_martinez_bacha.pageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotelDetails extends BaseWebObject {

	private WebElement container;
	
	private HashMap<String, WebElement> details;
	
	private final String HOTEL_PRICE_KEY = "hotelPrice";
	private final String HOTEL_STARS_KEY = "hotelStars";
	private final String HOTEL_LINK_KEY = "hotelLink";
	private final String HOTEL_NAME_KEY = "hotelName";
	
	private final String HOTEL_PRICE_XPATH = ".//li[@class='actualPrice price fakeLink ']";
	private final String HOTEL_STARS_XPATH = ".//li[@class='starRating secondary']/strong/span[contains(@class,'stars-grey')]";
	private final String HOTEL_LINK_XPATH = ".//a[@class='flex-link']";
	private final String HOTEL_NAME_XPATH = ".//h4[@class='hotelName fakeLink']";
	
	public HotelDetails(WebElement container, WebDriver driver) {
		super(driver);
		this.container = container;
		details = new HashMap<String, WebElement>();
	}
	
	public String getHotelPrice() {
		if(!details.containsKey(HOTEL_PRICE_KEY))
			details.put(HOTEL_PRICE_KEY, container.findElement(By.xpath(HOTEL_PRICE_XPATH)));
		return this.getText(details.get(HOTEL_PRICE_KEY));
	}
	
	public String getHotelStars() {
		if(!details.containsKey(HOTEL_STARS_KEY))
			details.put(HOTEL_STARS_KEY, container.findElement(By.xpath(HOTEL_STARS_XPATH)));
		return this.getAttributeValue(details.get(HOTEL_STARS_KEY), "title");
	}
	
	public String getHotelName() {
		if (!details.containsKey(HOTEL_NAME_KEY))
			details.put(HOTEL_NAME_KEY, container.findElement(By.xpath(HOTEL_NAME_XPATH)));
		return this.getText(details.get(HOTEL_NAME_KEY));
	}
	
	public void clickHotelLink() {
		if(!details.containsKey(HOTEL_LINK_KEY))
			details.put(HOTEL_LINK_KEY, container.findElement(By.xpath(HOTEL_LINK_XPATH)));
		this.clickElement(details.get(HOTEL_LINK_KEY));
	}

}
