/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 10, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.apps.hr.project.form;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.jds.apps.hr.project.form.ext.AbstractSearchListItemForm;


/**
 * 
 *
 * @author ma.j.a.valiente
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.1 $ $Date: 2014/11/03 08:36:19 $
 * 
 */
public class SearchListItemForm extends AbstractSearchListItemForm {
	private static final long serialVersionUID = 1L;

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		return errors;
	}
	
	java.util.Map<String, Object> projectMap;
	
	public java.util.Map<String, Object> getProjectMap(){
		projectMap = new HashMap<String, Object>();
		projectMap.put("id", super.getProjectId());
		return this.projectMap; 
	}
}
