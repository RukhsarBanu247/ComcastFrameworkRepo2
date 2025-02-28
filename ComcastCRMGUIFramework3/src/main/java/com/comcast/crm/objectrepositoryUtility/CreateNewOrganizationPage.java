package com.comcast.crm.objectrepositoryUtility;

import java.nio.channels.SelectableChannel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;

public class CreateNewOrganizationPage 
{
	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDrpDwn;
	
	@FindBy(name="accounttype")
	private WebElement typedrpdwn;
	
	public WebElement getTypedrpdwn() {
		return typedrpdwn;
	}

	@FindBy(name="phone")
	private WebElement phoneNumEdit;
	
	public WebElement getIndustryDrpDwn() {
		return industryDrpDwn;
	}
	public WebElement getPhoneNumEdit() {
		return phoneNumEdit;
	}

	WebDriver driver;
	
	public CreateNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebDriver getDriver() {
		return driver;
	}
	

	
	public void createOrg(String orgName)
	{
		orgNameEdit.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrg(String orgName,String industries,String type)
	{
		orgNameEdit.sendKeys(orgName);
		Select s=new Select(industryDrpDwn);
		s.selectByVisibleText(industries);
		Select s1=new Select(typedrpdwn);
		s1.selectByVisibleText(type);
		saveBtn.click();
	}
	
	public void createOrgWithPhoneNum(String orgName,String phoneNum)
	{
		orgNameEdit.sendKeys(orgName);
		phoneNumEdit.sendKeys(phoneNum);
		saveBtn.click();
	}
}
