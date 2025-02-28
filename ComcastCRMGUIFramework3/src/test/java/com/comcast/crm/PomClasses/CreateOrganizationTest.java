package com.comcast.crm.PomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.listenerUtility.ListenerImplementationClass;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.UtilityClassObject;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;

@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImplementationClass.class)
public class CreateOrganizationTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void createOrganization() throws Throwable {

		// reading data from excel file
		//ListenerImplementationClass.test.log(Status.INFO,"read data from excel");//---- this will not participate in prallell execution
		UtilityClassObject.getTest().log(Status.INFO,"read data from excel");//-- this will participate in parallele execution
		String orgName = eUtil.getDataFromExcel("Org1", 1, 2) + jUtil.getRandomNumber();

		// Step2:navigate to Organization Page
		//ListenerImplementationClass.test.log(Status.INFO,"navigate To org page");
		UtilityClassObject.getTest().log(Status.INFO,"navigate To org page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		
		// Step3: click on create Organization button
		//ListenerImplementationClass.test.log(Status.INFO,"navigate to create org page");
		UtilityClassObject.getTest().log(Status.INFO,"navigate to create org page");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// Step4: enter all the details and create new Organization
		//ListenerImplementationClass.test.log(Status.INFO,"create new Org");
		UtilityClassObject.getTest().log(Status.INFO,"create new Org");
		CreateNewOrganizationPage cp = new CreateNewOrganizationPage(driver);
		cp.createOrg(orgName);
		
		//ListenerImplementationClass.test.log(Status.INFO,orgName+" is created");
		UtilityClassObject.getTest().log(Status.INFO, orgName+" is created");
		
		
		// verify Header msg Expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualHeadermsg = oip.getHeaderMsg().getText();
		if (actualHeadermsg.contains(orgName)) {
			System.out.println(orgName + " is verified===PASS");
		} else {
			System.out.println(orgName + " is not verified===FAIL");
		}

	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithIndustry() throws Throwable {
		// reading data from excel file
		String orgName = eUtil.getDataFromExcel("Org1", 4, 2) + jUtil.getRandomNumber();

		String industries = eUtil.getDataFromExcel("Org1", 4, 3);
		String type = eUtil.getDataFromExcel("Org1", 4, 4);

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		CreateNewOrganizationPage cnp = new CreateNewOrganizationPage(driver);
		cnp.createOrg(orgName, industries, type);

		// verify the DropDown industries and type info
		String actualIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actualIndustries.equals(industries)) {
			System.out.println(industries + " Information is verified===PASS");
		} else {
			System.out.println(industries + " Information is not verified===FAIL");
		}

		String actualType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actualType.equals(type)) {
			System.out.println(type + " Information is verified===PASS");
		} else {
			System.out.println(type + " Information is not verified===FAIL");
		}

	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithPhoneNumber() throws Exception {

		// reading data from excel file
		String orgName = eUtil.getDataFromExcel("Org1", 7, 2) + jUtil.getRandomNumber();
		String phoneNum = eUtil.getDataFromExcel("Org1", 7, 3);

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		CreateNewOrganizationPage cnp = new CreateNewOrganizationPage(driver);
		cnp.createOrgWithPhoneNum(orgName, phoneNum);

		// VERIFY HEADER phone number EXPECTED RESULT
		String actualPhoneNum = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actualPhoneNum.equals(phoneNum)) {
			System.out.println(phoneNum + " information is created successfully===PASS");
		} else {
			System.out.println(phoneNum + " information is not created===FAIL");
		}

	}

	@Test(groups = "regressionTest")
	public void delereOrg() throws Exception {

		// reading data from excel file
		String orgName = eUtil.getDataFromExcel("Org1", 10, 2) + jUtil.getRandomNumber();

		// Step2:navigate to Organization Page
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// Step3: click on create Organization button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// Step4: enter all the details and create new Organization
		CreateNewOrganizationPage cp = new CreateNewOrganizationPage(driver);
		cp.createOrg(orgName);

		// verify Header msg Expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actualHeadermsg = oip.getHeaderMsg().getText();
//		if(actualHeadermsg.contains(orgName))
//		{
//			System.out.println(orgName+" is verified===PASS");
//		}
//		else
//		{
//			System.out.println(orgName+" is not verified===FAIL");
//		}
		boolean status = actualHeadermsg.contains(orgName);

		Assert.assertEquals(true, status);

		// go back to Organization page
		hp.getOrgLink().click();
		op.deleteOrg(orgName);

		// In dynamic webTable select and delete Org
		// driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();

	}
}
