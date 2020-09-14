/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 10, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.apps.hr.project.form.ext;

import com.jds.architecture.abstraction.AbstractForm;

/**
 * 
 *
 * @author ma.j.a.valiente
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.1 $ $Date: 2014/11/03 08:36:18 $
 * 
 */
public abstract class AbstractProjectSearchForm extends AbstractForm {
	private static final long serialVersionUID = 1L;
	private String projectCriteria;

    /**
     * @return Returns the search criteria for the project.
     */
    public String getProjectCriteria() {
        return projectCriteria;
    }
    
    /**
     * @param projectCriteria The search criteria to set for project.
     */
    public void setProjectCriteria(String projectCriteria) {
        if( projectCriteria != null ){
            projectCriteria = projectCriteria.trim();
        }
        this.projectCriteria = projectCriteria;
    }
    
 
}
