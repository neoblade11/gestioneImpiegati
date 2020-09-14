/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 10, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.apps.hr.project.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.jds.apps.hr.project.form.ext.AbstractProjectSearchForm;
import com.jds.architecture.utilities.ObjectIsNull;
import com.jds.architecture.utilities.StringIsEmpty;
import com.jds.architecture.utilities.StringIsValid;
import com.jds.architecture.utilities.StringLengthIsValid;
import com.jds.architecture.utilities.Validator;

/**
 * 
 *
 * @author ma.j.a.valiente
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.4 $ $Date: 2014/11/04 15:09:11 $
 * 
 */
public class SearchProjectForm extends AbstractProjectSearchForm {
	private static final long serialVersionUID = 1L;
	private List<?> projects;
	public boolean searchMode;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * accenture.manila.architecture.abstraction.AbstractForm#validate(org.apache.
	 * struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		Validator objectIsNull = new Validator(new ObjectIsNull());
		Validator stringIsEmpty = new Validator(new StringIsEmpty());
		Validator stringIsValidD = new Validator(new StringIsValid("_- ."));
		Validator stringLengthIsValidFifty = new Validator(new StringLengthIsValid(50));

		// controllo su project
		if (objectIsNull.validate(this.getProjectCriteria()) || stringIsEmpty.validate(this.getProjectCriteria())) {

			errors.add("invalidAll", new ActionError("search.criteria.null"));
		}
		if (!stringIsValidD.validate(this.getProjectCriteria())) {

			errors.add("project", new ActionError("search.criteria.invalid.specialcharacter", "Project"));
		}
		if (!stringLengthIsValidFifty.validate(this.getProjectCriteria())) {
			errors.add("project", new ActionError("search.criteria.invalid.length", "Project", "50"));
		}
		return errors;
	}

	/**
	 * @return Returns the projects.
	 */
	public List<?> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            The projects to set.
	 */
	public void setProjects(List<?> projects) {
		this.projects = projects;
	}

	/**
	 * @return Returns the search results size.
	 */
	public int getListSize() {
		if (projects == null) {
			return -1;
		}
		return projects.size();
	}

	/**
	 * @return Returns the hasResult.
	 */
	public String getRecordCount() {
		return Integer.toString(getListSize());
	}

	/**
	 * @return Returns the messageKey.
	 */
	public String getMessageKey() {

		if (0 == getListSize()) {
			return "search.zeroresult";
		} else {
			return "search.resultcount";
		}
	}

	/**
	 * @return Returns the searchMode.
	 */
	public boolean isSearchMode() {
		return searchMode;
	}

	/**
	 * @param searchMode
	 *            The searchMode to set.
	 */
	public void setSearchMode(boolean searchMode) {
		this.searchMode = searchMode;
	}
}
