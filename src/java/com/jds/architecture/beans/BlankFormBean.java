/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 3, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.architecture.beans;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.jds.architecture.abstraction.AbstractForm;


/**
 * 
 *
 * @author r.c.delos.santos
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.1 $ $Date: 2014/11/03 08:36:27 $
 * 
 */
public class BlankFormBean extends AbstractForm {
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
     * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {
        return null;
    }

}
