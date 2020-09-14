/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 10, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.apps.hr.project.form;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.jds.apps.Constants;
import com.jds.apps.hr.project.form.ext.AbstractProjectForm;
import com.jds.architecture.utilities.CalendarIsValid;
import com.jds.architecture.utilities.ObjectIsNull;
import com.jds.architecture.utilities.StringIsEmpty;
import com.jds.architecture.utilities.StringIsValid;
import com.jds.architecture.utilities.StringLengthIsValid;
import com.jds.architecture.utilities.Validator;

/**
 * 
 * 
 * @author ma.j.a.valiente
 * @author last modified by: $Author: tms $
 * @version $Revision: 1.4 $ $Date: 2014/11/11 16:23:13 $
 * 
 */
public class ProjectForm extends AbstractProjectForm {
	private static final long serialVersionUID = 1L;

	// static Logger log = Logger.getLogger(PropertiesMonitor.class);
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		Validator objectIsNull = new Validator(new ObjectIsNull());
		Validator stringIsEmpty = new Validator(new StringIsEmpty());
		Validator calendarIsValid = new Validator(new CalendarIsValid());

		Validator stringIsValidD = new Validator(new StringIsValid("_- ."));

		Validator stringLengthIsValidFifty = new Validator(
				new StringLengthIsValid(50));
		Validator stringLengthIsValidHundred = new Validator(
				new StringLengthIsValid(100));

		// controllo project
		if (objectIsNull.validate(this.getProject())
				|| stringIsEmpty.validate(this.getProject())) {

			errors.add("project", new ActionError("field.null", "Project"));
		}
		if (!stringIsValidD.validate(this.getProject())) {

			errors.add("project", new ActionError(
					"field.invalid.specialcharacter", "Project"));
		}
		if (!stringLengthIsValidFifty.validate(this.getProject())) {

			errors.add("project", new ActionError("field.invalid.length",
					"Project", "50"));
		}
		// controllo client
		if (objectIsNull.validate(this.getClient())
				|| stringIsEmpty.validate(this.getClient())) {

			errors.add("client", new ActionError("field.null", "Client"));
		}
		if (!stringIsValidD.validate(this.getClient())) {

			errors.add("client", new ActionError(
					"field.invalid.specialcharacter", "Client"));
		}
		if (!stringLengthIsValidFifty.validate(this.getClient())) {

			errors.add("client", new ActionError("field.invalid.length",
					"Client", "50"));
		}
		// controllo description
		if (objectIsNull.validate(this.getDescription())
				|| stringIsEmpty.validate(this.getDescription())) {

			errors.add("description", new ActionError("field.null",
					"Description"));
		}
		if (!stringIsValidD.validate(this.getDescription())) {

			errors.add("description", new ActionError(
					"field.invalid.specialcharacter", "Description"));
		}
		if (!stringLengthIsValidHundred.validate(this.getDescription())) {

			errors.add("description", new ActionError("field.invalid.length",
					"Description", "100"));
		}
		// controllo startDay
		if (objectIsNull.validate(this.getStartDay())
				|| stringIsEmpty.validate(this.getStartDay())) {
			errors.add("startDay", new ActionError("field.null", "Start Day"));
		}
		// controllo startMonth
		if (objectIsNull.validate(this.getStartMonth())
				|| stringIsEmpty.validate(this.getStartMonth())) {
			errors.add("startMonth", new ActionError("field.null",
					"Start Month"));
		}
		// controllo startYear
		if (objectIsNull.validate(this.getStartYear())
				|| stringIsEmpty.validate(this.getStartYear())) {
			errors
					.add("startYear", new ActionError("field.null",
							"Start Year"));
		}
		// controllo startDate
		if (!(objectIsNull.validate(this.getStartDay())
				|| stringIsEmpty.validate(this.getStartDay())
				|| objectIsNull.validate(this.getStartMonth())
				|| stringIsEmpty.validate(this.getStartMonth())
				|| objectIsNull.validate(this.getStartYear()) || stringIsEmpty
				.validate(this.getStartYear()))) {
			int[] dob = { Integer.parseInt(this.getStartYear()),
					Integer.parseInt(this.getStartMonth()),
					Integer.parseInt(this.getStartDay()) };
			if (!calendarIsValid.validate(dob)) {
				errors.add("startDate", new ActionError("field.invalid",
						"Start Date"));
			}
		}
		// controllo endDate
		if (!(objectIsNull.validate(this.getEndMonth())
				|| stringIsEmpty.validate(this.getEndMonth())
				|| objectIsNull.validate(this.getEndYear())
				|| stringIsEmpty.validate(this.getEndYear())
				|| objectIsNull.validate(this.getEndDay())
				|| stringIsEmpty.validate(this.getEndDay())
				|| objectIsNull.validate(this.getStartDay())
				|| stringIsEmpty.validate(this.getStartDay())
				|| objectIsNull.validate(this.getStartMonth())
				|| stringIsEmpty.validate(this.getStartMonth())
				|| objectIsNull.validate(this.getStartYear()) || stringIsEmpty
				.validate(this.getStartYear()))) {
			int[] sd = { Integer.parseInt(this.getStartYear()),
					Integer.parseInt(this.getStartMonth()),
					Integer.parseInt(this.getStartDay()) };
			int[] ed = { Integer.parseInt(this.getEndYear()),
					Integer.parseInt(this.getEndMonth()),
					Integer.parseInt(this.getEndDay()) };
			if (!calendarIsValid.validate(ed)) {
				errors.add("endDate", new ActionError("field.invalid",
						"End Date"));
			} else if (calendarIsValid.validate(sd)) {
				Calendar calStart = Calendar.getInstance();
				calStart.set(Integer.parseInt(this.getStartYear()), Integer
						.parseInt(this.getStartMonth()), Integer.parseInt(this
						.getStartDay()));
				Calendar calEnd = Calendar.getInstance();
				calEnd.set(Integer.parseInt(this.getEndYear()), Integer
						.parseInt(this.getEndMonth()), Integer.parseInt(this
						.getEndDay()));
				if (!(calEnd.after(calStart))) {
					errors.add("endDate", new ActionError(
							"field.projectdate.invalid"));
				}
			}
		}

		return errors;
	}

	public List<?> getMonth() {
		Constants cons = new Constants();
		return cons.getMonth();
	}

}
