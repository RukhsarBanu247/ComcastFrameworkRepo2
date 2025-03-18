package com.comcast.crm.baseTest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v114.runtime.Runtime.GetPropertiesResponse;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseUtility.DataBaseUtility;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.UtilityClassObject;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;

public class BaseClass 
{
	public DataBaseUtility db=new DataBaseUtility();
	public FileUtility fUtil=new FileUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null; // temporary variable using for listemers in Invoicetest Class
	public JavaUtility jUtil=new JavaUtility();
	public ExcelUtility eUtil=new ExcelUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();
	
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS() throws SQLException
	{
		System.out.println("-----------------------Connect to DB, Report Config---------------");
		db.getDatabaseConnection();
		
	}
	
	
	//@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC() throws Exception
	{
		System.out.println("------------------Launch the browser----------------------");
		//String BROWSER=fUtil.getDataFromPropertiesFile("browser");
		String BROWSER=System.getProperty("browser", fUtil.getDataFromPropertiesFile("browser"));//--commamnd line parameters
		//String BROWSER=browser;
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
			driver=new ChromeDriver();
		}
		sdriver=driver;// temporary variable
		UtilityClassObject.setDriver(driver);
		
		wUtil.waitForPageToLoad(driver);
		driver.manage().window().maximize();
		//String URL= fUtil.getDataFromPropertiesFile("url");
		String URL=System.getProperty("url", fUtil.getDataFromPropertiesFile("url"));//--commamnd line parameters
		driver.get(URL);
	}

	
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Exception
	{
		System.out.println("------------------Login to the Application----------------");
		//String URL= fUtil.getDataFromPropertiesFile("url");
//		String USERNAME=fUtil.getDataFromPropertiesFile("username");
//		String PASSWORD= fUtil.getDataFromPropertiesFile("password");
		String USERNAME=System.getProperty("username", fUtil.getDataFromPropertiesFile("username"));//--commamnd line parameters
		String PASSWORD=System.getProperty("password",fUtil.getDataFromPropertiesFile("password"));//--commamnd line parameters
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM()
	{
		System.out.println("------------------Logout from the Application----------------");
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC()
	{
		System.out.println("------------------close the browser----------------------");
		driver.quit();
	}
	
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS() throws Throwable
	{
		System.out.println("-------------------disconnect the DB, Report BackUp----------------------");
		db.closeDatabaseConnection();
		
	}
	

}
