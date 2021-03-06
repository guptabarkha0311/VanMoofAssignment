package com.cucumber.framework.interfaces;

import com.cucumber.framework.configuration.browser.BrowserType;

public interface ConfigReader {
	public String getWebsite();
	public String getAPIWebsite();
	public int getPageLoadTimeOut();
	public int getImplicitWait();
	public BrowserType getBrowser();
}
