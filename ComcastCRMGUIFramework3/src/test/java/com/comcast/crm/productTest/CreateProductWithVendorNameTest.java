package com.comcast.crm.productTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateProductWithVendorNameTest 
{
	public static void main(String[] args) throws Exception
	{
		//creating object
		FileUtility fUtil=new FileUtility();
		ExcelUtility eUtil=new ExcelUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		//read common data from properties file
		String BROWSER =fUtil.getDataFromPropertiesFile("browser");
		String URL= fUtil.getDataFromPropertiesFile("url");
		String USERNAME=fUtil.getDataFromPropertiesFile("username");
		String PASSWORD= fUtil.getDataFromPropertiesFile("password");
		

		//reading data from excel file
		String productName= eUtil.getDataFromExcel("product", 7, 1)+ jUtil.getRandomNumber();
		String handler=eUtil.getDataFromExcel("product", 7, 2);
		String salesStartDate=jUtil.getSystemDateYYYYMMDD();
		String  salesEndDate= jUtil.getRequiredDateYYYYMMDD(32);
		String vendorName= eUtil.getDataFromExcel("product", 7, 5)+ jUtil.getRandomNumber();
		
		WebDriver driver=null;
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new SafariDriver();
		}
		wUtil.waitForPageToLoad(driver);
		wUtil.maximizeWindow(driver);;
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		Actions a=new Actions(driver);
		WebElement more=driver.findElement(By.linkText("More"));
		a.moveToElement(more).perform();
		
		driver.findElement(By.linkText("Vendors")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		driver.findElement(By.name("vendorname")).sendKeys(vendorName);
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("productname")).sendKeys(productName);
		
		driver.findElement(By.xpath("//input[@value='T']")).click();
		WebElement drpdwn=driver.findElement(By.xpath("//select[@name='assigned_group_id']"));
		wUtil.select(drpdwn, handler);
		driver.findElement(By.id("jscal_field_sales_start_date")).sendKeys(salesStartDate);
		driver.findElement(By.id("jscal_field_sales_end_date")).sendKeys(salesEndDate);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		wUtil.switchToTabOnURL(driver, "Vendors&action");
		
		//String vendorName= eUtil.getDataFromExcel("product", 7, 5)+ jUtil.getRandomNumber();
		driver.findElement(By.name("search_text")).sendKeys(vendorName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+vendorName+"']")).click();//dynamic xpath
		
		wUtil.switchToTabOnURL(driver, "module=Products");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		//verification
		String actualHeadder=driver.findElement(By.className("lvtHeaderText")).getText();
		if(actualHeadder.contains(productName))
		{
			System.out.println(productName+" is verified----PASS");
		}
		else
		{
			System.out.println(productName+" is not verified----FAIL");
		}
		String actualProductInfo=driver.findElement(By.id("dtlview_Product Name")).getText();
		if(actualProductInfo.equals(productName))
		{
			System.out.println(productName+" is correct----PASS");
		}
		else
		{
			System.out.println(productName+" is not correct----FAIL");
		}
		
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out"));
		driver.quit();
	}

}
