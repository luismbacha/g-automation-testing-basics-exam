package com.globantu.automation.luis_armando_martinez_bacha;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	static public WebDriver getDriver(Browser browser, String driverLocation) {
		switch(browser) {
			case CHROME:
				System.setProperty("webdriver.chrome.driver", driverLocation);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				return new ChromeDriver(options);
			case FIREFOX:
			default:
				System.setProperty("webdriver.gecko.driver", driverLocation);
				return new FirefoxDriver();
		}
	}
	
}
