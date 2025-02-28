package Practice;

import org.testng.annotations.Test;

public class PriorityExampleTest 
{
	@Test(priority = 1)
	public void createContact()
	{
		System.out.println("execute createContact");
	}
	
	@Test(priority = 2)
	public void modifyContact()
	{
		System.out.println("execute modifyContact");
	}
	
	@Test(priority = -1)
	public void deleteContact()
	{
		System.out.println("execute deleteContact");
	}
}
