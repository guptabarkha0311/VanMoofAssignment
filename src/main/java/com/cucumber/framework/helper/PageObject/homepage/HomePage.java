package com.cucumber.framework.helper.PageObject.homepage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.cucumber.framework.helper.Logger.LoggerHelper;

import cucumber.api.Scenario;

public class HomePage {

	private WebDriver driver;
	private Scenario scenario;
	private final Logger log = LoggerHelper.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		if (driver == null)
			throw new IllegalArgumentException("Driver object is null");
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		this.driver = driver;
	}

	/** Public Methods **/

	public WebDriver getDriver() {
		return this.driver;
	}

	public String getUrl() {
		return driver.getCurrentUrl();

	}

	public List<WebElement> getLinks() throws MalformedURLException, IOException {
		return driver.findElements(By.tagName("a"));

	}

	public void accessAPIURL() {
		driver.get("https://jsonplaceholder.typicode.com/");
	}

}
