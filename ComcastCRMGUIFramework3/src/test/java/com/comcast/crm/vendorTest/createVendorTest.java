package com.comcast.crm.vendorTest;

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

public class createVendorTest 
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
		String vendorName= eUtil.getDataFromExcel("vendor", 2, 1)+ jUtil.getRandomNumber();
		String email=eUtil.getDataFromExcel("vendor", 2, 2);
		String phoneNum=eUtil.getDataFromExcel("vendor", 2, 3);
		String country=eUtil.getDataFromExcel("vendor", 2, 4);
		String city=eUtil.getDataFromExcel("vendor", 2, 5);
		String description=eUtil.getDataFromExcel("vendor", 2, 6);
		
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
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("phone")).sendKeys(phoneNum);
		driver.findElement(By.name("city")).sendKeys(city);
		driver.findElement(By.name("country")).sendKeys(country);
		driver.findElement(By.name("description")).sendKeys(description);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		
		
		
		String actualHedder=driver.findElement(By.className("lvtHeaderText")).getText();
		if(actualHedder.contains(vendorName))
		{
			System.out.println(vendorName+ " is verified====PASS");
		}
		else
		{
			System.out.println(vendorName+" is not verifies===FAIL");
		}
		
		//verify header vendor name info expected result
				String actualvendorInfo=driver.findElement(By.id("dtlview_Vendor Name")).getText();
				if(actualvendorInfo.equals(vendorName))
				{
					System.out.println(vendorName+ " information is created successfully===PASS");
				}
				else
				{
					System.out.println(vendorName+ " information is not created===FAIL");
				}
				
				WebElement userIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				a.moveToElement(userIcon).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				
				Thread.sleep(2000);
				driver.quit();
	}

}
