package com.socgen.excercise.kata.bank.helpers;

/**
 * @author Farah
 * 
 * <p>Helper class to check data validity.</p>
 */
public class DataChecker {

	/**
	 * <p>Check if a string is parsable into an unsigned number.</p>
	 * 
	 * @param str data to be checked
	 * @return a boolean equal to true if the string is parsable into an unsigned number, else false.
	 */
	public static boolean checkIsUnsignedAmount(String str)
	{
		return str.matches("^\\d+(\\.\\d{1,})?$");
	}

}
