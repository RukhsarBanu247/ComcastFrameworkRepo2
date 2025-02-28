package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;

public class OrganizationPage 
{
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createNewOrgBtn;

	@FindBy(name="search_text")
	private WebElement searchBox;
	
	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchDrpDwn() {
		return searchDrpDwn;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebDriver getDriver() {
		return driver;
	}

	@FindBy(name="search_field")
	private WebElement searchDrpDwn;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
	WebDriver driver;
	WebDriverUtility wUtil=new WebDriverUtility();
	public OrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public void deleteOrg(String orgName)
	{
		getSearchBox().sendKeys(orgName);
		wUtil.select(getSearchDrpDwn(),"Organization Name");
		getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		wUtil.alertHandling(driver);
	}

}
