package Practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImplementationClass.class)----Entering this in xml file
public class InvoiceTest extends com.comcast.crm.baseTest.BaseClass
{
	@Test
	public void createInvoiceTest()
	{
		System.out.println("Execute create createInvoiceTest");
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, "Login");
		System.out.println("Step 1");
		System.out.println("Step 2");
		System.out.println("Step 3");
		System.out.println("Step 4");
	}

	@Test
	public void createInvoiceWithContactTest()
	{
		System.out.println("Execute create createInvoiceWithContactTest");
		System.out.println("Step 1");
		System.out.println("Step 2");
		System.out.println("Step 3");
		System.out.println("Step 4");
	}

}
