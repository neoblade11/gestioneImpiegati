/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 29, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.architecture.exceptions;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;

/**
 * 
 *
 * @author r.c.delos.santos
 * @author last modified by: $Author: tms $
 * @version $Revision: 1.1 $ $Date: 2014/11/03 08:36:23 $
 * 
 */
public class ErrorHandler extends ActionErrors {
	private static final long serialVersionUID = 1L;

	public ErrorHandler(String siteAddress, HRSSystemException exception) {
        ActionError errorMessageKey = new ActionError(exception.getMessageKey());
        this.add("msgKey", errorMessageKey);
        
    }
}
