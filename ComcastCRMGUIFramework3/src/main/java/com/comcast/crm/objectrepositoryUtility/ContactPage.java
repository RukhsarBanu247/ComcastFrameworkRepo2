package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage
{
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createContactButton;

	public ContactPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateContactButton() {
		return createContactButton;
	}
	/**
	 * this business library is to perform click operation on create contact button(+ icon)
	 */
	
	public void clickOnCreateContactButton()
	{
		getCreateContactButton().click();
	}

}
