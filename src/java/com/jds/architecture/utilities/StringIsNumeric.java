/*
 * Created on Mar 10, 2005 
 */
package com.jds.architecture.utilities;

/**
 * Validation strategy object used to determine if the given argument translates
 * to a string containing numbers only.  The method uses the argument object's
 * toString() method to obtain a string representation and validates them using 
 * Character.isLetter() 
 * 
 * The inherited method <code>validate(Object target)</code> accepts an object
 * to be validated.  RuntimeExceptions will be thrown when any other argument is passed 
 * to the method.
 * 
 * Classes that implement the <code>ValidationStrategy</code> interface should be 
 * passed to <code>Validator</code> objects via their constructor or to the 
 * <code>Validator.validate(ValidationStrategy, Object)</code> method
 * 
 * 
 * @author Arthur D. Gerona
 * @see Validator
 * @see ValidationStrategy
 * 
 */
public class StringIsNumeric implements ValidationStrategy {
	private String specialChars;
	
	public StringIsNumeric() {
		this.specialChars = "";
	}
	
	/**
	 * Creates a new string validation strategy object and specifies 
	 * additional characters to be considered as valid
	 * 
	 * @param specialChars String containing characters considered as valid.
	 */
	public StringIsNumeric(String specialChars){
		this.specialChars = specialChars;
	}
	
	/** 
	 * Determines if the object argument contains only valid numbers
	 * 
	 * @param target object argument to be valdiated
	 * @return true if the object argument's string representation only contains 
	 * qualified  Unicode letters and digits , returns false otherwise.
	 * 
	 */
	public boolean validate(Object target) {
		//char[] charArray = target.toString().toCharArray();
		//if (target == null)
		//	throw new NullPointerException();
		//if (target instanceof String) {
		//	try {
		//		Integer.parseInt((String) target);
		//		Long.parseLong((String) target);
		//	}catch(NumberFormatException exc) {
		//		return false;
		//	}
		//	return true;
		//}
		//return false;
		//############################################
		String targetStr = target.toString();
		int targetLen = targetStr.length();
		char[] charArray = targetStr.toCharArray();
		for (int i = 0; i < targetLen; i++) {
			if (Character.isDigit(charArray[i]) || specialCharIsOK(charArray[i])) {
				continue;
			}else {
				return false;
			}
		}
		return true;
	}

	private boolean specialCharIsOK(char c){
		//DA testare
		char[] array = specialChars.toCharArray();
		System.out.println(array);
		for(int i=0;i<array.length;i++)
			if(array[i]==c)
				return true;
		return false;
	}
	
}
