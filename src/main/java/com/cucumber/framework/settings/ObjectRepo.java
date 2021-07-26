
package com.cucumber.framework.settings;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.cucumber.framework.interfaces.ConfigReader;

public class ObjectRepo {
	
	public static WebDriver driver;
	public static ConfigReader reader;
	public static Map<String, Object> data = new LinkedHashMap<String, Object>();
	
}

