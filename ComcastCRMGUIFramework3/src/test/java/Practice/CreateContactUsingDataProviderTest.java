package Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContactUsingDataProviderTest 
{
	@Test(dataProvider="getData")
	public void createContactTest(String firstName,String lastName)
	{
		System.out.println("FistName: "+firstName + " LastName: "+lastName);
	}

	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr=new Object[4][2];
		//hardcoding the data here but in realtime we need to store the data in excel
		
		objArr[0][0]="rukhsar";
		objArr[0][1]="banu";
		
		objArr[1][0]="nauman";
		objArr[1][1]="ahmed";
		
		objArr[2][0]="nasreen";
		objArr[2][1]="banu";
		
		objArr[3][0]="munna";
		objArr[3][1]="pasha";
		return objArr;
	}
}
