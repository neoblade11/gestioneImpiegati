/*
 * Created on Dec 7, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jds.apps.hr.skill.category.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.jds.apps.hr.skill.category.form.ext.AbstractCategoryForm;
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
 * @version $Revision: 1.3 $ $Date: 2014/11/11 16:23:13 $
 * 
 */
public class CategoryForm extends AbstractCategoryForm {
	private static final long serialVersionUID = 1L;

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		Validator objectIsNull = new Validator(new ObjectIsNull());
		Validator stringIsEmpty = new Validator(new StringIsEmpty());
		Validator stringIsValidD = new Validator(new StringIsValid("_- ."));

		String allowedCharactersC = "letters & numbers";
		String allowedCharactersD = allowedCharactersC + ", underscores, dashes, spaces, dots ";

		Validator stringLengthIsValidFifty = new Validator(new StringLengthIsValid(50));
		Validator stringLengthIsValidHundred = new Validator(new StringLengthIsValid(100));

		if (objectIsNull.validate(this.getCategory()) || stringIsEmpty.validate(this.getCategory())) {
			errors.add("category", new ActionError("field.null", "Category"));
		} else if (!stringIsValidD.validate(this.getCategory())) {
			errors.add("category", new ActionError("field.invalid.specialcharacter", "Category", allowedCharactersD));
		} else if (!stringLengthIsValidFifty.validate(this.getCategory())) {
			errors.add("category", new ActionError("field.invalid.length", "Category", "50"));
		}

		if (objectIsNull.validate(this.getDescription()) || stringIsEmpty.validate(this.getDescription())) {
			errors.add("description", new ActionError("field.null", "Description"));
		} else if (!stringIsValidD.validate(this.getDescription())) {
			errors.add("description",
					new ActionError("field.invalid.specialcharacter", "Description", allowedCharactersD));
		} else if (!stringLengthIsValidHundred.validate(this.getDescription())) {
			errors.add("description", new ActionError("field.invalid.length", "Description", "100"));
		}
		return errors;
	}

}
