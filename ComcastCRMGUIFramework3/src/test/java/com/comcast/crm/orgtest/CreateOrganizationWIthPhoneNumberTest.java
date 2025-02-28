package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateOrganizationWIthPhoneNumberTest 
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
		String lastName= eUtil.getDataFromExcel("Org1", 7, 2)+jUtil.getRandomNumber();
		String phoneNum=eUtil.getDataFromExcel("Org1", 7, 3);
	
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
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//System.out.println(data);
		driver.findElement(By.name("accountname")).sendKeys(lastName);
		driver.findElement(By.id("phone")).sendKeys(phoneNum);
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		
		//VERIFY HEADER phone number  EXPECTED RESULT
		String actualPhoneNum=driver.findElement(By.id("dtlview_Phone")).getText();
		if(actualPhoneNum.equals(phoneNum))
		{
			System.out.println(phoneNum+ " information is created successfully===PASS");
		}
		else
		{
			System.out.println(phoneNum+ " information is not created===FAIL");
		}
		
		driver.quit();
		
	}

}
