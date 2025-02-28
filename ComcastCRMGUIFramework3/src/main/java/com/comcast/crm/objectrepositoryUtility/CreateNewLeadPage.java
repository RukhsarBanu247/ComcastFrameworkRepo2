package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateNewLeadPage extends WebDriverUtility
{
	@FindBy(name="lastname")
	private WebElement leadLastName;

	public WebElement getLeadLastName() {
		return leadLastName;
	}
	
	@FindBy(name="company")
	private WebElement leadCompany;

	public WebElement getLeadCompany() {
		return leadCompany;
	}
	
	@FindBy(xpath = "//input[@value='T']")
	private WebElement groupRadioButton;

	public WebElement getGroupRadioButton() {
		return groupRadioButton;
	}
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getSelectDrpDwn() {
		return selectDrpDwn;
	}

	public WebDriver getDriver() {
		return driver;
	}

	@FindBy(name="assigned_group_id")
	private WebElement selectDrpDwn;
	
	public WebElement getLeadsrcdrpdwn() {
		return leadsrcdrpdwn;
	}

	@FindBy(name="leadsource")
	private WebElement leadsrcdrpdwn;
	
	
	WebDriver driver=null;
	
	@FindBy(className ="dvHeaderText")
	private WebElement actualHeadder;
	
	public WebElement getActualLeadName() {
		return actualLeadName;
	}

	@FindBy(id="dtlview_Last Name")
	private WebElement actualLeadName;
	
	public WebElement getAnnualRev() {
		return annualRev;
	}

	@FindBy(name="annualrevenue")
	private WebElement annualRev;
	
	public WebElement getActualHeadder() {
		return actualHeadder;
	}

	public CreateNewLeadPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void createLead(String lastName,String company,String assignedTo)
	{
		leadLastName.sendKeys(lastName);
		leadCompany.sendKeys(company);
		groupRadioButton.click();
		select(selectDrpDwn, assignedTo);
		saveBtn.click();
		
	}
	public void createLeadWithAnnualRevenue(String lastName,String company,String annualRevenue,String assignedTo)
	{
		leadLastName.sendKeys(lastName);
		leadCompany.sendKeys(company);
		groupRadioButton.click();
		select(selectDrpDwn, assignedTo);
		annualRev.sendKeys(annualRevenue);
		saveBtn.click();
	}
	public void createLeadWithLeadSource(String lastName,String company,String assignedTo,String leadSource)
	{
		leadLastName.sendKeys(lastName);
		leadCompany.sendKeys(company);
		groupRadioButton.click();
		select(selectDrpDwn, assignedTo);
		select(leadsrcdrpdwn,leadSource);
		saveBtn.click();
		
	}
	

}
