package com.comcast.crm.generic.listenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.webdriverUtility.UtilityClassObject;
import com.google.common.io.Files;

public class ListenerImplementationClass  implements ITestListener,ISuiteListener
{

	public ExtentSparkReporter spark;
	public static ExtentTest test;
	public static ExtentReports report;//to use this report for every testcase make it as static
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report configuration");  
		
		String timeStamp1=new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report"+"-"+timeStamp1+".html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.STANDARD);
		
		//add Environment info and create test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS","Windows-11");
		report.setSystemInfo("Browser", "Chrome-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//System.out.println("=======>"+result.getMethod().getMethodName()+">=====START======");
		test=report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"===> Started <===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("=======>"+result.getMethod().getMethodName()+">=====END======");
		test.log(Status.PASS, result.getMethod().getMethodName()+"===> Completed <===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testCaseName= result.getMethod().getMethodName();
	
//		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
//		File src=ts.getScreenshotAs(OutputType.FILE);
//		File dest=new File(".\\ScreenShot\\"+testCaseName+".png");
//		try {
//			Files.copy(src, dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		EventFiringWebDriver edriver= new EventFiringWebDriver(BaseClass.sdriver);
//		File srcFile= edriver.getScreenshotAs(OutputType.FILE);
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		//extend report uses Base64 file
		String filepath=ts.getScreenshotAs(OutputType.BASE64);
		Date d=new Date();
		String timeStamp1=d.toString().replace(" ", "_").replace(":", "_");
//		try {
//			FileUtils.copyFile(srcFile, new File(".\\ScreenShot\\"+testCaseName+"+"+timeStamp1+".png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		test.addScreenCaptureFromBase64String(filepath,testCaseName+"_"+timeStamp1);
		
		
	}
 
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		test.log(Status.INFO, result.getMethod().getMethodName()+"===> Skipped <===");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
	

}
