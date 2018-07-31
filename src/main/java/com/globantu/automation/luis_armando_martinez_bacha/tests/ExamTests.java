package com.globantu.automation.luis_armando_martinez_bacha.tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.globantu.automation.luis_armando_martinez_bacha.pages.BookHotelPage;
import com.globantu.automation.luis_armando_martinez_bacha.pages.CruisesSearchResultsPage;
import com.globantu.automation.luis_armando_martinez_bacha.pages.FlightSearchResultsPage;
import com.globantu.automation.luis_armando_martinez_bacha.pages.FlightTripDetailsPage;
import com.globantu.automation.luis_armando_martinez_bacha.pages.HomePage;
import com.globantu.automation.luis_armando_martinez_bacha.pages.HotelsSearchResultsPage;
import com.globantu.automation.luis_armando_martinez_bacha.pages.PackageSearchResultsPage;
import com.globantu.automation.luis_armando_martinez_bacha.pages.PackageTripDetailsPage;
import com.globantu.automation.luis_armando_martinez_bacha.pages.PaymentPage;

public class ExamTests extends BaseTests {
	
	@Test
	public void HP_booking_flight_with_credit_card() {
		
		Calendar calendar = Calendar.getInstance();
		Map<String, String> departureFlightInfo;
		Map<String, String> arrivalFlightInfo;
		
		HomePage homePage = new HomePage(this.getDriver());
		
		//S T E P   1
		homePage.clickOnFlightsButton();
		homePage.clickOnRoundtripButton();
		homePage.typeIntoFlightFlyingFromInput("LAS");
		homePage.typeIntoFlightFlyingToInput("LAX");
		homePage.clickOnFlightDepartingDateInput();
		homePage.clickOnNextMonthButton();
		
		calendar.add(Calendar.MONTH, 2);
		homePage.clickOnDay(
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH));
		
		homePage.clickOnFlightReturningDateInput();
		if(homePage.isNextMonthButtonDisplayed())
			homePage.clickOnNextMonthButton();
		
		calendar.add(Calendar.DAY_OF_MONTH, 23);
		homePage.clickOnDay(
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH));
		homePage.clickOnFlightSearchButton();
		
		//S T E P   2
		FlightSearchResultsPage searchResultsPage = new FlightSearchResultsPage(this.getDriver());
		Assert.assertTrue(searchResultsPage.getCurrentUrl().contains("https://www.travelocity.com/Flights-Search?flight-type=on&starDate="));
		Assert.assertEquals(searchResultsPage.getPageTitle(), "LAS to LAX Flights | Travelocity");
		Assert.assertEquals(searchResultsPage.getDepartureAirportInputValue(), "Las Vegas (LAS)");
		Assert.assertEquals(searchResultsPage.getArrivalAirportInputValue(), "Los Angeles, CA, United States (LAX)");
		Assert.assertEquals(searchResultsPage.getTitleCityText(), "Select your departure to Los Angeles");
		
		//S T E P   3
		searchResultsPage.clickFlightDetailsLinks();
		
		//S T E P   4
		searchResultsPage.selectSortByOption("Duration (Shortest)");
		searchResultsPage.waitSortingByMessage();
		
		List<Integer> results = searchResultsPage.getFlightsDuration();
		for(int i = 1; i < results.size(); i++) {
			Assert.assertTrue(results.get(i-1) <= results.get(i));
		}
		
		//S T E P   5
		departureFlightInfo = searchResultsPage.clickSelectButton(0);
		searchResultsPage.waitFindingReturningFlights();
		Assert.assertTrue(searchResultsPage.getCurrentUrl().contains("https://www.travelocity.com/Flights-Search?flight-type=on&starDate="));
		Assert.assertEquals(searchResultsPage.getPageTitle(), "LAS to LAX Flights | Travelocity");
		Assert.assertEquals(searchResultsPage.getDepartureAirportInputValue(), "Las Vegas (LAS)");
		Assert.assertEquals(searchResultsPage.getArrivalAirportInputValue(), "Los Angeles, CA, United States (LAX)");
		Assert.assertEquals(searchResultsPage.getTitleCityText(), "Select your return to Las Vegas");
		
		//S T E P   6
		arrivalFlightInfo = searchResultsPage.clickSelectButton(2);
		if(searchResultsPage.isNoThanksLinkDisplayed())
			searchResultsPage.clickNoThanksLink();
		
		//S T E P   7
		FlightTripDetailsPage tripDetailsPage = new FlightTripDetailsPage(this.getDriver());
		Assert.assertTrue(tripDetailsPage.isReviewYouTripMessageDisplayed());
		Assert.assertEquals(tripDetailsPage.getDepartureFromAirport(), "McCarran Intl. (LAS)");
		Assert.assertEquals(tripDetailsPage.getDepartureToAirport(), "Los Angeles Intl. (LAX)");
		Assert.assertEquals(tripDetailsPage.getDepartureAirline(), departureFlightInfo.get("airlineName"));
		Assert.assertEquals(tripDetailsPage.getDepartureStartTime(), departureFlightInfo.get("departureTime"));
		Assert.assertEquals(tripDetailsPage.getDepartureEndTime(), departureFlightInfo.get("arrivalTime"));
		Assert.assertEquals(tripDetailsPage.getArrivalFromAirport(), "Los Angeles Intl. (LAX)");
		Assert.assertEquals(tripDetailsPage.getArrivalToAirport(), "McCarran Intl. (LAS)");
		Assert.assertEquals(tripDetailsPage.getArrivalAirline(), arrivalFlightInfo.get("airlineName"));
		Assert.assertEquals(tripDetailsPage.getArrivalStartTime(), arrivalFlightInfo.get("departureTime"));
		Assert.assertEquals(tripDetailsPage.getArrivalEndTime(), arrivalFlightInfo.get("arrivalTime"));
		
		//S T E P   8
		Assert.assertTrue(tripDetailsPage.isTripSummaryHeaderDisplayed());
		Assert.assertTrue(tripDetailsPage.isToggleButtonDisplayed());
		Assert.assertEquals(tripDetailsPage.getPassenger1Text(), "Adult");
		Assert.assertTrue(tripDetailsPage.isTotalPricePassenger1Displayed());
		Assert.assertTrue(tripDetailsPage.isPriceTotalDisplayed());
		float totalPricePassenger = Math.round((Float.valueOf(tripDetailsPage.getTotalPricePassenger1().replaceAll("[$,]", ""))*100.0))/((float)100.0);
		float basePricePassenger = Math.round(Float.valueOf(tripDetailsPage.getBasePricePassenger1().replaceAll("[$,]", ""))*100.0)/((float)100.0);
		float taxesTotal = Math.round(Float.valueOf(tripDetailsPage.getTaxesTotal().replaceAll("[$,]", ""))*100.0)/((float)100.0);
		float bookingFee = Math.round(Float.valueOf(tripDetailsPage.getBookingFee().replaceAll("[$,]", ""))*100.0)/((float)100.0);
		float priceTotal = Math.round(Float.valueOf(tripDetailsPage.getPriceTotal().replaceAll("[$,]", ""))*100.0)/((float)100.0);
		Assert.assertEquals(Math.round((basePricePassenger + taxesTotal)*100)/((float)100.0), totalPricePassenger);
		Assert.assertEquals(Math.round((totalPricePassenger + bookingFee)*100)/((float)100.0), priceTotal);
		
		//S T E P   9
		tripDetailsPage.clickBookButton();
		tripDetailsPage.waitForProcessingMessage();
		
		//S T E P  10
		PaymentPage paymentPage = new PaymentPage(this.getDriver());
		Assert.assertTrue(paymentPage.isTotalPricePassenger1Displayed());
		Assert.assertTrue(paymentPage.isPriceTotalDisplayed());
		totalPricePassenger = Math.round(Float.valueOf(paymentPage.getTotalPricePassenger1().replaceAll("[$,]", ""))*100.0)/((float)100.0);
		basePricePassenger = Math.round(Float.valueOf(paymentPage.getBasePricePassenger1().replaceAll("[$,]", ""))*100.0)/((float)100.0);
		taxesTotal = Math.round(Float.valueOf(paymentPage.getTaxesTotal().replaceAll("[$,]", ""))*100.0)/((float)100.0);
		bookingFee = Math.round(Float.valueOf(paymentPage.getBookingFee().replaceAll("[$,]", ""))*100.0)/((float)100.0);
		priceTotal = Math.round(Float.valueOf(paymentPage.getPriceTotal().replaceAll("[$,]", ""))*100.0)/((float)100.0);
		Assert.assertEquals(Math.round((basePricePassenger + taxesTotal)*100)/((float)100.0), totalPricePassenger);
		Assert.assertEquals(Math.round((totalPricePassenger + bookingFee)*100)/((float)100.0), priceTotal);
	}
	
	@Test
	public void HP_booking_flight_hotel_and_car_with_credit_card() {

		Calendar calendar = Calendar.getInstance();
		Map<String, String> departureFlightInfo;
		Map<String, String> arrivalFlightInfo;
		
		HomePage homePage = new HomePage(this.getDriver());
		
		//S T E P   1
		homePage.clickOnFlightHotelButton();
		Assert.assertTrue(homePage.isFlightHotelButtonDisplayed());
		Assert.assertTrue(homePage.isFlightHotelCarButtonDisplayed());
		Assert.assertTrue(homePage.isFlightCarButtonDisplayed());
		Assert.assertTrue(homePage.isHotelCarButtonDisplayed());

		//S T E P   2
		homePage.typeIntoPackageFlyingFromInput("LAS");
		homePage.typeIntoPackageFlyingToInput("LAX");
		homePage.clickOnPackageDepartingDateInput();
		homePage.clickOnNextMonthButton();
		
		calendar.add(Calendar.MONTH, 2);
		homePage.clickOnDay(
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH));
		
		homePage.clickOnPackageReturningDateInput();
		if(homePage.isNextMonthButtonDisplayed())
			homePage.clickOnNextMonthButton();
		
		calendar.add(Calendar.DAY_OF_MONTH, 13);
		homePage.clickOnDay(
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH));
		homePage.selectAdults(1);
		homePage.clickOnPackageSearchButton();
		
		//S T E P   3
		PackageSearchResultsPage searchResultsPage = new PackageSearchResultsPage(this.getDriver());
		searchResultsPage.waitForInterstitialMessageToDissapear();
		searchResultsPage.waitForPageTitle("Los Angeles (and vicinity) Hotel Search Results | Travelocity");
		Assert.assertTrue(searchResultsPage.getCurrentUrl().contains("https://www.travelocity.com/Hotel-Search?packageType=fh"));
		Assert.assertEquals(searchResultsPage.getPageTitle(), "Los Angeles (and vicinity) Hotel Search Results | Travelocity");
		Assert.assertEquals(searchResultsPage.getTitleHotelText(), "Start by choosing your hotel");
		Assert.assertEquals(searchResultsPage.getOriginFakeLink(), "Las Vegas, NV, United States (LAS-All Airports)");
		Assert.assertEquals(searchResultsPage.getDestinationFakeLink(), "Los Angeles (and vicinity), California, United States Of America");
		
		//S T E P   4
		searchResultsPage.clickSortByPriceButton();
		searchResultsPage.waitForPageToLoad();
		searchResultsPage.waitForUpdatingResultsMessageToDissapear();
		
		List<Float> results = searchResultsPage.getHotelPrices();
		for(int i = 1; i < results.size(); i++) {
			Assert.assertTrue(results.get(i-1) <= results.get(i));
		}
		
		//S T E P   5
		Map<String, String> selectedHotelInfo = searchResultsPage.clickFirstResultsWithStars(3);
		
		//S T E P   6
		BookHotelPage bookHotelPage = new BookHotelPage(this.getDriver());
		bookHotelPage.waitForGettingOptionsMessageToDissapear();
		String urlStr = bookHotelPage.getHotelName().replaceAll("[^a-zA-Z0-9- ]", "").replaceAll("[- ]+", "-");
		Assert.assertTrue(bookHotelPage.getCurrentUrl().contains(urlStr));
		Assert.assertTrue(bookHotelPage.getPageTitle().contains("Book " + selectedHotelInfo.get("hotelName")));
		Assert.assertEquals(bookHotelPage.getHotelName(), selectedHotelInfo.get("hotelName"));
		Assert.assertEquals(bookHotelPage.getHotelStars(), selectedHotelInfo.get("hotelStars"));
		
		//S T E P   7
		bookHotelPage.clickFirstRoomOptionSelectButton();
		
		//S T E P   8
		FlightSearchResultsPage flightResultsPage = new FlightSearchResultsPage(this.getDriver());
		flightResultsPage.waitForSearchingMessageToDissapear();
		departureFlightInfo = flightResultsPage.clickSelectButton(0);
		flightResultsPage.waitFindingReturningFlights();
		
		//S T E P   9
		arrivalFlightInfo = flightResultsPage.clickSelectButton(2);
		flightResultsPage.waitForPuttingTogetherMessageToDissapear();
		
		//S T E P  10
		//Can't select a car by following previous steps
		
		//S T E P  11
		PackageTripDetailsPage tripDetailsPage = new PackageTripDetailsPage(this.getDriver());
		Assert.assertEquals(tripDetailsPage.getReviewYourTripMessage(), "Your trip to Los Angeles, CA");
		Assert.assertEquals(tripDetailsPage.getDepartureFlightAirlineName(), departureFlightInfo.get("airlineName"));
		Assert.assertEquals(tripDetailsPage.getArrivalFlightAirlineName(), arrivalFlightInfo.get("airlineName"));
		Assert.assertEquals(tripDetailsPage.getHotelName(), selectedHotelInfo.get("hotelName"));
		tripDetailsPage.clickContinueBookingButton();
		tripDetailsPage.waitForProcessingMessage();
		
		//S T E P  12
		//There is not another page before payment page
		
		//S T E P  13
		PaymentPage paymentPage = new PaymentPage(this.getDriver(), true);
		paymentPage.waitForPageTitle("Travelocity: Payment");
		paymentPage.waitForPageToLoad();
		Assert.assertEquals(paymentPage.getHeaderMessage(), "Review and book");
		Assert.assertEquals(paymentPage.getFlightAirportsDetails(), "Las Vegas (LAS) to Los Angeles (LAX)");
		Assert.assertEquals(paymentPage.getWhosFlyingHeader(), "Who's flying?");
		Assert.assertEquals(paymentPage.getWhosCheckingInHeader(), "Who's checking in?");
		Assert.assertEquals(paymentPage.getProtectYourTripHeader(), "Protect your trip (recommended)");
	}
	
	@Test
	@Parameters({"hotelName"})
	public void HP_verify_search_by_hotel_name(String hotelName) {
		
		HomePage homePage = new HomePage(getDriver());
		Calendar calendar = Calendar.getInstance();
		
		//S T E P   1
		homePage.clickOnHotelsButton();
		
		//S T E P   2
		homePage.typeIntoHotelDestinationInput(hotelName);
		
		homePage.clickOnCheckinDateInput();
		
		homePage.clickOnNextMonthButton();
		calendar.add(Calendar.MONTH, 2);
		homePage.clickOnDay(
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH));
		
		homePage.clickOnCheckoutDateInput();
		if(homePage.isNextMonthButtonDisplayed())
			homePage.clickOnNextMonthButton();
		
		calendar.add(Calendar.DAY_OF_MONTH, 4);
		homePage.clickOnDay(
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH));
		
		homePage.clickOnHotelsSearchButton();
		
		//S T E P   3
		HotelsSearchResultsPage searchResultsPage = new HotelsSearchResultsPage(getDriver());
		searchResultsPage.waitForWaitMessageToDissapear();
		searchResultsPage.waitForSectionHeader();
		Assert.assertTrue(searchResultsPage.isResultPresent(hotelName));
		
	}
	
	@Test
	public void ES_verify_error_message_for_incorrect_hotel_dates() {
		
		Calendar calendar = Calendar.getInstance();
		HomePage homePage = new HomePage(this.getDriver());
		
		//S T E P   1
		homePage.clickOnFlightHotelButton();

		//S T E P   2
		homePage.typeIntoPackageFlyingFromInput("LAS");
		homePage.typeIntoPackageFlyingToInput("LAX");
		homePage.clickOnPackageDepartingDateInput();
		homePage.clickOnNextMonthButton();
		
		calendar.add(Calendar.MONTH, 2);
		homePage.clickOnDay(
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH));
		
		homePage.clickOnPackageReturningDateInput();
		if(homePage.isNextMonthButtonDisplayed())
			homePage.clickOnNextMonthButton();
		
		calendar.add(Calendar.DAY_OF_MONTH, 13);
		homePage.clickOnDay(
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH));
		homePage.selectAdults(1);
		
		//S T E P   3
		homePage.clickPartialHotelBookingCheckbox();
		
		//S T E P   4
		homePage.clickOnHotelCheckInDateInput();
		calendar.add(Calendar.DAY_OF_MONTH, -3);
		homePage.clickOnDay(
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH));
		
		homePage.clickOnHotelCheckOutDateInput();
		calendar.add(Calendar.DAY_OF_MONTH, 5);
		homePage.clickOnDay(
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH));
		
		homePage.clickOnPackageSearchButton();
		
		//S T E P   5
		Assert.assertEquals(
				homePage.getErrorMessage(),
				"Your partial check-in and check-out dates must fall within "
				+ "your arrival and departure dates. Please review your dates.");
		
	}
	
	@Test
	@Parameters({"destinationValue"})
	public void HP_verify_cruises_information(String destinationValue) {
		
		Calendar calendar = Calendar.getInstance();
		HomePage homePage = new HomePage(this.getDriver());
		
		//S T E P   1
		homePage.clickOnCruisesButton();
		
		//S T E P   2
		homePage.selectCruiseDestination(destinationValue);
		
		//S T E P   3
		calendar.add(Calendar.MONTH, 3);
		homePage.selectCruiseDepartureMonth(new SimpleDateFormat("MMM yyyy").format(calendar.getTime()));
		homePage.clickCruisesSearchButton();
		
		CruisesSearchResultsPage resultsPage = new CruisesSearchResultsPage(getDriver());
		resultsPage.waitForFindingCruisesMessageToDissapear();
		
		//S T E P   4
		Assert.assertEquals(resultsPage.getDestinationLinkText(), destinationValue);
		
		//S T E P   5
		resultsPage.clickLenght1014RadioButton();
		resultsPage.waitLenght1014RadioButtonIsClickable();
		
		//S T E P   6
		resultsPage.clickShowDatesButton(0);
		resultsPage.clickShowItineraryLink();
		
		//S T E P   7
		Assert.assertTrue((resultsPage.getItineraryListSize() >= 10) && (resultsPage.getItineraryListSize() <= 14));
		
	}
}