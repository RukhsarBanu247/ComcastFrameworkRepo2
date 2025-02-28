package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateContactWithOrganizationTest //-----Integration Testcase
{
	public static void main(String[] args) throws Exception 
	{
		FileUtility fUtil=new FileUtility();
		ExcelUtility eUtil=new ExcelUtility();
		JavaUtility jUtil=new JavaUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		String BROWSER =fUtil.getDataFromPropertiesFile("browser");
		String URL= fUtil.getDataFromPropertiesFile("url");
		String USERNAME=fUtil.getDataFromPropertiesFile("username");
		String PASSWORD= fUtil.getDataFromPropertiesFile("password");
		
		
		String orgName= eUtil.getDataFromExcel("Contact", 7, 2)+ jUtil.getRandomNumber();
		String contactLastName= eUtil.getDataFromExcel("Contact", 7, 3)+ jUtil.getRandomNumber();
		
	
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
		driver.manage().window().maximize();
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//System.out.println(data);
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		
		
		//VERIFY HEADER MSG EXPECTED RESULT
		String ActualHeadder=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(ActualHeadder.contains(orgName))
		{
			System.out.println(orgName+ " Organization is created successfully===PASS");
		}
		else
		{
			System.out.println(orgName+ " Organization is not created===FAIL");
		}
		
		//navigate to contact button
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
	
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//switch to child window
//		String parentId=driver.getWindowHandle();
//		Set<String> childId=driver.getWindowHandles();
//		for(String id:childId)
//		{
//			driver.switchTo().window(id);
//		}
		
		
		//switch to child window
//		Set<String> set=driver.getWindowHandles();
//		Iterator<String> it=set.iterator();
//		while(it.hasNext())
//		{
//			String windowID=it.next();
//			driver.switchTo().window(windowID);
//			
//			String actualURL=driver.getCurrentUrl();
//			if(actualURL.contains("module=Account"))
//			{
//				break;
//			}
//		}
		wUtil.switchToTabOnURL(driver,"module=Account");
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();//dynamic xpath
		
		//switch to parent window
		//driver.switchTo().window(parentId);
		
		
		//switch back to parent window
//				Set<String> set1=driver.getWindowHandles();
//				Iterator<String> it1=set1.iterator();
//				while(it1.hasNext())
//				{
//					String windowID=it1.next();
//					driver.switchTo().window(windowID);
//					
//					String actualURL=driver.getCurrentUrl();
//					if(actualURL.contains("Contacts&action"))
//					{
//						break;
//					}
//				}
		wUtil.switchToTabOnTitle(driver, "Contacts&action");
				
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				
			
				
				//VERIFY HEADER MSG EXPECTED RESULT
				 ActualHeadder=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(ActualHeadder.contains(contactLastName))
				{
					System.out.println(contactLastName+ " Organization is created successfully===PASS");
				}
				else
				{
					System.out.println(contactLastName+ " Organization is not created===FAIL");
				}
				
				
				//verify header orgname info expected result----DataFlow
				String actualOrgnameInfo=driver.findElement(By.id("mouseArea_Organization Name")).getText();
				System.out.println(actualOrgnameInfo);
				if(actualOrgnameInfo.trim().equals(orgName))//trim()---is used to remove the unwanted spaces
				{
					System.out.println(orgName+ " information is created successfully===PASS");
				}
				else
				{
					System.out.println(orgName+ " information is not created===FAIL");
				}
				
				
		
		
		
		
		
	
		driver.quit();
		
	}

}
