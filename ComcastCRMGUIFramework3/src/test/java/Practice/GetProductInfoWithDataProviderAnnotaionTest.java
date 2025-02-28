package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileUtility.ExcelUtility;

public class GetProductInfoWithDataProviderAnnotaionTest 
{
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brand,String productName) throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("http://www.amazon.com/");
		
		driver.get("https://www.flipkart.com/");
		
		//search field
		driver.findElement(By.className("Pke_EE")).sendKeys(brand,Keys.ENTER);
		
		
		//capture the product info with dynamic xpath
		
		
		String x="//div[text()='"+productName+"']/../following-sibling::div/div/div/div[1]";
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() throws Exception
	{
		
		//hardcoding the data
//		Object[][] objArr=new Object[3][2];
//		objArr[0][0]="iphone 16";
//		objArr[0][1]="Apple iPhone 16 (Black, 128 GB)";
//		
//		objArr[1][0]="iphone 16";
//		objArr[1][1]="Apple iPhone 16 (Pink, 256 GB)";
//		
//		objArr[2][0]="iphone 16";
//		objArr[2][1]="Apple iPhone 16 Pro (Desert Titanium, 128 GB)";
		
		//getting data from excel
		ExcelUtility eUtil=new ExcelUtility();
		int rowCount=eUtil.getRowCount("product");
		Object[][] objArr=new Object[rowCount][2];
		
		for(int i=0;i<rowCount;i++)
		{
			objArr[i][0]=eUtil.getDataFromExcel("product", i+1, 0);
			objArr[i][1]=eUtil.getDataFromExcel("product", i+1, 1);
		}
		return objArr;
	}

}
