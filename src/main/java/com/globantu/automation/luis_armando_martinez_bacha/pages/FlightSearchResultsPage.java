package com.globantu.automation.luis_armando_martinez_bacha.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.luis_armando_martinez_bacha.SelectOptions;
import com.globantu.automation.luis_armando_martinez_bacha.pageObjects.FlightDetails;

public class FlightSearchResultsPage extends BasePage {
	
	private final String FLIGHTS_RESULTS_XPATH = "//li[@class='flight-module segment offer-listing']";
	
	@FindBy(xpath="//h3[text()='Searching...']")
	private WebElement searchingMessage;
	
	@FindBy(id="departure-airport-1")
	private WebElement departureAirportInput;
	
	@FindBy(id="arrival-airport-1")
	private WebElement arrivalAirportInput;
	
	@FindBy(xpath="//span[@class='title-city-text']")
	private WebElement titleCityText;
	
	@FindBy(name="sort")
	private WebElement sortBySelect;
	
	@FindBy(xpath="//*[text()='Sorting by duration...']")
	private WebElement sortingMessage;
	
	@FindBy(xpath="//*[text()='Finding return flights...']")
	private WebElement findingReturnFlightsMessage;
	
	@FindBy(id="forcedChoiceNoThanks")
	private WebElement noThanksLink;
	
	@FindBy(xpath="//*[text()='Putting together your trip']")
	private WebElement puttingTogetherMessage;
	
	public FlightSearchResultsPage(WebDriver driver) {
		super(driver);
	}
	
	public String getDepartureAirportInputValue() {
		return this.getAttributeValue(departureAirportInput, "value");
	}
	
	public String getArrivalAirportInputValue() {
		return this.getAttributeValue(arrivalAirportInput, "value");
	}
	
	public String getTitleCityText() {
		return this.getText(titleCityText);
	}

	public void clickFlightDetailsLinks() {
		List<WebElement> flightsList = this.getDriver().findElements(By.xpath(FLIGHTS_RESULTS_XPATH));
		for(WebElement element : flightsList) {
			new FlightDetails(element, this.getDriver()).clickDetails();
		}
	}
	
	public void selectSortByOption(String visibleText) {
		this.selectOption(sortBySelect, visibleText, SelectOptions.VISIBLE_TEXT);
	}
	
	public void waitSortingByMessage() {
		if(this.isElementDisplayed(sortingMessage)) {
			this.waitElementToDissapear(sortingMessage);
		}
	}
	
	public void waitFindingReturningFlights() {
		this.waitElementToDissapear(findingReturnFlightsMessage);
	}
	
	public List<Integer> getFlightsDuration() {
		List<Integer>  result = new ArrayList<Integer>();
		List<WebElement> flightsList = this.getDriver().findElements(By.xpath(FLIGHTS_RESULTS_XPATH));
		this.waitElementsToBeDisplayed(flightsList);
		for(WebElement element : flightsList) {
			String[] values = new FlightDetails(element, this.getDriver()).getFlightDuration().split("h ");
			result.add(
					Integer.valueOf(values[0]) * 60 +
					Integer.valueOf(values[1].replace("m", ""))
				);
		}
		return result;
	}
	
	public Map<String, String> clickSelectButton(int index) {
		List<WebElement> flightsList = this.getDriver().findElements(By.xpath(FLIGHTS_RESULTS_XPATH));
		FlightDetails flight = new FlightDetails(flightsList.get(index), this.getDriver());
		Map<String, String> info = new HashMap<String, String>();
		info.put("airlineName", flight.getAirlineName());
		info.put("departureTime", flight.getDepartureTime());
		info.put("arrivalTime", flight.getArrivalTime());
		flight.clickSelectButton();
		return info;
	}
	
	public boolean isNoThanksLinkDisplayed() {
		return this.isElementDisplayed(noThanksLink);
	}
	
	public void clickNoThanksLink() {
		this.clickElement(noThanksLink);
	}
	
	public void waitForSearchingMessageToDissapear() {
		this.waitElementToDissapear(searchingMessage);
	}
	
	public void waitForPuttingTogetherMessageToDissapear() {
		this.waitElementToDissapear(puttingTogetherMessage);
	}
}