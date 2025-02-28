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
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;

public class CreateOrganizationTest 
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
		String orgName= eUtil.getDataFromExcel("Org1", 1, 2)+ jUtil.getRandomNumber();
		
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
//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("submitButton")).click();
//		
//		driver.findElement(By.linkText("Organizations")).click();
//		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
//		
//		//System.out.println(data);
//		driver.findElement(By.name("accountname")).sendKeys(lastName);
//		
//		
//		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
//		
		
		
		//code for POM class
		//LoginPage lp=new LoginPage(driver);
		//private access modifier to elements
//		lp.usernameEdit.sendKeys("admin");
//		lp.passwordEdit.sendKeys("admin");
//		lp.loginButton.click();
		
//		lp.getUsernameEdit().sendKeys("admin");;
//		lp.getPasswordEdit().sendKeys("admin");
//		lp.getLoginButton().click();
		
		
		//by using businness library
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
		
		//Step5:logout operation
		hp.logout();
		
//		//VERIFY HEADER MSG EXPECTED RESULT
//		String ActualHeadder=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
//		if(ActualHeadder.contains(lastName))
//		{
//			System.out.println(lastName+ " Organization is created successfully===PASS");
//		}
//		else
//		{
//			System.out.println(lastName+ " Organization is not created===FAIL");
//		}
//		
//		
//		//verify header orgname info expected result
//		String actualOrgnameInfo=driver.findElement(By.id("dtlview_Organization Name")).getText();
//		if(actualOrgnameInfo.equals(lastName))
//		{
//			System.out.println(lastName+ " information is created successfully===PASS");
//		}
//		else
//		{
//			System.out.println(lastName+ " information is not created===FAIL");
//		}
//		
		
		
		
//		Actions a=new Actions(driver);
//		WebElement userIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		//WebElement userIcon=driver.findElement(By.xpath("//td[@class='small'][1]/img"));
//		a.moveToElement(userIcon).perform();
//		driver.findElement(By.linkText("Sign Out")).click();
		
		Thread.sleep(2000);
		driver.quit();
		
		
	}

}
