/*
 * Created on Feb 3, 2005
 */
package com.jds.architecture.utilities;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import java.text.DateFormat;
/**
 * Transform strategy object used to transform a <code>Calendar</code> instance
 * into an array of strings. This class uses the <code>java.text.DateFormat</code>
 * to provide the formatting.  The array of strings returned will contain 4 indeces:  
 * The first index contains the entire formatted string, followed by the year, month, 
 * and date.
 * 
 * The overriden method <code>transform(Object target)</code> accepts a <code>Calendar</code>
 * or <code>Date</code>object representing the date to be transformed.  RuntimeExceptions will be thrown when any other 
 * argument is passed to the method.
 *
 * Classes that implement the <code>TransformStrategy</code> interface should be 
 * passed to <code>Transformer</code> objects via their constructor or to the 
 * <code>Transformer.transform(TransformStrategy, Object)</code> method.
 *  
 * @author Eugene P. Lozada, Arthur D. Gerona
 * @see Transformer
 * @see TransformStrategy
 */


public class CalendarToStringArray implements TransformStrategy{
	
	Date data;
	DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.US);
	
	
	/**
	 * Constructs a new strategy object using the specified format for its transform rule.
	 * 
	 * @param format format to be used by this transfrom strategy
	 * @see java.text.DateFormat
	 *
	 * Removed this method to enforce compliance with documented functionality
	public CalendarToStringArray(int format) {
		
		df = DateFormat.getDateInstance(format);
		calendarStr = df.format(new Date());
		
	}*/
	// NOTE: modified javadoc - for verification
	/**
	 * Constructs a new strategy object using the default format DateFormat.MEDIUM for its 
	 * transform rule.
	 */
	public CalendarToStringArray(){
		//arrayString = new String[10];
		//System.out.println(df);
		//df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
		
	}
	
	/**
	 * Returns the specified Calendar argument as an array of strings
	 * 
	 * @param target the Calendar to transform
	 * @return array of strings representing the Calendar
	 */
	public Object transform(Object target) {
		String[] arrayString = new String[4];
		//System.out.println(target);
		df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
		try {//Gestisce il calendario
				//arrayString[0] = df.format(String.valueOf((Calendar) target));
				//arrayString[1] = df.format((Calendar) target);
				//System.out.println(arrayString[0]);
				arrayString[1] = String.valueOf(((Calendar) target).get(Calendar.YEAR));
				System.out.println(arrayString[1]);
				//arrayString[2] = df.format(((Calendar) target).get(Calendar.YEAR));
				arrayString[2] = String.valueOf(((Calendar) target).get(Calendar.MONTH));
				//arrayString[3] = df.format(((Calendar) target).get(Calendar.MONTH)+1);
				System.out.println(arrayString[2]);
				arrayString[3] = String.valueOf(((Calendar) target).get(Calendar.DAY_OF_MONTH));
				//arrayString[4] = df.format(((Calendar) target).get(Calendar.DAY_OF_MONTH));
				System.out.println(arrayString[3]);
		}catch(RuntimeException exc) {
			throw new ClassCastException("Il bersaglio deve essere un Calendar");
		}
		return arrayString;	
	}
	
}

