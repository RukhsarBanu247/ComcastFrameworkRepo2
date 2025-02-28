package Practice;
/**
 * test class for contact module
 * @author rukhs
 */

import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryUtility.LoginPage;


public class SearchContactTest extends com.comcast.crm.baseTest.BaseClass
{
	/**
	 * Login-->navigate to contact--->createcontact()--- >verify
	 */
	@Test
	public void searchContactTest()
	{
		/* step1: login to app */
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("username", "password");
	}

}
