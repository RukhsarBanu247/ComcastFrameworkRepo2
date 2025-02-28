package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class AutoHealingTechnique 
{
	@FindBy(name="user_name")
	private WebElement usernameField;
	
	@FindBy(name="user_password")
	private WebElement passwordField;
	
	@FindAll({@FindBy(id="subm"),@FindBy(xpath = "//input[@type='submit']")})
	private WebElement loginBtn;
	
	@Test
	public void loginOperationToApp()
	{
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
		
		AutoHealingTechnique at=PageFactory.initElements(driver,AutoHealingTechnique.class);
		
		at.usernameField.sendKeys("admin");
		at.passwordField.sendKeys("admin");
		at.loginBtn.click();
		
		driver.quit();
	}
	
	

}
