/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 3, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.apps.hr.skill.category.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.jds.apps.hr.skill.category.form.ext.AbstractCategorySearchForm;
import com.jds.architecture.utilities.StringIsEmpty;
import com.jds.architecture.utilities.StringIsValid;
import com.jds.architecture.utilities.StringLengthIsValid;
import com.jds.architecture.utilities.Validator;

/**
 * 
 * 
 * @author ma.j.a.valiente
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.3 $ $Date: 2014/11/04 14:26:08 $
 * 
 */
public class SearchCategoryForm extends AbstractCategorySearchForm {
	private static final long serialVersionUID = 1L;
	private List<?> categories = null;
	private boolean searchMode;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * accenture.manila.architecture.abstraction.AbstractForm#validate(org.apache
	 * .struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		Validator stringIsEmpty = new Validator(new StringIsEmpty());
		Validator stringIsValidD = new Validator(new StringIsValid("_- ."));
		Validator stringLengthIsValidFifty = new Validator(new StringLengthIsValid(50));

		String allowedCharactersC = "letters & numbers";
		String allowedCharactersD = allowedCharactersC + ", underscores, dashes, spaces, dots ";

		if (stringIsEmpty.validate(this.getCategoryCriteria())) {
			errors.add("invalidAll", new ActionError("search.criteria.null"));
		}

		/*
		 * Controlla se i criteri di ricerca abilità hanno un carattere speciale
		 * controlla se i criteri di ricerca abilità superano la lunghezza massima
		 */
		if (!stringIsValidD.validate(this.getCategoryCriteria())) {
			errors.add("category",
					new ActionError("search.criteria.invalid.specialcharacter", "Category", allowedCharactersD));
		} else if (!stringLengthIsValidFifty.validate(this.getCategoryCriteria())) {
			errors.add("category", new ActionError("search.criteria.invalid.length", "Category", "50"));
		}

		return errors;
	}

	/**
	 * @return Returns the categories.
	 */
	public List<?> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            The categories to set.
	 */
	public void setCategories(List<?> categories) {
		this.categories = categories;
	}

	/**
	 * @return Returns the search results size.
	 */
	public int getListSize() {
		if (categories == null) {
			return -1;
		}
		return categories.size();
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
