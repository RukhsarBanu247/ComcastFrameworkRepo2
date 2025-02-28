package Practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class BaseClass
{
	@BeforeSuite
	public void BS()
	{
		System.out.println("before suite");
	}
	@AfterSuite()
	public void AS()
	{
		System.out.println("after suite");
	}
	@BeforeClass
	public void BC1()
	{
		System.out.println("before class1");
	}
	@AfterClass
	public void AC1()
	{
		System.out.println("after class1");
	}
	@BeforeClass
	public void BC2()
	{
		System.out.println("before class2");
	}
	@AfterClass
	public void AC2()
	{
		System.out.println("after class2");
	}
	@BeforeMethod
	public void BM1()
	{
		System.out.println("before method");
	}
	@AfterMethod
	public void AM1()
	{
		System.out.println("after method");
	}
	@Test
	public void testMethod()
	{
		int a=10;
		int sum=2*a;
		System.out.println(sum);
	}
}

