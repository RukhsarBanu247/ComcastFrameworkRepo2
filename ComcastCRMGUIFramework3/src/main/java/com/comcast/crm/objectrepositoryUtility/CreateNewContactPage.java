package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateNewContactPage 
{
	@FindBy(name="lastname")
	private WebElement lastNameEdit;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="support_start_date")
	private WebElement startDateEdit;
	
	
	@FindBy(name="support_end_date")
	private WebElement endDateEdit;

	@FindBy(name="account_name")
	private WebElement contactOrgName;
	
	@FindBy(xpath="(//img[@src='themes/softed/images/select.gif'])[1]")
	private WebElement addOrgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchField;
	
	public WebElement getSearchField() {
		return searchField;
	}
	@FindBy(name="search")
	private WebElement contactSearchBtn;
	

	public void setContactSearchBtn(WebElement contactSearchBtn) {
		this.contactSearchBtn = contactSearchBtn;
	}
	WebDriver driver;
	public CreateNewContactPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public WebElement getLastNameEdit() {
		return lastNameEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebDriver getDriver() {
		return driver;
	}
	public WebElement getStartDateEdit() {
		return startDateEdit;
	}

	public WebElement getEndDateEdit() {
		return endDateEdit;
	}
	
	public WebElement getContactOrgName() {
		return contactOrgName;
	}
	
	public WebElement getAddOrgBtn() {
		return addOrgBtn;
	}
	
	public WebElement getContactSearchBtn() {
		return contactSearchBtn;
	}
	
	public void createContact(String lastName)
	{
		lastNameEdit.sendKeys(lastName);
		saveBtn.click();
	}
	public void createContactWithSupportDate(String lastName, String startDate, String endDate)
	{
		lastNameEdit.sendKeys(lastName);
		startDateEdit.sendKeys(startDate);
		endDateEdit.clear();
		endDateEdit.sendKeys(endDate);
		saveBtn.click();
	}
	public void createContactWithOrg(String contactLastName, String orgName)
	{
		
		lastNameEdit.sendKeys(contactLastName);
		addOrgBtn.click();
		WebDriverUtility wUtil=new WebDriverUtility();
		wUtil.switchToTabOnURL(driver,"module=Account");
		searchField.sendKeys(orgName);

		contactSearchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
	
		wUtil.switchToTabOnTitle(driver, "Contacts&action");
		saveBtn.click();
		
		
	}
	
}
	
