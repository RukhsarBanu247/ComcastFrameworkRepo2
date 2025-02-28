package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GetProductInfoInAmazonTest
{
	@Test
	public void getProductInfoTest()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("http://www.amazon.com/");
		
		driver.get("https://www.flipkart.com/");
		//search for product
		//driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone 16",Keys.ENTER);
		driver.findElement(By.className("Pke_EE")).sendKeys("iphone 16",Keys.ENTER);
		
		
		//capture the product info
		//String x="//span[text()='Apple iPhone 15 (128 GB) - Black']/../../../following-sibling::div[2]/div/div/div/div/div/a/span/span[1]";
		
		String x="//div[text()='Apple iPhone 16 (Black, 128 GB)']/../following-sibling::div/div/div/div[1]";
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
	}

}
