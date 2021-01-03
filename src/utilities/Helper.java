package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
	
	public int stringToInteger(String stringNumber) {
		try {
			int number = Integer.parseInt(stringNumber);
			return number;
		}
			catch (NumberFormatException e)
		{
			   throw new Error("Couldn't convert string to integer");
		}
		
	}
	
	public String getDateNow() {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		 LocalDateTime now = LocalDateTime.now(); 
		 return dtf.format(now);
	}
}
