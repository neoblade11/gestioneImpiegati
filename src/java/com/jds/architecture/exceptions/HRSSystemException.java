/*
 * Created on Dec 9, 2004
 */
package com.jds.architecture.exceptions;
import org.apache.log4j.Level;


/**
 * @author Eugene Lozada
 */
public class HRSSystemException extends HRSException {
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new HRSSystemException
	 * 
	 * @param messageKey the message key
	 * @param cause the cause of this exception
	 */
	public HRSSystemException(String messageKey, Throwable cause){
		super(messageKey, cause, Level.ERROR.toInt(), true);	
	}
}
