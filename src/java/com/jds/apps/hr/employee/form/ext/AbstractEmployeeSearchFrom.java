/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 3, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.apps.hr.employee.form.ext;

import com.jds.architecture.abstraction.AbstractForm;

/**
 * 
 *
 * @author r.c.delos.santos
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.1 $ $Date: 2014/11/03 08:36:23 $
 * 
 */
public abstract class AbstractEmployeeSearchFrom extends AbstractForm {
	private static final long serialVersionUID = 1L;
	private String firstName;

    /**
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * @param firstName The firstName to set.
     */
    public void setFirstName(String firstName) {
        if( firstName != null ){
            firstName = firstName.trim();
        }
        this.firstName = firstName;
    }
    
    private String lastName;
    
    /**
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName) {
        if( lastName != null ){
            lastName = lastName.trim();
        }
        this.lastName = lastName;
    }

}
