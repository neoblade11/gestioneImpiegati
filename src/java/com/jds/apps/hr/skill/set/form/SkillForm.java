/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 10, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.apps.hr.skill.set.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.jds.apps.hr.skill.set.form.ext.AbstractSkillForm;
import com.jds.architecture.exceptions.HRSLogicalException;
import com.jds.architecture.exceptions.HRSSystemException;
import com.jds.architecture.utilities.ObjectIsNull;
import com.jds.architecture.utilities.StringIsEmpty;
import com.jds.architecture.utilities.StringIsValid;
import com.jds.architecture.utilities.StringLengthIsValid;
import com.jds.architecture.utilities.Transformer;
import com.jds.architecture.utilities.VOCollectionToMap;
import com.jds.architecture.utilities.Validator;
//import com.jds.businesscomponent.HRManager;
import com.jds.businesscomponent.stub.HRManager;

/**
 * 
 *
 * @author ma.j.a.valiente
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.6 $ $Date: 2014/11/12 11:04:13 $
 * 
 */
public class SkillForm extends AbstractSkillForm {
	private static final long serialVersionUID = 1L;

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		Validator objectIsNull        =  new Validator( new ObjectIsNull() );
        Validator stringIsEmpty       =  new Validator ( new StringIsEmpty() );
        

        Validator stringIsValidD = new Validator( new StringIsValid("_- .") );
        
        Validator stringLengthIsValidFifty       = new Validator( new StringLengthIsValid(50) );        
        if( objectIsNull.validate(this.getSkill()) ||
                stringIsEmpty.validate( this.getSkill() ) ){
        	errors.add("skill", new ActionError("field.null","Skill"));
        }
        else if( !stringIsValidD.validate( this.getSkill() ) ){
        	
			errors.add("skill", new ActionError("field.invalid.specialcharacter", "Skill"));
        }
        else if( !stringLengthIsValidFifty.validate( this.getSkill() ) ){
			errors.add("skill", new ActionError("field.invalid.length", "Skill", "50"));        	
        }   

        if( objectIsNull.validate(this.getDescription()) ||
                stringIsEmpty.validate( this.getDescription()) ){
        	errors.add("description", new ActionError("field.null","Description"));
        }
        else if( !stringIsValidD.validate( this.getDescription() ) ){
			errors.add("description", new ActionError("field.invalid.specialcharacter", "Description"));
        }
        else if( !stringLengthIsValidFifty.validate( this.getDescription()) ){
			errors.add("skill", new ActionError("field.invalid.length", "Description", "100"));        	
        }
              
        return errors;
	}
	
	public List<?> getCategories() {
	    Transformer list = new Transformer( new VOCollectionToMap() );
	    
	    List<?> tempList = new ArrayList<Object>();
		try {
		    HRManager hr = HRManager.getInstance();
		    tempList = (List<?>)list.transform( hr.searchApprovedCategories(null) );		    
		} catch (HRSLogicalException logicalException) {
            // create error 
            ActionErrors errors = new ActionErrors();
            errors.add("skillFormError", new ActionError(
                    logicalException.getMessage()));
		} catch (HRSSystemException e) {
			e.printStackTrace();
		}
		return  tempList;
	}
}
