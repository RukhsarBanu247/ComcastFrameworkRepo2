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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;

public class CreateOrganizationWithIndustryTest
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
		String lastName= eUtil.getDataFromExcel("Org1", 4, 2)+ jUtil.getRandomNumber();;
		String industries= eUtil.getDataFromExcel("Org1", 4, 3);
		String type= eUtil.getDataFromExcel("Org1", 4, 4);	;
		
		
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
		
		WebElement industry=driver.findElement(By.name("industry"));
		Select s=new Select(industry);
		//s.selectByIndex(9);
		s.selectByVisibleText(industries);
		WebElement type1=driver.findElement(By.name("accounttype"));
		Select s1=new Select(type1);
		//s1.selectByValue("Press");
		s1.selectByVisibleText(type);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		

		
		//verify the DropDown industries and type info
		String actualIndustries=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actualIndustries.equals(industries))
		{
			System.out.println(industries+ " Information is verified===PASS");
		}
		else
		{
			System.out.println(industries+ " Information is not verified===FAIL");
		}
		
		
		String actualType=driver.findElement(By.id("dtlview_Type")).getText();
		if(actualType.equals(type))
		{
			System.out.println(type+ " Information is verified===PASS");
		}
		else
		{
			System.out.println(type+ " Information is not verified===FAIL");
		}
		
		
		
//		Actions a=new Actions(driver);
//		WebElement userIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		//WebElement userIcon=driver.findElement(By.xpath("//td[@class='small'][1]/img"));
//		a.moveToElement(userIcon).perform();
//		driver.findElement(By.linkText("Sign Out")).click();
		
		
		driver.quit();
		
		
		
		
		
	}

}
