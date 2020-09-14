/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 3, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.apps.hr.employee.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.jds.apps.hr.employee.form.ext.AbstractEmployeeSearchFrom;
import com.jds.architecture.utilities.ObjectIsNull;
import com.jds.architecture.utilities.StringIsAlphaNumeric;
import com.jds.architecture.utilities.StringIsEmpty;
import com.jds.architecture.utilities.StringIsNumeric;
import com.jds.architecture.utilities.StringIsValid;
import com.jds.architecture.utilities.StringLengthIsValid;
import com.jds.architecture.utilities.Validator;

/**
 * 
 *
 * @author r.c.delos.santos
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.2 $ $Date: 2014/11/03 16:29:03 $
 * 
 */
public class EmployeeSearchForm extends AbstractEmployeeSearchFrom {
	private static final long serialVersionUID = 1L;
	private List<?> employees;
	private boolean searchMode;

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

		new Validator(new StringIsNumeric());
		new Validator(new StringIsNumeric(" -"));
		new Validator(new StringIsAlphaNumeric());
		Validator stringIsValidD = new Validator(new StringIsValid("_- ."));
		new Validator(new StringIsValid("_- .\n\r,/\\+#()"));

		String allowedCharactersC = "letters & numbers";
		String allowedCharactersD = allowedCharactersC + ", underscores, dashes, spaces, dots ";
		Validator stringLengthIsValidFifty = new Validator(new StringLengthIsValid(50));

		if (objectIsNull.validate(this.getLastName()) || stringIsEmpty.validate(this.getLastName())) {
			errors.add("lastName", new ActionError("field.null", "Last Name"));
		} else if (!stringIsValidD.validate(this.getLastName())) {
			errors.add("lastName", new ActionError("field.invalid.specialcharacter", "Last Name", allowedCharactersD));
		} else if (!stringLengthIsValidFifty.validate(this.getLastName())) {
			errors.add("lastName", new ActionError("field.invalid.length", "Last Name", "50"));
		}

		if (objectIsNull.validate(this.getFirstName()) || stringIsEmpty.validate(this.getFirstName())) {
			errors.add("firstName", new ActionError("field.null", "First Name"));
		} else if (!stringIsValidD.validate(this.getFirstName())) {
			errors.add("firstName",
					new ActionError("field.invalid.specialcharacter", "First Name", allowedCharactersD));
		} else if (!stringLengthIsValidFifty.validate(this.getFirstName())) {
			errors.add("firstName", new ActionError("field.invalid.length", "First Name", "50"));
		}

		return errors;
	}

	/**
	 * @return Returns the employees.
	 */
	public List<?> getEmployees() {
		return employees;
	}

	/**
	 * @param employees
	 *            The employees to set.
	 */
	public void setEmployees(List<?> employees) {
		this.employees = employees;
	}

	/**
	 * @return Returns the hasResult.
	 */
	public int getListSize() {
		if (employees == null) {
			return -1;
		}

		return employees.size();
	}

	/**
	 * @return Returns the hasResult.
	 */
	public String getRecordCount() {
		return Integer.toString(getListSize());
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

}
