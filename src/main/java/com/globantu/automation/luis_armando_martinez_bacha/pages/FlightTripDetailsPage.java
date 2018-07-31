package com.globantu.automation.luis_armando_martinez_bacha.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightTripDetailsPage extends BasePage {
	
	@FindBy(xpath="//h1[text()='Review your trip']")
	private WebElement reviewYouTripMessage;
	
	@FindBy(xpath="(//span[contains(@class,'airport')])[1]")
	private WebElement departureFromAirport;
	
	@FindBy(xpath="(//span[contains(@class,'airport')])[2]")
	private WebElement departureToAirport;
	
	@FindBy(xpath="(//span[contains(@class,'airport')])[3]")
	private WebElement arrivalFromAirport;
	
	@FindBy(xpath="(//span[contains(@class,'airport')])[4]")
	private WebElement arrivalToAirport;
	
	@FindBy(xpath="(//h4[@class='airlineName'])[1]")
	private WebElement departureFlightAirlaneName;
	
	@FindBy(xpath="(//h4[@class='airlineName'])[2]")
	private WebElement arrivalFlightAirlaneName;
	
	@FindBy(xpath="(//h4[@class='airlineName']/following-sibling::div/div[@class='departure']/span)[1]")
	private WebElement departureStartTime;
	
	@FindBy(xpath="(//h4[@class='airlineName']/following-sibling::div/div[@class='arrival']/span)[1]")
	private WebElement departureEndTime;
	
	@FindBy(xpath="(//h4[@class='airlineName']/following-sibling::div/div[@class='departure']/span)[2]")
	private WebElement arrivalStartTime;
	
	@FindBy(xpath="(//h4[@class='airlineName']/following-sibling::div/div[@class='arrival']/span)[2]")
	private WebElement arrivalEndTime;
	
	@FindBy(xpath="//h2[text()='Trip Summary']")
	private WebElement tripSummaryHeader;
	
	@FindBy(xpath="//button[descendant::span[@id='passenger1']]")
	private WebElement passenger1ToggleButton;
	
	@FindBy(id="passenger1")
	private WebElement passenger1Text;
	
	@FindBy(id="totalPriceForPassenger1")
	private WebElement totalPricePassenger1;
	
	@FindBy(id="basePriceForPassenger1")
	private WebElement basePricePassenger1;
	
	@FindBy(xpath="//span[@class='right taxesFees']")
	private WebElement taxesTotal;
	
	@FindBy(xpath="//li[@id='expediaBookingFee']/span")
	private WebElement bookingFee;
	
	@FindBy(xpath="//span[@class='packagePriceTotal']")
	private WebElement priceTotal;
	
	@FindBy(id="bookButton")
	private WebElement bookButton;
	
	@FindBy(xpath="//*[text()='Processing your request']")
	private WebElement processingMessage;
	
	public FlightTripDetailsPage(WebDriver driver) {
		super(driver);
		for(String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			if(driver.getTitle().equals("Trip Detail | Travelocity"))
				break;
			if(driver.getTitle().isEmpty()) {
				this.waitForPageTitle("Trip Detail | Travelocity");
				break;
			}
		}
	}
	
	public boolean isReviewYouTripMessageDisplayed() {
		return this.isElementDisplayed(this.reviewYouTripMessage);
	}
	
	public String getDepartureFromAirport() {
		return this.getText(this.departureFromAirport);
	}
	
	public String getDepartureToAirport() {
		return this.getText(this.departureToAirport);
	}
	
	public String getArrivalFromAirport() {
		return this.getText(this.arrivalFromAirport);
	}
	
	public String getArrivalToAirport() {
		return this.getText(this.arrivalToAirport);
	}
	
	public String getDepartureAirline() {
		return this.getText(this.departureFlightAirlaneName);
	}
	
	public String getArrivalAirline() {
		return this.getText(this.arrivalFlightAirlaneName);
	}
	
	public String getDepartureStartTime() {
		return this.getText(this.departureStartTime);
	}
	
	public String getDepartureEndTime() {
		return this.getText(this.departureEndTime);
	}
	
	public String getArrivalStartTime() {
		return this.getText(this.arrivalStartTime);
	}
	
	public String getArrivalEndTime() {
		return this.getText(this.arrivalEndTime);
	}
	
	public boolean isTripSummaryHeaderDisplayed() {
		return this.isElementDisplayed(tripSummaryHeader);
	}
	
	public boolean isToggleButtonDisplayed() {
		return this.isElementDisplayed(passenger1ToggleButton);
	}
	
	public String getPassenger1Text() {
		return this.getText(passenger1Text);
	}
	
	public boolean isTotalPricePassenger1Displayed() {
		return this.isElementDisplayed(totalPricePassenger1);
	}
	
	public String getTotalPricePassenger1() {
		return this.getText(totalPricePassenger1);
	}
	
	public String getBasePricePassenger1() {
		return this.getText(basePricePassenger1);
	}
	
	public String getTaxesTotal() {
		return this.getText(taxesTotal);
	}
	
	public String getBookingFee() {
		return this.getText(bookingFee);
	}
	
	public boolean isPriceTotalDisplayed() {
		return this.isElementDisplayed(priceTotal);
	}

	public String getPriceTotal() {
		return this.getText(priceTotal);
	}
	
	public void clickBookButton() {
		this.clickElement(bookButton);
	}
	
	public void waitForProcessingMessage() {
		this.waitElementToDissapear(processingMessage);
	}

}
