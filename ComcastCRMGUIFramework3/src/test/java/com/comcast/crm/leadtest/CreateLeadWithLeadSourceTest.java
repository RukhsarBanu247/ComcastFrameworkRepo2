package com.comcast.crm.leadtest;

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

public class CreateLeadWithLeadSourceTest 
{
	public static void main(String[] args) throws Throwable 
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
				String lastName= eUtil.getDataFromExcel("leads", 4, 2)+ jUtil.getRandomNumber();
				String company=eUtil.getDataFromExcel("leads", 4, 3);
				String assignedTo=eUtil.getDataFromExcel("leads", 4, 4);
				String leadSource=eUtil.getDataFromExcel("leads", 4, 5);
				
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
				driver.findElement(By.linkText("Leads")).click();
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
				
				driver.findElement(By.name("lastname")).sendKeys(lastName);
				driver.findElement(By.name("company")).sendKeys(company);
				driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
			
				WebElement drpdwn=driver.findElement(By.name("assigned_group_id"));
				wUtil.select(drpdwn, assignedTo);
				
				WebElement leadsrcdrpdwn=driver.findElement(By.name("leadsource"));
				wUtil.select(leadsrcdrpdwn, leadSource);
				
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
				
				//VERIFY HEADER MSG EXPECTED RESULT
				String ActualHeadder=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(ActualHeadder.contains(lastName))
				{
					System.out.println(lastName+ " Lead is created successfully===PASS");
				}
				else
				{
					System.out.println(lastName+ " Lead is not created===FAIL");
				}
				
				
				//verify header leadname info expected result
				String actualLeadInfo=driver.findElement(By.id("dtlview_Last Name")).getText();
				if(actualLeadInfo.equals(lastName))
				{
					System.out.println(lastName+ " Lead information is created successfully===PASS");
				}
				else
				{
					System.out.println(lastName+ " Lead information is not created===FAIL");
				}
				
				Actions a=new Actions(driver);
				WebElement userIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				a.moveToElement(userIcon).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				
				Thread.sleep(2000);
				driver.quit();
				
		
	}

}
