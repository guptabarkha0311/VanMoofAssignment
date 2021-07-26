package com.cucumber.framework.configreader;

import java.util.Properties;

import com.cucumber.framework.configuration.browser.BrowserType;
import com.cucumber.framework.interfaces.ConfigReader;
import com.cucumber.framework.utility.ResourceHelper;

public class PropertyFileReader implements ConfigReader {
	
	private Properties prop = null;

	public PropertyFileReader() {
		prop = new Properties();
		try {
			prop.load(ResourceHelper
					.getResourcePathInputStream("/configfile/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getWebsite() {
		return prop.getProperty("Website");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplcitWait"));
	}

	public BrowserType getBrowser() {
		return BrowserType.valueOf(prop.getProperty("Browser"));
	}
}
