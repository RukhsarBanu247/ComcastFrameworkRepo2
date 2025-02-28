package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewVendorPage
{
	@FindBy(name="vendorname")
	private WebElement vendor;

	public WebElement getVendorName() {
		return vendor;
	}
	
	@FindBy(id="email")
	private WebElement emailId;

	public WebElement getEmailId() {
		return emailId;
	}
	
	@FindBy(id="phone")
	private WebElement phone;

	public WebElement getPhoneNum() {
		return phone;
	}
	
	public WebElement getCityName() {
		return cityName;
	}

	@FindBy(id="city")
	private WebElement cityName;
	
	@FindBy(name="description")
	private WebElement desc;
	
	public WebElement getDesc() {
		return desc;
	}

	public WebElement getCountryName() {
		return countryName;
	}

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(id="country")
	private WebElement countryName;
	
	WebDriver driver=null;
	
	@FindBy(className ="lvtHeaderText" )
	private WebElement actHeadder;
	
	@FindBy(id="dtlview_Vendor Name")
	private WebElement actVendorInfo;
	
	
	
	
	public WebElement getActVendorInfo() {
		return actVendorInfo;
	}

	public WebElement getVendor() {
		return vendor;
	}

	public WebElement getPhone() {
		return phone;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getActHeadder() {
		return actHeadder;
	}

	public CreateNewVendorPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void createvendor(String vendorName)
	{
		vendor.sendKeys(vendorName);
		saveBtn.click();
	}
	public void createVendor(String vendorName,String email,String phoneNum,String city,String country,String description )
	{
		vendor.sendKeys(vendorName);
		emailId.sendKeys(email);
		phone.sendKeys(phoneNum);
		cityName.sendKeys(city);
		countryName.sendKeys(country);
		desc.sendKeys(description);
		saveBtn.click();
	}
	
	

}
