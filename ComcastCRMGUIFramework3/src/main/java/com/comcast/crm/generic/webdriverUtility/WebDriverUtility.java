package com.comcast.crm.generic.webdriverUtility;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.FileHandler;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class WebDriverUtility 
{
	//Implicit Wait
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	//Explicit wait
	public void waitforElementPresent(WebDriver driver,WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	//Switch to Child Window
	public void switchToTabOnURL(WebDriver driver,String partialURl)
	{
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext())
		{
			String windowID=it.next();
			driver.switchTo().window(windowID);
			
			String actualURL=driver.getCurrentUrl();
			if(actualURL.contains(partialURl))
			{
				break;
			}
		}
	}
	
	//Switch to Parent Window
	public void switchToTabOnTitle(WebDriver driver,String partialTitle)
	{
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext())
		{
			String windowID=it.next();
			driver.switchTo().window(windowID);
			
			String actualURL=driver.getTitle();
			if(actualURL.contains(partialTitle))
			{
				break;
			}
		}
	}
	
	//Switch to frame
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String nameId)
	{
		driver.switchTo().frame(nameId);
	}
	public void switchToFrame(WebDriver driver,WebElement ele)
	{
		driver.switchTo().frame(ele);
	}
	
	//switch to alert
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	//select dropdown
	public void select(WebElement ele,String text)
	{
		Select s=new Select(ele);
		s.selectByVisibleText(text);
	}
	public void select(WebElement ele,int index)
	{
		Select s=new Select(ele);
		s.selectByIndex(index);;
	}
	
	//Action class
	public void mouseMoveOnElement(WebDriver driver,WebElement ele)
	{
		Actions a=new Actions(driver);
		a.moveToElement(ele).perform();
	}
	public void doubleClick(WebDriver driver,WebElement ele)
	{
		Actions a=new Actions(driver);
		a.doubleClick(ele).perform();
	}
	public void rightClick(WebDriver driver,WebElement ele)
	{
		Actions a=new Actions(driver);
		a.contextClick(ele).perform();
	}
	public void scrollToElement(WebDriver driver,WebElement ele)
	{
		Actions a=new Actions(driver);
		a.scrollToElement(ele).perform();
	}
	public void scrollByAmt(WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.scrollByAmount(0,0).perform();
	}
	public void clickAndHold(WebDriver driver,WebElement ele)
	{
		Actions a=new Actions(driver);
		a.clickAndHold(ele).perform();;
	}
	public void dragAndDrop(WebDriver driver,WebElement ele1,WebElement ele2)
	{
		Actions a=new Actions(driver);
		a.dragAndDrop(ele1, ele2).perform();
	}
	public void dragAndDrop(WebDriver driver,WebElement ele1,int xoffset,int yoffset)
	{
		Actions a=new Actions(driver);
		a.dragAndDropBy(ele1, xoffset, yoffset).perform();
	}
	public void pressShiftWithKey(WebDriver driver,WebElement ele,String key)
	{
		Actions a=new Actions(driver);
		a.keyDown(ele,Keys.SHIFT).sendKeys(ele,key).keyUp(ele,Keys.SHIFT).perform();
	}
	
	//maximize the window
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	//taking Screenshot of WebPage
	public void takeScreenShot(WebDriver driver,String path)
	{
		 TakesScreenshot ts=(TakesScreenshot)driver;
		 File src=ts.getScreenshotAs(OutputType.FILE);
		 File dest=new File(path);
		 try {
			Files.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//taking Screenshot of WebElement
	public void takeScreenShotOfWebElement(WebElement ele,String path)
	{
		File src=ele.getScreenshotAs(OutputType.FILE);
		File dest=new File(path);
		try {
			Files.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 //handling alert popups
	public void alertHandling(WebDriver driver)
	{
		Alert al=driver.switchTo().alert();
		al.accept();
		//al.dismiss();
	}
	
	
}
