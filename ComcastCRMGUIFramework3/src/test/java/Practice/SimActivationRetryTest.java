package Practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SimActivationRetryTest 
{
	@Test(retryAnalyzer = com.comcast.crm.generic.listenerUtility.RetryAnalyzer.class)
	public void simActivate()
	{
		System.out.println("Execution started");
//		System.out.println("Step1");
//		System.out.println("Step2");
		Assert.assertEquals(" ", "rukhsar");
//		System.out.println("Step3");
		System.out.println("Exceution stop");
	}

}
