package com.comcast.crm.generic.webdriverUtility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject 
{
	public static ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	//getters
	public static ExtentTest getTest() 
	{
		return test.get();
	}
	public static WebDriver getDriver()
	{
		return driver.get();
	}
	
	//setter
	public static void setTest(ExtentTest actualTest) 
	{
		test.set(actualTest);;
	}
	public static void setDriver(WebDriver actualDriver)
	{
		 driver.set(actualDriver);
	}
	
}
