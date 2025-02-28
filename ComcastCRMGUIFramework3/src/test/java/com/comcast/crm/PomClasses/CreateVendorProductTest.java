package com.comcast.crm.PomClasses;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.fileUtility.ExcelUtility;
import com.comcast.crm.generic.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcast.crm.generic.webdriverUtility.WebDriverUtility;
import com.comcast.crm.objectrepositoryUtility.CreateNewProductPage;
import com.comcast.crm.objectrepositoryUtility.CreateNewVendorPage;
import com.comcast.crm.objectrepositoryUtility.HomePage;
import com.comcast.crm.objectrepositoryUtility.ProductPage;
import com.comcast.crm.objectrepositoryUtility.VendorsPage;

public class CreateVendorProductTest extends BaseClass {
	@Test
	public void createVendorTest() throws Throwable {
		// reading data from excel file
		String vendorName = eUtil.getDataFromExcel("vendor", 2, 1) + jUtil.getRandomNumber();
		String email = eUtil.getDataFromExcel("vendor", 2, 2);
		String phoneNum = eUtil.getDataFromExcel("vendor", 2, 3);
		String country = eUtil.getDataFromExcel("vendor", 2, 4);
		String city = eUtil.getDataFromExcel("vendor", 2, 5);
		String description = eUtil.getDataFromExcel("vendor", 2, 6);

		HomePage hp = new HomePage(driver);
		hp.navigateToVendorsPage();

		VendorsPage vp = new VendorsPage(driver);
		vp.getCreateVendorBtn().click();

		CreateNewVendorPage cnvp = new CreateNewVendorPage(driver);
		cnvp.createVendor(vendorName, email, phoneNum, city, country, description);

		// String
		// actualHedder=driver.findElement(By.className("lvtHeaderText")).getText();
		String ActualHeadder = cnvp.getActHeadder().getText();
		if (ActualHeadder.contains(vendorName)) {
			System.out.println(vendorName + " is verified====PASS");
		} else {
			System.out.println(vendorName + " is not verifies===FAIL");
		}
		// verify header vendor name info expected result
		// String actualvendorInfo=driver.findElement(By.id("dtlview_Vendor
		// Name")).getText();
		String ActualVendorName = cnvp.getActVendorInfo().getText();

//						if(actualvendorInfo.equals(vendorName))
//						{
//							System.out.println(vendorName+ " information is created successfully===PASS");
//						}
//						else
//						{
//							System.out.println(vendorName+ " information is not created===FAIL");
//						}
		Assert.assertEquals(ActualVendorName, vendorName);
	}

	@Test
	public void createVendorWithProduct() throws Exception {

		// reading data from excel file
		String productName = eUtil.getDataFromExcel("product", 7, 1) + jUtil.getRandomNumber();
		String handler = eUtil.getDataFromExcel("product", 7, 2);
		String salesStartDate = jUtil.getSystemDateYYYYMMDD();
		String salesEndDate = jUtil.getRequiredDateYYYYMMDD(32);
		String vendorName = eUtil.getDataFromExcel("product", 7, 5) + jUtil.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.navigateToVendorsPage();

		VendorsPage vp = new VendorsPage(driver);
		vp.getCreateVendorBtn().click();

		CreateNewVendorPage cnvp = new CreateNewVendorPage(driver);
		cnvp.createvendor(vendorName);

		ProductPage pp = new ProductPage(driver);
		pp.createProduct();

		CreateNewProductPage cnpp = new CreateNewProductPage(driver);
		cnpp.createProduct(productName, salesStartDate, salesEndDate, vendorName);
	
		//verification
				String actualHeadder=driver.findElement(By.className("lvtHeaderText")).getText();
				if(actualHeadder.contains(productName))
				{
					System.out.println(productName+" is verified----PASS");
				}
				else
				{
					System.out.println(productName+" is not verified----FAIL");
				}
				String actualProductInfo=driver.findElement(By.id("dtlview_Product Name")).getText();
				if(actualProductInfo.equals(productName))
				{
					System.out.println(productName+" is correct----PASS");
				}
				else
				{
					System.out.println(productName+" is not correct----FAIL");
				}
	}
}
				

