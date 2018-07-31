package com.globantu.automation.luis_armando_martinez_bacha.pageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightDetails extends BaseWebObject {
	
	private WebElement container;
	
	private HashMap<String, WebElement> details;
	
	private final String DETAILS_LINK_KEY = "detailsLink";
	private final String FLIGHT_DURATION_KEY = "flightDuration";
	private final String SELECT_BUTTON_KEY = "selectButton";
	private final String AIRLINE_NAME_KEY = "airlineName";
	private final String DEPARTURE_TIME_KEY = "departureTime";
	private final String ARRIVAL_TIME_KEY = "arrivalTime";
	
	private final String DETAILS_LINK_XPATH = ".//a[@data-test-id='flight-details-link']";
	private final String FLIGHT_DURATION_XPATH = ".//*[@data-test-id='duration']";
	private final String SELECT_BUTTON_XPATH = ".//button[@data-test-id='select-button']";
	private final String AIRLINE_NAME_XPATH = ".//*[@data-test-id='airline-name']";
	private final String DEPARTURE_TIME_XPATH = ".//*[@data-test-id='departure-time']";
	private final String ARRIVAL_TIME_XPATH = ".//*[@data-test-id='arrival-time']";
	
	public FlightDetails(WebElement container, WebDriver driver) {
		super(driver);
		this.container = container;
		details = new HashMap<String, WebElement>();
	}
	
	public void clickDetails() {
		if(!details.containsKey(DETAILS_LINK_KEY))
			details.put(DETAILS_LINK_KEY, container.findElement(By.xpath(DETAILS_LINK_XPATH)));
		this.clickElement(details.get(DETAILS_LINK_KEY));
	}
	
	public String getFlightDuration() {
		if(!details.containsKey(FLIGHT_DURATION_KEY))
			details.put(FLIGHT_DURATION_KEY, container.findElement(By.xpath(FLIGHT_DURATION_XPATH)));
		return this.getText(details.get(FLIGHT_DURATION_KEY));
	}
	
	public void clickSelectButton() {
		if(!details.containsKey(SELECT_BUTTON_KEY))
			details.put(SELECT_BUTTON_KEY, container.findElement(By.xpath(SELECT_BUTTON_XPATH)));
		this.clickElement(details.get(SELECT_BUTTON_KEY));
	}
	
	public String getAirlineName() {
		if(!details.containsKey(AIRLINE_NAME_KEY))
			details.put(AIRLINE_NAME_KEY, container.findElement(By.xpath(AIRLINE_NAME_XPATH)));
		return this.getText(details.get(AIRLINE_NAME_KEY));
	}
	
	public String getDepartureTime() {
		if(!details.containsKey(DEPARTURE_TIME_KEY))
			details.put(DEPARTURE_TIME_KEY, container.findElement(By.xpath(DEPARTURE_TIME_XPATH)));
		return this.getText(details.get(DEPARTURE_TIME_KEY));
	}

	public String getArrivalTime() {
		if(!details.containsKey(ARRIVAL_TIME_KEY))
			details.put(ARRIVAL_TIME_KEY, container.findElement(By.xpath(ARRIVAL_TIME_XPATH)));
		return this.getText(details.get(ARRIVAL_TIME_KEY));
	}

}
