package com.globantu.automation.luis_armando_martinez_bacha.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CruisesSearchResultsPage extends BasePage {

	@FindBy(xpath="//div/p[text()='Finding available cruises for your trip']")
	private WebElement findingCruisesMessage;
	
	@FindBy(id="destination-select")
	private WebElement destinationLink;
	
	@FindBy(id="length-10-14")
	private WebElement lenght1014RadioButton;
	
	@FindBy(xpath="//button[@class='btn-secondary btn-sub-action show-dates-button']")
	private List<WebElement> showDatesButtonsList;
	
	@FindBy(className="ports-toggle")
	private WebElement showItineraryLink;
	
	@FindBy(xpath="//ul[@class='secondary port-list-wrap']/li")
	private List<WebElement> itineraryList;
	
	public CruisesSearchResultsPage(WebDriver driver) {
		super(driver);
	}

	public void waitForFindingCruisesMessageToDissapear() {
		waitElementToDissapear(findingCruisesMessage);
	}
	
	public String getDestinationLinkText() {
		return getText(destinationLink);
	}
	
	public void clickLenght1014RadioButton() {
		clickElement(lenght1014RadioButton);
	}
	
	public void waitLenght1014RadioButtonIsClickable() {
		waitElementToBeClickable(lenght1014RadioButton);
	}
	
	public void clickShowDatesButton(int index) {
		clickElement(showDatesButtonsList.get(index));
	}
	
	public void clickShowItineraryLink() {
		clickElement(showItineraryLink);
	}
	
	public int getItineraryListSize() {
		return itineraryList.size();
	}
	
}
