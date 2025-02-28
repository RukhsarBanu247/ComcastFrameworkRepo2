package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateNewProductPage  extends WebDriverUtility
{
	public WebElement getProductName() {
		return product;
	}

	public WebElement getSelectdrpdwn() {
		return selectdrpdwn;
	}

	public WebDriver getDriver() {
		return driver;
	}

	@FindBy(name="productname")
	private WebElement product;
	
	@FindBy(name="assigntype")
	private WebElement selectdrpdwn;
	
	@FindBy(name="sales_start_date")
	private WebElement SaleStartDate;
	
	@FindBy(name="sales_end_date")
	private WebElement SaleEndDate;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']")
	private WebElement vendorBtn;
	
	@FindBy(name="search_text")
	private WebElement searchBox;
	
	@FindBy(name = "search")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	WebDriver driver=null;
	
	public CreateNewProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void createProduct(String productName,String salesStartDate,String salesEndDate,String vendorName)
	{
		product.sendKeys(productName);
		SaleStartDate.sendKeys(salesStartDate);
		SaleEndDate.sendKeys(salesEndDate);
		//select(selectdrpdwn, handler);
		vendorBtn.click();
		switchToTabOnURL(driver, "Vendors&action");
		searchBox.sendKeys(vendorName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+vendorName+"']")).click();//dynamic xpath
		switchToTabOnURL(driver, "module=Products");
		saveBtn.click();
		
	}
	
}
