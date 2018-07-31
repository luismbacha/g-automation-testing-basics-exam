package com.globantu.automation.luis_armando_martinez_bacha.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.globantu.automation.luis_armando_martinez_bacha.pageObjects.HotelDetails;

public class PackageSearchResultsPage extends BasePage {
	
	private final String HOTEL_DETAILS_RESULTS_XPATH = "//div[@id='resultsContainer']/section/article";
	
	@FindBy(id="resultsContainer")
	private WebElement resultsContainer;
	
	@FindBy(xpath="(//div[@id='resultsContainer']/section/article)[last()]")
	private WebElement lastResult;
	
	@FindBy(id="interstitial-message")
	private WebElement interstitialMessage;
	
	@FindBy(xpath="//h1[@class='section-header-main']")
	private WebElement titleHotelText;
	
	@FindBy(xpath="//button[@class='origin fakeLink']")
	private WebElement originFakeLink;
	
	@FindBy(xpath="//div[@class='col playback-summary-data']/button[@class='destination fakeLink']")
	private WebElement destinationFakeLink;
	
	@FindBy(xpath="//div[@class='modal-body loading']")
	private WebElement updatingResultsMessage;
	
	@FindBy(xpath="//button[./span[text()='Price']]")
	private WebElement sortByPriceButton;
	
	@FindBy(xpath="//abbr[contains(text(),'Next')]")
	private WebElement nextLink;
	
	public PackageSearchResultsPage(WebDriver driver) {
		super(driver);
	}
	
	public void waitForInterstitialMessageToDissapear() {
		this.waitElementToDissapear(interstitialMessage);
	}
	
	public String getTitleHotelText() {
		return this.getText(titleHotelText);
	}
	
	public String getOriginFakeLink() {
		return this.getText(originFakeLink);
	}
	
	public String getDestinationFakeLink() {
		return this.getText(destinationFakeLink);
	}
	
	public void clickSortByPriceButton() {
		this.clickElement(sortByPriceButton);
	}
	
	public void waitForUpdatingResultsMessageToDissapear() {
		this.waitElementToDissapear(updatingResultsMessage);
	}
	
	public List<Float> getHotelPrices() {
		List<Float> result = new ArrayList<Float>();
		List<WebElement> hotelsList = this.getDriver().findElements(By.xpath(HOTEL_DETAILS_RESULTS_XPATH));
		this.waitElementsToBeDisplayed(hotelsList);
		for(WebElement element : hotelsList) {
			result.add(
					Float.valueOf(
							new HotelDetails(element, this.getDriver()).getHotelPrice().replaceAll("[$,]", "")));
		}
		return result;
	}
	
	public Map<String, String> clickFirstResultsWithStars(float stars) {
		Map<String, String> info = null;
		this.waitForUpdatingResultsMessageToDissapear();
		List<WebElement> hotelsList = this.getDriver().findElements(By.xpath(HOTEL_DETAILS_RESULTS_XPATH));
		this.waitElementsToBeDisplayed(hotelsList);
		for(WebElement element : hotelsList) {
			HotelDetails details = new HotelDetails(element, this.getDriver());
			if(Float.valueOf(details.getHotelStars()) >= stars) {
				info = new HashMap<String, String>();
				info.put("hotelStars", details.getHotelStars());
				info.put("hotelName", details.getHotelName());
				details.clickHotelLink();
				break;
			}
		}
		if(info != null)
			return info;
		else if(this.isElementDisplayed(nextLink)) {
			this.clickElement(nextLink);
			return clickFirstResultsWithStars(stars);
		}
		else
			return null;
	}

}