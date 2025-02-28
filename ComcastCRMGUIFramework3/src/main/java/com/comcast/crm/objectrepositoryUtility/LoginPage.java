package com.comcast.crm.objectrepositoryUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
/**
 * @author rukhs
 * contains Login page elements and business libraries like login()
 */

//Rule1: create the seperate java class 
public class LoginPage extends WebDriverUtility
{
	//Rule2: Identify element by using @FindBy annotation
	@FindBy(name="user_name")
	private WebElement usernameEdit;
	
	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;

	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Rule:3 Object Inialization
	
	
	//Rule:4 Object Encapsulation
	
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	
	/**
	 * This method used to login to application based on url,username and password
	 * @param url
	 * @param username
	 * @param paswword
	 */
	//Rule:5 provide Actions
	public void loginToApp(String username,String paswword)
	{
		//waitForPageToLoad(driver);
		//driver.manage().window().maximize();
		//driver.get(url);
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(paswword);
		loginButton.click();
	}
	
	
	
	
	

}
