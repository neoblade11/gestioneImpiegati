/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 10, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.apps.hr.skill.set.form.ext;

import com.jds.architecture.abstraction.AbstractForm;

/**
 * 
 *
 * @author ma.j.a.valiente
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.1 $ $Date: 2014/11/03 08:36:22 $
 * 
 */
public abstract class AbstractSkillSearchForm extends AbstractForm {
	private static final long serialVersionUID = 1L;
	private String skillCriteria;

	/**
	 * @return Returns the search criteria for the skill.
	 */
	public String getSkillCriteria() {
		return skillCriteria;
	}

	/**
	 * @param skillCriteria
	 *            The search criteria to set for skill.
	 */
	public void setSkillCriteria(String skillCriteria) {
		if (skillCriteria != null) {
			skillCriteria = skillCriteria.trim();
		}
		this.skillCriteria = skillCriteria;
	}

}
