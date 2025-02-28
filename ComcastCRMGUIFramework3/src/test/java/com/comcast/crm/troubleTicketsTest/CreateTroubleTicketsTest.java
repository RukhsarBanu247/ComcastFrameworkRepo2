package com.comcast.crm.troubleTicketsTest;

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

public class CreateTroubleTicketsTest 
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
		String title= eUtil.getDataFromExcel("trouble tickets", 1, 2)+ jUtil.getRandomNumber();
		String status=eUtil.getDataFromExcel("trouble tickets", 1, 4);
		String productName=eUtil.getDataFromExcel("trouble tickets", 1, 5)+jUtil.getRandomNumber();
		String contact=eUtil.getDataFromExcel("trouble tickets", 1, 6);
		String assignedTo=eUtil.getDataFromExcel("trouble tickets", 1, 3);
		String lastName= eUtil.getDataFromExcel("Contact", 1, 2)+jUtil.getRandomNumber();
		
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
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
	
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		
		driver.findElement(By.linkText("Trouble Tickets")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		driver.findElement(By.name("ticket_title")).sendKeys(title);
		WebElement drpdwn=driver.findElement(By.name("assigned_user_id"));
		wUtil.select(drpdwn, assignedTo);
		
		WebElement status1=driver.findElement(By.name("ticketstatus"));
		wUtil.select(status1, status);
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		
		
		wUtil.switchToTabOnURL(driver, "Contacts&action");
		
		driver.findElement(By.name("search_text")).sendKeys(lastName);
		driver.findElement(By.name("search")).click();
		
		wUtil.switchToTabOnURL(driver, "module=HelpDesk");
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		//verification
				String actualHeadder=driver.findElement(By.className("dvHeaderText")).getText();
				if(actualHeadder.contains(title))
				{
					System.out.println(title+" is verified----PASS");
				}
				else
				{
					System.out.println(title+" is not verified----FAIL");
				}
				String actualTroubleTiTleInfo=driver.findElement(By.id("dtlview_Title")).getText();
				if(actualTroubleTiTleInfo.equals(title))
				{
					System.out.println(title+" is correct----PASS");
				}
				else
				{
					System.out.println(title+" is not correct----FAIL");
				}
				
		Actions a=new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out"));
		driver.quit();
		
	}
		

}
