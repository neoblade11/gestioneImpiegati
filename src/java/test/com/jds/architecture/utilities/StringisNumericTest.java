package test.com.jds.architecture.utilities;

import junit.framework.TestCase;


import com.jds.architecture.utilities.StringIsNumeric;


public class StringisNumericTest extends TestCase {
	
	private String normTarget1 = "13494395495494993034404";
	private String normTarget2 = "458653865865856#434343#342234";
	private String normTarget3 = "73wehwfer43843934943c4rrm";
	private final String normTarget4 = "$Test123%";
	
	
	/*
	 * @see TestCase#setUp()
	 */
	
	
	public void testValidateNormal() {
		StringIsNumeric stringIsNumeric = new StringIsNumeric("!#@");
		assertEquals(true, stringIsNumeric.validate(normTarget1));
	}
	
	
}
