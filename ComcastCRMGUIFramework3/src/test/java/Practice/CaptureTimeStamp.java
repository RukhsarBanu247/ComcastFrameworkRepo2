package Practice;

import java.util.Date;

public class CaptureTimeStamp 
{
	public static void main(String[] args) {
		
		Date d= new Date();
		String timeStamp=d.toString();
		System.out.println(timeStamp);
		
		//remove the spaces and colon from the coming timeStamp o/p and replace it with underscore
		String timeStamp1=d.toString().replace(" ", "_").replace(":", "_");
		System.out.println(timeStamp1);
	}

}
