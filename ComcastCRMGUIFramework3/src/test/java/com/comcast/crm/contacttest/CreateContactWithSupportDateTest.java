package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDateTest 
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
		
	
		
		String lastName= eUtil.getDataFromExcel("Contact", 4, 2)+jUtil.getRandomNumber();
			
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
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wUtil.waitForPageToLoad(driver);
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
//		Date d=new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		String startDate=sdf.format(d);
//		//System.out.println(startDate);
//		
//		Calendar cal=sdf.getCalendar();
//		cal.add(Calendar.DAY_OF_MONTH, 30);
//		String endDate=sdf.format(cal.getTime());
//		//System.out.println(endDate);
		
		
		String startDate=jUtil.getSystemDateYYYYMMDD();
		String endDate = jUtil.getRequiredDateYYYYMMDD(30);
	
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		

		
		//VERIFY StartDate EXPECTED RESULT
		String startDateField=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(startDateField.equals(startDate))
		{
			System.out.println(startDate+ " Information is verified===PASS");
		}
		else
		{
			System.out.println(startDate+ " Information is not verified===FAIL");
		}
		
		//VERIFY EndDate EXPECTED RESULT
		String endDateField=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(endDateField.equals(endDate))
				{
					System.out.println(endDate+ " Information is verified===PASS");
				}
				else
				{
					System.out.println(endDate+ " Information is not verified===FAIL");
				}	
		
		driver.quit();
	}
}
		
		
		

