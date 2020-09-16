/*
 * Created on Feb 9, 2005
 */
package com.jds.architecture.utilities;

import java.util.Calendar;
import java.util.Date;

/**
 * Transform strategy object used to transform a <code>Calendar</code> instance
 * to an array of integers containing that <code>Calendar's</code> {year, month,
 * date}. Date standard used by the array will use those defined by the
 * <code>CalendarConstants</code> class.
 * 
 * The overriden method <code>transform(Object target)</code> accepts a
 * <code>Calendar</code> or <code>Date</code> object representing the date to be
 * transformed. RuntimeExceptions will be thrown when any other argument is
 * passed to the method.
 * 
 * Classes that implement the <code>TransformStrategy</code> interface should be
 * passed to <code>Transformer</code> objects via their constructor or to the
 * <code>Transformer.transform(TransformStrategy, Object)</code> method.
 * 
 * @author Eugene P. Lozada, Arthur D. Gerona
 * @see Transformer
 * @see TransformStrategy
 */
public class CalendarToIntArray implements TransformStrategy {

	/**
	 * Returns an equivalent integer array representation of argument
	 * <code>Calendar</code> instance
	 * 
	 * @param target <code>Calendar</code> instance to transform
	 * @return array of integers representing the Calendar [year, month, date]
	 *
	 */
	public Object transform(Object target) {
		int res[] = new int[3];
		Calendar tmp = Calendar.getInstance();

		try {
			if (target instanceof Date) {
				tmp.setTime((Date) target);
				res[0] = tmp.get(Calendar.YEAR);
				res[1] = tmp.get(Calendar.MONTH) + 1;
				res[2] = tmp.get(Calendar.DAY_OF_MONTH);
				//System.out.println(" " + res[0] + " " + res[1] + " " + res[2]);

			}
			else {// dovrebbe andare bene
				res[0] = ((Calendar) target).get(Calendar.YEAR);
				res[1] = ((Calendar) target).get(Calendar.MONTH) + 1;
				res[2] = ((Calendar) target).get(Calendar.DAY_OF_MONTH);
				//System.out.println(" " + res[0] + " " + res[1] + " " + res[2]);
			}
			
		} catch (RuntimeException exc) {
			throw new ClassCastException("target must be a Date or a Calendar");
		}

		return res;

	}
}
