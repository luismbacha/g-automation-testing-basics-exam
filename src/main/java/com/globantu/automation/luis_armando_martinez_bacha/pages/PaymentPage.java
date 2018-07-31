package com.globantu.automation.luis_armando_martinez_bacha.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

	@FindBy(xpath="//h2[text()=\"Who's traveling?\"]")
	private WebElement whosTravelingMessage;
	
	@FindBy(id="totalPriceForPassenger1")
	private WebElement totalPricePassenger1;
	
	@FindBy(xpath="//span[@class='description ' and contains(text(),'Flight')]/following-sibling::span[@class='amount ']")
	private WebElement basePricePassenger1;
	
	@FindBy(xpath="//span[text()='Taxes & Fees']/following-sibling::span[@class='amount ']")
	private WebElement taxesTotal;
	
	@FindBy(xpath="//span[contains(text(),'Travelocity Booking Fee')]/following-sibling::span[@class='amount']")
	private WebElement bookingFee;
	
	@FindBy(id="totalPriceForTrip")
	private WebElement priceTotal;
	
	@FindBy(xpath="//h1[@class='flights']")
	private WebElement headerMessage;
	
	@FindBy(xpath="//div/h3[@class='module-subtitle']/span[not(@class='visuallyhidden')]")
	private WebElement flightAirportsDetails;
	
	@FindBy(xpath="(//h2[@class='title-main'])[1]")
	private WebElement whosFlyingHeader;
	
	@FindBy(xpath="(//h2[@class='title-main'])[2]")
	private WebElement whosCheckingInHeader;
	
	@FindBy(xpath="(//h2[@class='title-main'])[3]")
	private WebElement protectYourTripHeader;

	public PaymentPage(WebDriver driver) {
		super(driver);
	}
	
	public PaymentPage(WebDriver driver, boolean isPopUp) {
		super(driver);
		if(isPopUp) {
			for(String handle : driver.getWindowHandles()) {
				driver.switchTo().window(handle);
				if(driver.getTitle().equals("Travelocity: Payment"))
					break;
				if(driver.getTitle().isEmpty()) {
					this.waitForPageTitle("Travelocity: Payment");
					break;
				}
			}
		}
	}
	public boolean isWhosTravelingMessageDisplayed() {
		return this.isElementDisplayed(whosTravelingMessage);
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
	
	public String getHeaderMessage() {
		return getText(headerMessage);
	}
	
	public String getFlightAirportsDetails() {
		return getText(flightAirportsDetails);
	}
	
	public String getWhosFlyingHeader() {
		return getText(whosFlyingHeader);
	}
	
	public String getWhosCheckingInHeader() {
		return getText(whosCheckingInHeader);
	}
	
	public String getProtectYourTripHeader() {
		return getText(protectYourTripHeader);
	}

}