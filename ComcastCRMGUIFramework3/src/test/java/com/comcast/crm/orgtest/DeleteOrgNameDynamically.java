package com.comcast.crm.orgtest;

import org.openqa.selenium.Alert;
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
import com.comcast.crm.objectrepositoryUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;

public class DeleteOrgNameDynamically 
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
			String orgName= eUtil.getDataFromExcel("Org1", 10, 2)+ jUtil.getRandomNumber();
			System.out.println(orgName);
			
			
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
			driver.get(URL);	
			
			//Step1: Login to App
			LoginPage lp=new LoginPage(driver);
			//lp.loginToApp(USERNAME,PASSWORD);
			lp.loginToApp(USERNAME, orgName);
			
			//Step2:navigate to Organization Page
			HomePage hp=new HomePage(driver);
			hp.getOrgLink().click();
			
			//Step3: click on create Organization button
			OrganizationPage op=new OrganizationPage(driver);
			op.getCreateNewOrgBtn().click();
			
			//Step4: enter all the details and create new Organization
			CreateNewOrganizationPage cp=new CreateNewOrganizationPage(driver);
			cp.createOrg(orgName);
			
			//verify Header msg Expected result
			OrganizationInfoPage oip=new OrganizationInfoPage(driver);
			String actualHeadermsg=oip.getHeaderMsg().getText();
			if(actualHeadermsg.contains(orgName))
			{
				System.out.println(orgName+" is verified===PASS");
			}
			else
			{
				System.out.println(orgName+" is not verified===FAIL");
			}
			
			//go back to Organization page
			hp.getOrgLink().click();
			
			//search for Organization
			op.getSearchBox().sendKeys(orgName);
			wUtil.select(op.getSearchDrpDwn(),"Organization Name");
			op.getSearchBtn().click();
			
			//In dynamic webTable select and delete Org
			driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
			
			//Alert a= driver.switchTo().alert();
			wUtil.alertHandling(driver);
			
			//Step5:logout operation
			hp.logout();
			
			driver.quit();
			
	}
			
}







