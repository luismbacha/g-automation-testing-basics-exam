package com.globantu.automation.luis_armando_martinez_bacha.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PackageTripDetailsPage extends BasePage {
	
	@FindBy(xpath="//h1[@class='section-header-main  header-class']")
	private WebElement reviewYouTripMessage;
	
	@FindBy(xpath="(//span[@class='secondary sub-info'])[1]")
	private WebElement departureFlightAirlineName;

	@FindBy(xpath="(//span[@class='secondary sub-info'])[2]")
	private WebElement arrivalFlightAirlineName;
	
	@FindBy(id="trip-flight-to")
	private WebElement tripFlightFrom;
	
	@FindBy(id="trip-flight-from")
	private WebElement tripFlightTo;
	
	@FindBy(id="trip-summary-hotel-title")
	private WebElement hotelName;
	
	@FindBy(xpath="(//button[@class='btn-primary btn-action'])[1]")
	private WebElement continueBookingButton;
	
	@FindBy(xpath="//*[text()='Processing your request']")
	private WebElement processingMessage;

	public PackageTripDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	public String getReviewYourTripMessage() {
		return getText(reviewYouTripMessage);
	}
	
	public String getDepartureFlightAirlineName() {
		return getText(departureFlightAirlineName);
	}
	
	public String getArrivalFlightAirlineName() {
		return getText(arrivalFlightAirlineName);
	}
	
	public String getTripFlightFrom() {
		return getText(tripFlightFrom);
	}
	
	public String getTripFlightTo() {
		return getText(tripFlightTo);
	}
	
	public String getHotelName() {
		return getText(hotelName);
	}
	
	public void clickContinueBookingButton() {
		clickElement(continueBookingButton);
	}

	public void waitForProcessingMessage() {
		this.waitElementToDissapear(processingMessage);
	}
}
