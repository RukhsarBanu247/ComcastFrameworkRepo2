package com.comcast.crm.PomClasses;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.ContactPage;
import com.comcast.crm.objectrepositoryUtility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LoginPage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;


/**
 * @author rukhs
 * 
 */
public class CreateContactTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {

		/* reading testscriptdata from excel file */
		String lastName = eUtil.getDataFromExcel("Contact", 1, 2) + jUtil.getRandomNumber();

		// step 2:navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3:click on create contact button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactButton().click();

		// step 4:enter all the details and create new contact
		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.createContact(lastName);

		// VERIFY HEADER MSG EXPECTED RESULT
		String actualLastname = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actualLastname.contains(lastName)) {
			System.out.println(lastName + " Information is verified===PASS");
		} else {
			System.out.println(lastName + " Information is not verified===FAIL");
		}
	
		
	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws Exception {
		String lastName = eUtil.getDataFromExcel("Contact", 4, 2) + jUtil.getRandomNumber();
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactButton().click();

		String startDate = jUtil.getSystemDateYYYYMMDD();
		String endDate = jUtil.getRequiredDateYYYYMMDD(30);

		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.createContactWithSupportDate(lastName, startDate, endDate);

		// VERIFY StartDate EXPECTED RESULT
		String startDateField = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (startDateField.equals(startDate)) {
			System.out.println(startDate + " Information is verified===PASS");
		} else {
			System.out.println(startDate + " Information is not verified===FAIL");
		}

		// VERIFY EndDate EXPECTED RESULT
		String endDateField = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if (endDateField.equals(endDate)) {
			System.out.println(endDate + " Information is verified===PASS");
		} else {
			System.out.println(endDate + " Information is not verified===FAIL");
		}
	}

	@Test(groups={"regressionTest"})
	public void createContactWithOrgTest() throws Exception {
		String orgName = eUtil.getDataFromExcel("Contact", 7, 2) + jUtil.getRandomNumber();
		String contactLastName = eUtil.getDataFromExcel("Contact", 7, 3) + jUtil.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		CreateNewOrganizationPage cnp = new CreateNewOrganizationPage(driver);
		cnp.createOrg(orgName);

		// VERIFY HEADER MSG EXPECTED RESULT
		String ActualHeadder = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (ActualHeadder.contains(orgName)) {
			System.out.println(orgName + " Organization is created successfully===PASS");
		} else {
			System.out.println(orgName + " Organization is not created===FAIL");
		}

		hp.getContactLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactButton().click();

		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.createContactWithOrg(contactLastName, orgName);

		// verify header orgname info expected result----DataFlow
		String actualOrgnameInfo = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actualOrgnameInfo);
		if (actualOrgnameInfo.trim().equals(orgName))// trim()---is used to remove the unwanted spaces
		{
			System.out.println(orgName + " information is created successfully===PASS");
		} else {
			System.out.println(orgName + " information is not created===FAIL");
		}

	}

}
