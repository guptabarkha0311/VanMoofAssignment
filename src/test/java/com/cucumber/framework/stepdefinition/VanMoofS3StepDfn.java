
package com.cucumber.framework.stepdefinition;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.cucumber.framework.helper.PageObject.homepage.HomePage;
import com.cucumber.framework.settings.ObjectRepo;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class VanMoofS3StepDfn {

	private HomePage hPage;
	private Scenario scenario;

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	@Given("^: I am at the S3 page$")
	public void _i_am_at_the_s3_page() throws Throwable {
		ObjectRepo.driver.get(ObjectRepo.reader.getWebsite());
		hPage = new HomePage(ObjectRepo.driver);
		ObjectRepo.data.put("HomePage", hPage);
	}

	@Then("^: I verify the current url$")
	public void verify_current_url() throws Throwable {
		URI uri = new URI(hPage.getUrl());
		Assert.assertEquals(
				new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), null, uri.getFragment()).toString(),
				"https://www.vanmoof.com/en-NL/s3");

	}

	@And("^: I get all the links on the page$")
	public void get_all_links() throws Throwable {
		List<WebElement> allLinks = hPage.getLinks();
		scenario.write("Total number of Links" + allLinks.size());

	}

	@Then("^: I verify response code of all links$")
	public void response_code_all_links() throws Throwable {

		List<WebElement> invalidLinks = new ArrayList<WebElement>();
		for (WebElement link : hPage.getLinks()) {

			String url = link.getAttribute("href");
			if (StringUtils.isNotEmpty(url)) {
				HttpURLConnection c = (HttpURLConnection) new URL(link.getAttribute("href")).openConnection();
				c.setRequestMethod("HEAD");
				c.connect();
				int r = c.getResponseCode();
				if (r != 200) {
					invalidLinks.add(link);
					scenario.write("Links having invalid response code" + link.getAttribute("href"));
				}
				System.out.println("Http response code: " + r);
			}
		}
		scenario.write("Invalid number of Links with response code other than 200" + invalidLinks.size());

	}
}
