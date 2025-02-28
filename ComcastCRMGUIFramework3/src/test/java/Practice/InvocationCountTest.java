package Practice;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class InvocationCountTest 
{
	@Test(invocationCount = 5)
	public void createOrderTest()
	{
		//for(int i=0;i<10;i++)---->it will show only one testcase is executed with 5 times of iteration
	
			System.out.println("Order created====123");


	}

	///@Ignore
	@Test(enabled = false)
	public void billingOrderTest()
	{
		System.out.println("billing Order created====123");
	}

}
