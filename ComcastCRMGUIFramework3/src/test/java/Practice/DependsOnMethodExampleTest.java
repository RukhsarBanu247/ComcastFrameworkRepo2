package Practice;

import org.testng.annotations.Test;

public class DependsOnMethodExampleTest 
{
//	@Test
//	public void createOrderTest()
//	{
//		System.out.println("Order created====123");
//		//negative testcase
//		String apple=null;
//		System.out.println(apple.equals("123"));
//	}
//
//	@Test(dependsOnMethods = "createOrderTest")
//	public void billingOrderTest()
//	{
//		System.out.println("billing Order created====123");
//	}
	
	@Test
	public void createContactTest()
	{
		System.out.println("execute createContacttest");
	
	}
	
	@Test(dependsOnMethods = "createContactTest")
	public void modifyContactTest()
	{
		System.out.println("execute modifyContactTest");
	}
	
	@Test(dependsOnMethods = "modifyContactTest")
	public void deleteContactTest()
	{
		System.out.println("execute deleteContactTest");
	}
}
