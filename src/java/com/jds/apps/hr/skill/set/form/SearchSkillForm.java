/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 10, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.apps.hr.skill.set.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.jds.apps.hr.skill.set.form.ext.AbstractSkillSearchForm;
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
 * @author ma.j.a.valiente
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.3 $ $Date: 2014/11/04 15:41:47 $
 * 
 */
public class SearchSkillForm extends AbstractSkillSearchForm {
	private static final long serialVersionUID = 1L;
	private List<?> skills;
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

		if (objectIsNull.validate(this.getSkillCriteria()) || stringIsEmpty.validate(this.getSkillCriteria())) {
			errors.add("skill", new ActionError("search.criteria.null"));
		} else if (!stringIsValidD.validate(this.getSkillCriteria())) {
			errors.add("skill",
					new ActionError("search.criteria.invalid.specialcharacter", "Skill", allowedCharactersD));
		} else if (!stringLengthIsValidFifty.validate(this.getSkillCriteria())) {
			errors.add("skill", new ActionError("search.criteria.invalid.length", "Skill", "50"));
		}
		return errors;
	}

	/**
	 * @return Returns the skills.
	 */
	public List<?> getSkills() {
		return skills;
	}

	/**
	 * @param skills
	 *            The skills to set.
	 */
	public void setSkills(List<?> listSize) {
		this.skills = listSize;
	}

	/**
	 * @return Returns the search results size.
	 */
	public int getListSize() {
		if (skills == null) {
			return -1;
		}
		return skills.size();
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
