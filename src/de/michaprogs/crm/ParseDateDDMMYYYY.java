package de.michaprogs.crm;

public class ParseDateDDMMYYYY {

	private String date = "";
	
	public ParseDateDDMMYYYY(String stringToParse){
		
		String regex = "[0-9]{4}[-][0-9]{2}[-][0-9]{2}";
		
		if(stringToParse.matches(regex)){
			
			String year = stringToParse.substring(0, 4);
			String month = stringToParse.substring(5, 7);
			String day = stringToParse.substring(8, 10);
			
			date = day+"."+month+"."+year;
			
		}
		
	}
	
	public String getDateDDMMYYYY(){
		return date;
	}
	
}
