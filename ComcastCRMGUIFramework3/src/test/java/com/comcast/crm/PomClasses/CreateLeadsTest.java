package com.comcast.crm.PomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.CreateNewLeadPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.LeadPage;

public class CreateLeadsTest extends BaseClass {
	@Test
	public void createLead() throws Throwable {

		// reading data from excel file
		String lastName = eUtil.getDataFromExcel("leads", 1, 2) + jUtil.getRandomNumber();
		String company = eUtil.getDataFromExcel("leads", 1, 3);
		String assignedTo = eUtil.getDataFromExcel("leads", 1, 4);

		HomePage hp = new HomePage(driver);
		hp.getLeadLink().click();

		LeadPage lp = new LeadPage(driver);
		lp.getCreateLeadBtn().click();

		CreateNewLeadPage cnlp = new CreateNewLeadPage(driver);
		cnlp.createLead(lastName, company, assignedTo);

		// VERIFY HEADER MSG EXPECTED RESULT
		String ActualHeadder = cnlp.getActualHeadder().getText();
		if (ActualHeadder.contains(lastName)) {
			System.out.println(lastName + " Leads is created successfully===PASS");
		} else {
			System.out.println(lastName + " Leads is not created===FAIL");
		}

		// verify header leadname info expected result
		String actualLeadInfo = cnlp.getActualLeadName().getText();
		if (actualLeadInfo.equals(lastName)) {
			System.out.println(lastName + " information is created successfully===PASS");
		} else {
			System.out.println(lastName + " information is not created===FAIL");
		}

	}

	@Test
	public void createLeadWithAnnualRevenueTest() throws Throwable {
		// reading data from excel file
		String lastName = eUtil.getDataFromExcel("leads", 7, 2) + jUtil.getRandomNumber();
		String company = eUtil.getDataFromExcel("leads", 7, 3);
		String assignedTo = eUtil.getDataFromExcel("leads", 7, 4);
		String annualRevenue = eUtil.getDataFromExcel("leads", 7, 5) + jUtil.getRandomNumber();
		;

		HomePage hp = new HomePage(driver);
		hp.getLeadLink().click();

		LeadPage lp = new LeadPage(driver);
		lp.getCreateLeadBtn().click();

		CreateNewLeadPage cnlp = new CreateNewLeadPage(driver);
		cnlp.createLeadWithAnnualRevenue(lastName, company, annualRevenue, assignedTo);
		
		// VERIFY HEADER MSG EXPECTED RESULT
		String ActualHeadder = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (ActualHeadder.contains(lastName)) {
			System.out.println(lastName + " Lead is created successfully===PASS");
		} else {
			System.out.println(lastName + " Lead is not created===FAIL");
		}

		// verify header lead With Annual revenue info expected result
		String actualAnnualRevenue = driver.findElement(By.id("dtlview_Annual Revenue")).getText();
		if (!actualAnnualRevenue.equals(annualRevenue)) {
			System.out.println(annualRevenue + " Lead information is created successfully===PASS");
		} else {
			System.out.println(annualRevenue + " Lead information is not created===FAIL");
		}

	}

	@Test
	public void createLeadWithLeadSource() throws Throwable {
		// reading data from excel file
		String lastName = eUtil.getDataFromExcel("leads", 4, 2) + jUtil.getRandomNumber();
		String company = eUtil.getDataFromExcel("leads", 4, 3);
		String assignedTo = eUtil.getDataFromExcel("leads", 4, 4);
		String leadSource = eUtil.getDataFromExcel("leads", 4, 5);

		HomePage hp = new HomePage(driver);
		hp.getLeadLink().click();

		LeadPage lp = new LeadPage(driver);
		lp.getCreateLeadBtn().click();

		CreateNewLeadPage cnlp = new CreateNewLeadPage(driver);
		cnlp.createLeadWithLeadSource(lastName, company, assignedTo, leadSource);

		// VERIFY HEADER MSG EXPECTED RESULT
		String ActualHeadder = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (ActualHeadder.contains(lastName)) {
			System.out.println(lastName + " Lead is created successfully===PASS");
		} else {
			System.out.println(lastName + " Lead is not created===FAIL");
		}

		// verify header leadname info expected result
		String actualLeadInfo = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actualLeadInfo.equals(lastName)) {
			System.out.println(lastName + " Lead information is created successfully===PASS");
		} else {
			System.out.println(lastName + " Lead information is not created===FAIL");
		}

	}

}
