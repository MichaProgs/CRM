package de.michaprogs.crm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;

public class Validate {

	/**
	 * Formats: <br> 
	 * GERMANY: dd.mm.yyy <br>
	 * 			ddmmyyy <br>
	 * US: mm/dd/yyyy <br>
	 *     mm-dd-yyyy <br>
	 *     mmddyyyy
	 */
	public class ValidateDate{
		
		//Day Month Year
	//	private String regexGERWithDots = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])[.](0[1-9]|1[0-2])[.][0-9]{4}";
	//	private String regexGERWithoutDots = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])(0[1-9]|1[0-2])[0-9]{4}";
	//	
	//	//Month Day Year
	//	private String regexUSWithSlashAndMinus = "(0[1-9]|1[0-2])[\\/|-](0[1-9]|1[0-9]|2[0-9]|3[0-1])[\\/|-][0-9]{4}";
	//	private String regexUSWithoutSlashAndMinus = "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[0-9]{4}";
		
		private String regexGERWithDots = "[0-9]{2}[.][0-9]{2}[.][0-9]{4}";
		private String regexGERWithoutDots = "[0-9]{8}";
		
		private String regexUSWithSlashAndMinus = "[0-9]{2}[/|-][0-9]{2}[/|-][0-9]{4}";
		private String regexUSWithoutSlashAndMinus = "[0-9]{8}";
		
		/**
		 * Checks if the string is in the format (GER) dd.mm.yyyy ddmmyyyy<br>
		 * Afterwards it checks if the month is correct (between 1 and 12). <br>
		 * If the month is correct it checks if the month has as many days as given and returns date as a string.
		 * 
		 * @param stringToValidate
		 * @return string date in GER format
		 */
		public String validateDateGER(String stringToValidate){
			
			if(checkDateGER(stringToValidate) == true){
				
				if(stringToValidate.matches(regexGERWithDots)){
					return stringToValidate;
				}else if(stringToValidate.matches(regexGERWithoutDots)){
					
					String day = stringToValidate.substring(0, 2);
					String month = stringToValidate.substring(2, 4);
					String year = stringToValidate.substring(4, 8);
					
					return day+"."+month+"."+year;
					
				}else{
					return "";
				}
				
			}else{
				return "";
			}
			
		}
		
		/**
		 * Checks if the string is in the format (GER) dd.mm.yyyy ddmmyyyy<br>
		 * Afterwards it checks if the month is correct (between 1 and 12). <br>
		 * If the month is correct it checks if the month has as many days as given and returns the boolean 
		 * 
		 * @param stringToValidate
		 * @return boolean true if the string is in the right format
		 */
		public boolean checkDateGER(String stringToValidate){
			
			if(	stringToValidate.matches(regexGERWithoutDots)){
				
				int day = Integer.valueOf(stringToValidate.substring(0, 2));
				int month = Integer.valueOf(stringToValidate.substring(2, 4));
				int year = Integer.valueOf(stringToValidate.substring(4, 8));
				
				if(month > 12 || month < 1){
					System.err.println("*ERROR DATE GER* Month has to be between 1 and 12");
					return false;
				}
				
				YearMonth ym = YearMonth.of(Integer.valueOf(year), Integer.valueOf(month));
				int daysOfMonth = ym.lengthOfMonth();
				
				if(day > daysOfMonth || day < 1){
					System.err.println("*ERROR DATE GER* Day has between 1 and " + daysOfMonth);
					return false;
				}
				
				return true;
				
			}else if(stringToValidate.matches(regexGERWithDots)){
				
				int day = Integer.valueOf(stringToValidate.substring(0, 2));
				int month = Integer.valueOf(stringToValidate.substring(3, 5));
				int year = Integer.valueOf(stringToValidate.substring(6, 10));
				
				if(month > 12 || month < 1){
					System.err.println("*ERROR DATE GER* Month has to be between 1 and 12");
					return false;
				}
				
				YearMonth ym = YearMonth.of(Integer.valueOf(year), Integer.valueOf(month));
				int daysOfMonth = ym.lengthOfMonth();
				
				if(day > daysOfMonth || day < 1){
					System.err.println("*ERROR DATE GER* Day has between 1 and " + daysOfMonth);
					return false;
				}
				
				return true;			
				
			}else{
				
				System.err.println("*ERROR DATE GER* The String: " + stringToValidate + " must be formatted like dd.mm.yyy or ddmmyyyy");
				return false;
				
			}
			
		}
		
		/**
		 * Checks if the string is in the format (US) mm-dd-yyyy or mm/dd/yyyy or mmddyyyy.
		 * Afterwards it checks if the month is correct (between 1 and 12). <br>
		 * If the month is correct it checks if the month has as many days as given and returns the date as a string. 
		 * @param stringToValidate
		 * @return String date in US format
		 */
		public String validateDateUS(String stringToValidate){
			
			if(checkDateUS(stringToValidate) == true){
				
				if(stringToValidate.matches(regexUSWithSlashAndMinus)){
					
					return  stringToValidate;
					
				}else if(stringToValidate.matches(regexUSWithoutSlashAndMinus)){
					
					String month = stringToValidate.substring(0, 2);
					String day = stringToValidate.substring(2, 4);
					String year = stringToValidate.substring(4, 8);
					
					return month+"/"+day+"/"+year;
					
				}else{
					return "";
				}
				
			}else{
				return "";
			}
			
		}
		
		/**
		 * Checks if the string is in the format (US) mm-dd-yyyy or mm/dd/yyyy or mmddyyyy. <br>
		 * Afterwards it checks if the month is correct (between 1 and 12). <br>
		 * If the month is correct it checks if the month has as many days as given and returns the boolean 
		 * 
		 * @param stringToValidate
		 * @return boolean true if the string is in the right format
		 */
		public boolean checkDateUS(String stringToValidate){
			
			if(	stringToValidate.matches(regexUSWithoutSlashAndMinus)){
				
				int month = Integer.valueOf(stringToValidate.substring(0, 2));
				int day = Integer.valueOf(stringToValidate.substring(2, 4));
				int year = Integer.valueOf(stringToValidate.substring(4, 8));
				
				if(month > 12 || month < 1){
					System.err.println("*ERROR DATE US* Month has to be between 1 and 12");
					return false;
				}
				
				YearMonth ym = YearMonth.of(Integer.valueOf(year), Integer.valueOf(month));
				int daysOfMonth = ym.lengthOfMonth();
				
				if(day > daysOfMonth || day < 1){
					System.err.println("*ERROR DATE US* Day has between 1 and " + daysOfMonth);
					return false;
				}
				
				return true;
				
			}else if(stringToValidate.matches(regexUSWithSlashAndMinus)){
				
				int month = Integer.valueOf(stringToValidate.substring(0, 2));
				int day = Integer.valueOf(stringToValidate.substring(3, 5));
				int year = Integer.valueOf(stringToValidate.substring(6, 10));
				
				if(month > 12 || month < 1){
					System.err.println("*ERROR DATE US* Month has to be between 1 and 12");
					return false;
				}
				
				YearMonth ym = YearMonth.of(Integer.valueOf(year), Integer.valueOf(month));
				int daysOfMonth = ym.lengthOfMonth();
				
				if(day > daysOfMonth || day < 1){
					System.err.println("*ERROR DATE US* Day has between 1 and " + daysOfMonth);
					return false;
				}
				
				return true;	
			
			}else{
				System.err.println("*ERROR DATE US* The String " + stringToValidate + " must be formatted like mm-dd-yyyy or mm/dd/yyyy");
				return false;
			}
			
		}
		
	}
	
	/**
	 * This class checks if vk, amount and priceunit are in the right format and returns the booelan. 
	 * It can also calculate the totalprice and return it as BigDecimal
	 */
	public class ValidateTotalPrice{
		
		/**
		 * Checks if all parameters are in the right format and returns the totalprice as bigdecimal.
		 * <br>
		 * <br>
		 * See also: {@link #checkTotalPrice(String, String, String)}
		 * 
		 * @param String vk
		 * @param String amount
		 * @param String priceunit
		 * @return BigDecimal totalprice
		 */
		public BigDecimal validateTotalPrice(String vk, String amount, String priceunit, int decimals){
			
			if(checkTotalPrice(vk, amount, priceunit) == true){
				
				BigDecimal bdVk = new ValidateCurrency().validateCurrency(vk);
				double dAmount = new ValidateDoubleTwoDigits().validateDouble(amount);
				int iPriceUnit = new ValidateOnlyIntegerPositive().validateOnlyInteger(priceunit);
				
	//			String sTotalPrice = String.valueOf(bdVk.multiply(new BigDecimal(iAmount)).divide(new BigDecimal(iPriceUnit)));
				BigDecimal totalPrice = new BigDecimal(String.valueOf(bdVk.multiply(new BigDecimal(dAmount)).divide(new BigDecimal(iPriceUnit))));
				totalPrice = totalPrice.setScale(decimals, RoundingMode.CEILING);
				System.out.println("total " + totalPrice);
				
				return totalPrice;
				
			}else{
				return new BigDecimal("0.00");
			}
			
		}
		
		/**
		 * Checks if all parameters are in the right format and returns the boolean
		 * <br>
		 * <br>
		 * See also: {@link #validateTotalPrice(String, String, String)}
		 * 
		 * @param String vk
		 * @param String amount
		 * @param String priceunit
		 * @return boolean - true if all paramters are in the right format
		 */
		public boolean checkTotalPrice(String vk, String amount, String priceunit){
			
			if(	new ValidateCurrency().checkCurrency(vk) &&
				new ValidateOnlyInteger().checkOnlyInteger(amount) &&
				new ValidateOnlyIntegerPositive().checkOnlyInteger(priceunit)){
				return true;
			}else{
				return false;
			}			
			
		}
		
	}
	
	/**
	 * This class checks if the string has the right format and returns the boolean.
	 * It can also return the currency as BigDecimal.
	 */
	public class ValidateCurrency{
		
		//regex is for #,00 or #.00 or #
		//[0-9]+ = as much numbers as wanted
		//[,|.]? = followed by a comma or dot (not duty)
		//[0-9]{2}? = followed by two numbers (decimal) (not duty)
		private String regex = "([0-9]+)([.|,]?[0-9]{2})?";
		
		/**
		 * Checks if the string is in the correct format (#,00 or #.00 or #) and returns the BigDecimal
		 * <br>
		 * <br>
		 * See also: {@link #checkCurrency(String)}
		 * 
		 * @param stringToValidate
		 * @return string as BigDecimal
		 */
		public BigDecimal validateCurrency(String stringToValidate){
			
			if(checkCurrency(stringToValidate) == true){
				
				//BigDecimal requires #.00 as format. If the string is formatted like #,00 the comma will be replaced as a dot
				if(	stringToValidate.length() > 3 &&
					Character.toString(stringToValidate.charAt(stringToValidate.length() - 3)).equals(",")){
					
					int beginIndex = 0;
					int endIndex = stringToValidate.length() - 3;
					int beginIndexAfter = stringToValidate.length() - 2;
					
					stringToValidate = stringToValidate.substring(beginIndex, endIndex)+"."+stringToValidate.substring(beginIndexAfter);
				}
				
				//BigDecimal requires decimal format. If the string is without decimal .00 will be automatically added
				else if(! stringToValidate.contains(",") && ! stringToValidate.contains(".")){
					stringToValidate = stringToValidate.concat(".00");
				}
				
				//If stringToValidate is empty it will be automatically set to 0.00
				else if(stringToValidate.isEmpty()){
					stringToValidate = "0.00";
				}
				
				return new BigDecimal(stringToValidate);
				
			}else{
				return new BigDecimal("0.00");
			}
			
		}
		
		/**
		 * Checks if the string is in the format #,00 or #.00 or # and returns the boolean.
		 * <br>
		 * <br>
		 * See also: {@link #validateCurrency(String)}
		 * 
		 * @param stringToValidate
		 * @return boolean if the string is in the right format (#,00 or #.00 or #)
		 */
		public boolean checkCurrency(String stringToValidate){
			
			if(stringToValidate.matches(regex) || stringToValidate.equals("")){	
				return true;
			}else{
				System.err.println("*ERROR CURRENCY* The String: " + stringToValidate + " must be formatted like #,00 or #.00 or #");
				return false;
			}
			
		}
		
	}
	
	/**
	 * This class checks if the string has 5 int chars and returns the boolean.
	 * It can also return the zip as int. *
	 */
	public class ValidateZip{
		
		/**
		 * Checks if the string contains only integers and is at least 5 chars long. It returns the string as an integer or 0
		 * <br>
		 * <br>
		 * See also: {@link #checkZip(String)}
		 * 
		 * @param stringToValidate
		 * @return string as int or 0
		 */
		public int validateZip(String stringToValidate){
			
			if(checkZip(stringToValidate) == true){
				return Integer.valueOf(stringToValidate);
			}else{
				return 0;
			}
			
		}
		
		/**
		 * Checks if the string contains only integer and is at least 5 chars long. It returns the boolean.
		 * <br>
		 * <br>
		 * See also: {@link #validateZip(String)}
		 * 
		 * @param stringToValidate
		 * @return boolean if string contains only integer and is 5 chars long
		 */
		public boolean checkZip(String stringToValidate){
			
			String regex = "[0-9]{5}";
			
			if(stringToValidate.matches(regex)){
				return true;
			}else if(stringToValidate.length() != 5){
				
				System.err.println("*ERROR ZIP* The String: " + stringToValidate + " must be 5 chars long");
				return false;
				
			}else{
				
				System.err.println("*ERROR ZIP* The String: " + stringToValidate + " must be only integer");
				return false;
				
			}
			
		}
		
	}
	
	/**
	 * This class checks if the string has only positive int and returns the boolean
	 * It can also return the string as an int
	 */
	public class ValidateOnlyIntegerPositive{
	
		/**
		 * Checks if the string contains only integers and returns the string as an integer or 0 
		 * <br>
		 * <br>
		 * See also: {@link #checkOnlyInteger(String)}
		 * 
		 * @param stringToValidate
		 * @return string as int or 0
		 */
		public int validateOnlyInteger(String stringToValidate){
			
			if(checkOnlyInteger(stringToValidate) == true){
				return Integer.valueOf(stringToValidate);
			}else{
				return 0;
			}
			
		}
		
		/**
		 * Checks if the string contains only integers and returns the boolean
		 * <br>
		 * <br>
		 * See also: {@link #validateOnlyInteger(String)}
		 * 
		 * @param stringToValidate
		 * @return boolean if string contains only integer
		 */
		public boolean checkOnlyInteger(String stringToValidate){
			
			String regexOnlyInt = "[0-9]+";
			
			if(stringToValidate.matches(regexOnlyInt)){
				return true;
			}else{
				
				System.err.println("*ERROR ONLY INTEGER* The String: " + stringToValidate + " must be only integers");			
				return false;
				
			}	
			
		}
		
	}
	
	/**
	 * This class checks if the string has only int (positive or negative) and returns the boolean
	 * It can also return the string as an int
	 */
	public class ValidateOnlyInteger{
	
		/**
		 * Checks if the string contains only integers and returns the string as an integer or 0 
		 * <br>
		 * <br>
		 * See also: {@link #checkOnlyInteger(String)}
		 * 
		 * @param stringToValidate
		 * @return string as int or 0
		 */
		public int validateOnlyInteger(String stringToValidate){
			
			if(checkOnlyInteger(stringToValidate) == true){
				
				if(stringToValidate.equals("")){
					return 0;
				}else{
					return Integer.valueOf(stringToValidate);
				}
				
			}else{
				return 0;
			}
			
		}
		
		/**
		 * Checks if the string contains only integers and returns the boolean
		 * <br>
		 * <br>
		 * See also: {@link #validateOnlyInteger(String)}
		 * 
		 * @param stringToValidate
		 * @return boolean if string contains only integer
		 */
		public boolean checkOnlyInteger(String stringToValidate){
			
			String regexOnlyInt = "-?[0-9]+";
			
			if(stringToValidate.matches(regexOnlyInt)){
				return true;
			}else if(stringToValidate.equals("")){
				return true;
			}else{
				
				System.err.println("*ERROR ONLY INTEGER* The String: " + stringToValidate + " must be only integers");			
				return false;
				
			}	
			
		}
		
	}
	
	public class ValidateDoubleTwoDigits{
		
		private String regex = "-?[0-9]*([.|,]?[0-9]{0,2})?";
		
		public Double validateDouble(String stringToValidate){
			
			if(checkDouble(stringToValidate) == true){
				
				if(stringToValidate.equals("")){
					return 0.00;
				}
				
			}
			
			return Double.valueOf(stringToValidate);
			
		}
		
		public boolean checkDouble(String stringToValidate){
			
			if(stringToValidate.matches(regex)){
				return true;
			}else{
				System.err.println("*ERROR DOUBLE* The String: " + stringToValidate + " must be formatted like #,00 or #.00 or #");
				return false;
			}
			
		}
		
	}
	
	public class ValidateDoubleFourDigits{
		
		private String regex = "-?[0-9]*([.|,]?[0-9]{0,4})?";
		
		public Double validateDouble(String stringToValidate){
			
			if(checkDouble(stringToValidate) == true){
				return Double.valueOf(stringToValidate);
			}else if(	! stringToValidate.contains(",") ||
						! stringToValidate.contains(".")){
				return Double.valueOf(stringToValidate.concat(".0000"));
			}else if(stringToValidate.isEmpty()){
				return 0.0000;
			}else{
				return 0.0000;
			}
			
		}
		
		public boolean checkDouble(String stringToValidate){
			
			if(stringToValidate.matches(regex)){
				return true;
			}else{
				System.err.println("*ERROR DOUBLE* The String: " + stringToValidate + " must be formatted like #,0000 or #.0000 or #");
				return false;
			}
			
		}
		
	}
	
}
