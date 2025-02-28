package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
		//@FindBy(xpath="//td[@class='tabUnSelected']/a[text()='Organizations']")
		@FindBy(linkText = "Organizations")
		private WebElement orgLink;
	
		@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
		private WebElement adminImage;
		
		@FindBy(linkText = "Sign Out")
		private WebElement logOut;
		
		@FindBy(linkText = "Contacts")
		private WebElement contactLink;
		
		@FindBy(linkText = "Leads")
		private WebElement leadLink;
		
		
		
		
		@FindBy(linkText = "Email")
		private WebElement emailLink;


		@FindBy(linkText = "Campaigns")
		private WebElement campaignlink;
		
		@FindBy(linkText = "More")
		private WebElement morelink;
		
		@FindBy(linkText = "Vendors")
		private WebElement vendorslink;
		
		WebDriver driver;
		
		public HomePage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		
		public WebElement getCampaignlink() {
			return campaignlink;
		}

		public WebElement getMorelink() {
			return morelink;
		}

		public WebElement getOrgLink() {
			return orgLink;
		}


		public WebElement getContactLink() {
			return contactLink;
		}

		public WebElement getLeadLink() {
			return leadLink;
		}

		public WebDriver getDriver() {
			return driver;
		}
		
		
		//Buisness Library
		public void navigateToCampaignPage()
		{
			Actions a=new Actions(driver);
			a.moveToElement(morelink).perform();
			campaignlink.click();
		}
		
		public void navigateToVendorsPage()
		{
			Actions a=new Actions(driver);
			a.moveToElement(morelink).perform();
			vendorslink.click();
		}
		
		
		
		
		public void logout()
		{
			Actions a=new Actions(driver);
			a.moveToElement(adminImage).perform();
			logOut.click();
		}

}

