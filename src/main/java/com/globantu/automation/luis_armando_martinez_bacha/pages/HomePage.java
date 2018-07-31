package com.globantu.automation.luis_armando_martinez_bacha.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.luis_armando_martinez_bacha.SelectOptions;
import com.globantu.automation.luis_armando_martinez_bacha.pageObjects.CalendarPicker;

public class HomePage extends BasePage {
	
	@FindBy(id="tab-flight-tab-hp")
	private WebElement flightsButton;
	
	@FindBy(id="tab-package-tab-hp")
	private WebElement packagesButton;
	
	@FindBy(id="tab-hotel-tab-hp")
	private WebElement hotelsButton;
	
	@FindBy(id="tab-cruise-tab-hp")
	private WebElement cruisesButton;
	
	@FindBy(id="flight-type-roundtrip-label-hp-flight")
	private WebElement roundtripButton;
	
	@FindBy(id="fh-fh-hp-package")
	private WebElement flightHotelButton;
	
	@FindBy(id="fhc-fhc-hp-package")
	private WebElement flightHotelCarButton;
	
	@FindBy(id="fc-fc-hp-package")
	private WebElement flightCarButton;
	
	@FindBy(id="hotel-car-package-type-hp-package")
	private WebElement hotelCarButton;
	
	@FindBy(id="flight-origin-hp-flight")
	private WebElement flightFlyingFromInput;
	
	@FindBy(id="flight-destination-hp-flight")
	private WebElement flightFlyingToInput;
	
	@FindBy(id="flight-departing-hp-flight")
	private WebElement flightDepartingDateInput;
	
	@FindBy(id="flight-returning-hp-flight")
	private WebElement flightReturningDateInput;
	
	@FindBy(xpath="(//button[contains(@class,'btn-primary btn-action gcw-submit')])[1]")
	private WebElement flightSearchButton;
	
	@FindBy(xpath="(//button[contains(@class,'btn-primary btn-action gcw-submit')])[2]")
	private WebElement hotelsSearchButton;
	
	@FindBy(xpath="(//button[@class='btn-primary btn-action gcw-submit'])[5]")
	private WebElement cruisesSearchButton;
	
	@FindBy(id="package-origin-hp-package")
	private WebElement packageFlyingFromInput;
	
	@FindBy(id="package-destination-hp-package")
	private WebElement packageFlyingToInput;
	
	@FindBy(id="package-departing-hp-package")
	private WebElement packageDepartingDateInput;
	
	@FindBy(id="package-returning-hp-package")
	private WebElement packageReturningDateInput;
	
	@FindBy(id="search-button-hp-package")
	private WebElement packageSearchButton;
	
	@FindBy(id="package-1-adults-hp-package")
	private WebElement adultsSelect;
	
	@FindBy(id="hotel-destination-hp-hotel")
	private WebElement destinationHotelInput;
	
	@FindBy(id="hotel-checkin-hp-hotel")
	private WebElement checkinDateInput;
	
	@FindBy(id="hotel-checkout-hp-hotel")
	private WebElement checkoutDateInput;
	
	@FindBy(id="partialHotelBooking-hp-package")
	private WebElement partialHotelBookingCheckbox;
	
	@FindBy(id="package-checkin-hp-package")
	private WebElement hotelCheckInDateInput;
	
	@FindBy(id="package-checkout-hp-package")
	private WebElement hotelCheckOutDateInput;
	
	@FindBy(xpath="//a[@class='error-link']")
	private WebElement errorMessage;
	
	@FindBy(id="cruise-destination-hp-cruise")
	private WebElement cruiseDestinationSelect;
	
	@FindBy(id="cruise-departure-month-hp-cruise")
	private WebElement cruiseDepartureMonthSelect;
	
	private CalendarPicker calendar;
	
	public HomePage(WebDriver driver) {
		super(driver);
		calendar = new CalendarPicker(driver);
	}
	
	public void clickOnFlightsButton() {
		this.clickElement(flightsButton);
	}
	
	public void clickOnFlightHotelButton() {
		this.clickElement(packagesButton);
	}
	
	public void clickOnRoundtripButton() {
		this.clickElement(roundtripButton);
	}
	
	public void typeIntoFlightFlyingFromInput(String value) {
		this.typeInto(flightFlyingFromInput, value);
	}

	public void typeIntoFlightFlyingToInput(String value) {
		this.typeInto(flightFlyingToInput, value);
	}
	
	public void clickOnFlightDepartingDateInput() {
		this.clickElement(flightDepartingDateInput);
	}
	
	public void clickOnFlightReturningDateInput() {
		this.clickElement(flightReturningDateInput);
	}
	
	public boolean isNextMonthButtonDisplayed() {
		return calendar.isNextMonthButtonDisplayed();
	}
	
	public void clickOnNextMonthButton() {
		calendar.clickOnNextMonthButton();
	}
	
	public void clickOnDay(int day, int month) {
		calendar.clickOnDay(day, month);
	}
	
	public void clickOnFlightSearchButton() {
		this.clickElement(flightSearchButton);
	}
	
	public boolean isFlightHotelButtonDisplayed() {
		return this.isElementDisplayed(flightHotelButton);
	}
	
	public boolean isFlightHotelCarButtonDisplayed() {
		return this.isElementDisplayed(flightHotelCarButton);
	}
	
	public boolean isFlightCarButtonDisplayed() {
		return this.isElementDisplayed(flightCarButton);
	}
	
	public boolean isHotelCarButtonDisplayed() {
		return this.isElementDisplayed(hotelCarButton);
	}
	
	public void typeIntoPackageFlyingFromInput(String value) {
		this.typeInto(packageFlyingFromInput, value);
	}
	
	public void typeIntoPackageFlyingToInput(String value) {
		this.typeInto(packageFlyingToInput, value);
	}
	
	public void clickOnPackageDepartingDateInput() {
		this.clickElement(packageDepartingDateInput);
	}
	
	public void clickOnPackageReturningDateInput() {
		this.clickElement(packageReturningDateInput);
	}
	
	public void clickOnPackageSearchButton() {
		this.clickElement(packageSearchButton);
	}
	
	public void selectAdults(int adults) {
		selectOption(adultsSelect, String.valueOf(adults), SelectOptions.VISIBLE_TEXT);
	}
	
	public void clickOnHotelsButton() {
		clickElement(hotelsButton);
	}
	
	public void typeIntoHotelDestinationInput(String value) {
		typeInto(destinationHotelInput, value);
		pressTab(destinationHotelInput);
	}
	
	public void clickOnCheckinDateInput() {
		clickElement(checkinDateInput);
	}
	
	public void clickOnCheckoutDateInput() {
		clickElement(checkoutDateInput);
	}
	
	public void clickOnHotelsSearchButton() {
		clickElement(hotelsSearchButton);
	}
	
	public void clickPartialHotelBookingCheckbox() {
		clickElement(partialHotelBookingCheckbox);
	}
	
	public void clickOnHotelCheckInDateInput() {
		clickElement(hotelCheckInDateInput);
	}
	
	public void clickOnHotelCheckOutDateInput() {
		clickElement(hotelCheckOutDateInput);
	}
	
	public String getErrorMessage() {
		return getText(errorMessage);
	}
	
	public void clickOnCruisesButton() {
		clickElement(cruisesButton);
	}
	
	public void selectCruiseDestination(String value) {
		selectOption(cruiseDestinationSelect, value, SelectOptions.VISIBLE_TEXT);
	}
	
	public void selectCruiseDepartureMonth(String value) {
		selectOption(cruiseDepartureMonthSelect, value, SelectOptions.VISIBLE_TEXT);
	}
	
	public void clickCruisesSearchButton() {
		clickElement(cruisesSearchButton);
	}
	
}