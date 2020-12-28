package utilities;

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
}
