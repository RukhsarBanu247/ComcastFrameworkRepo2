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
import com.comcast.crm.objectrepositoryUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;

public class CreateLeadTest 
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
		String lastName= eUtil.getDataFromExcel("leads", 1, 2)+ jUtil.getRandomNumber();
		String company=eUtil.getDataFromExcel("leads", 1, 3);
		String assignedTo=eUtil.getDataFromExcel("leads", 1, 4);
		
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
		wUtil.select(drpdwn, "Support Group");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
	
		
		
		//code for POM class
		//LoginPage lp=new LoginPage(driver);
		//private access modifier to elements
//		lp.usernameEdit.sendKeys("admin");
//		lp.passwordEdit.sendKeys("admin");
//		lp.loginButton.click();
		
//		lp.getUsernameEdit().sendKeys("admin");;
//		lp.getPasswordEdit().sendKeys("admin");
//		lp.getLoginButton().click();
		
//		
//		//by using businness library
//		//Step1: Login to App
//		LoginPage lp=new LoginPage(driver);
//		lp.loginToApp(USERNAME,PASSWORD);
//		
//		//Step2:navigate to Organization Page
//		HomePage hp=new HomePage(driver);
//		hp.getOrgLink().click();
//		
//		//Step3: click on create Organization button
//		OrganizationPage op=new OrganizationPage(driver);
//		op.getCreateNewOrgBtn().click();
//		
//		//Step4: enter all the details and create new Organization
//		CreateNewOrganizationPage cp=new CreateNewOrganizationPage(driver);
//		cp.createOrg(orgName);
//		
//		//verify Header msg Expected result
//		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
//		String actualHeadermsg=oip.getHeaderMsg().getText();
//		if(actualHeadermsg.contains(orgName))
//		{
//			System.out.println(orgName+" is verified===PASS");
//		}
//		else
//		{
//			System.out.println(orgName+" is not verified===FAIL");
//		}
//		
//		//Step5:logout operation
//		hp.logout();
		
		//VERIFY HEADER MSG EXPECTED RESULT
		String ActualHeadder=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(ActualHeadder.contains(lastName))
		{
			System.out.println(lastName+ " Leads is created successfully===PASS");
		}
		else
		{
			System.out.println(lastName+ " Leads is not created===FAIL");
		}
		
		
		//verify header leadname info expected result
		String actualLeadInfo=driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actualLeadInfo.equals(lastName))
		{
			System.out.println(lastName+ " information is created successfully===PASS");
		}
		else
		{
			System.out.println(lastName+ " information is not created===FAIL");
		}
		
		Actions a=new Actions(driver);
		WebElement userIcon=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		a.moveToElement(userIcon).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		Thread.sleep(2000);
		driver.quit();
		
		
	}

}



