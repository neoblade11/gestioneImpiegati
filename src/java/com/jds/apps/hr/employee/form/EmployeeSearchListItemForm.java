/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 8, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.apps.hr.employee.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.jds.apps.hr.employee.form.ext.AbstractEmployeeSearchListItemForm;


/**
 * 
 *
 * @author r.c.delos.santos
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.1 $ $Date: 2014/11/03 08:36:19 $
 * 
 */
public class EmployeeSearchListItemForm extends
        AbstractEmployeeSearchListItemForm {

	java.util.Map<String, String> linkDataMap;
    
    /* (non-Javadoc)
     * @see accenture.manila.architecture.abstraction.AbstractForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public ActionErrors validate(ActionMapping mapping, 
            HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();
        return errors;
     }

    /**
     * @return Returns the linkDataMap.
     */
	public java.util.Map<String,String> getLinkDataMap() {
        linkDataMap = new java.util.HashMap<String,String>();
        linkDataMap.put("id", super.getEmployeeId());
        return linkDataMap;
    }

    public String getFullName() {
        return super.getLastName() + ", " + super.getFirstName() + " " + 
        		super.getMiddleName();
    }
}
