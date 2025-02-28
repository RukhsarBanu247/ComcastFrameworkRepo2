package com.comcast.crm.PomClasses;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.objectrepositoryUtility.ContactPage;
import com.comcast.crm.objectrepositoryUtility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.OrganizationPage;



public class createContactWithphoneNumberAssertion extends BaseClass
{
	@Test
	public void createContact() throws Throwable {

		// reading testscriptdata from excel file
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

	

	

}
