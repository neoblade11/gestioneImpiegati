/*
 * Created on Feb 2, 2005
 */
package com.jds.architecture.utilities;

/**
 * Validation strategy object used to determine if the argument object can
 * translates to an integer a value. The method uses the argument object's
 * toString() method to obtain its string representation and the
 * <code>Integer.parseInteger(String)</code> method for parsing.
 * 
 * The overriden method <code>transform(Object target)</code> accepts any
 * object. RuntimeExceptions will be thrown when any other argument is passed to
 * the method.
 * 
 * Classes that implement the <code>ValidationStrategy</code> interface should
 * be passed to <code>Validator</code> objects via their constructor or to the
 * <code>Validator.validate(ValidationStrategy, Object)</code> method
 * 
 * @author Eugene P. Lozada, Arthur D. Gerona
 * @see Validator
 * @see ValidationStrategy
 * 
 */
public class StringIsInt implements ValidationStrategy {

	/**
	 * Determines if the string representation of the argument object can be
	 * translated as an integer.
	 * 
	 * @param target the object to be validated
	 * @return boolean true if the object can be parsed as an integer, false
	 *         otherwise
	 */
	public boolean validate(Object target) {

		if (target == null)
			throw new NullPointerException();

		if (target instanceof String) {
			try {
				Integer.parseInt((String) target);
			} catch (NumberFormatException exc) {
				return false;
			}
			return true;
		}
		return false;
	}

}

