package Practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContactWithPhineNumberDataProviderTest 
{

	@Test(dataProvider="getData")
	public void createContactTestWithPhoneNumber(String firstName,String lastName,long phoneNum)
	{
		System.out.println("FistName: "+firstName + " LastName: "+lastName+ " phone number is: "+phoneNum);
	}

	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr=new Object[4][3];
		objArr[0][0]="rukhsar";
		objArr[0][1]="banu";
		objArr[0][2]=8296925883l;
		
		objArr[1][0]="nauman";
		objArr[1][1]="ahmed";
		objArr[1][2]=8296925883l;
		
		objArr[2][0]="nasreen";
		objArr[2][1]="banu";
		objArr[2][2]=9902032601l;
		
		objArr[3][0]="munna";
		objArr[3][1]="pasha";
		objArr[3][2]=9880759555l;
		return objArr;
	}
}
