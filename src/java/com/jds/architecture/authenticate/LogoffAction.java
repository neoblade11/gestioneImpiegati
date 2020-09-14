/*
 * Copyright Accenture 2004 All Rights Reserved.
 * Dec 3, 2004
 * 
 * This software is the proprietary information of Accenture.
 * Use is subject to license terms.
 * 
 */
package com.jds.architecture.authenticate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jds.architecture.abstraction.AbstractAction;



/**
 * 
 *
 * @author r.c.delos.santos
 * @author last modified by: $Author: tms ${date}
 * @version $Revision: 1.1 $ $Date: 2014/11/03 08:36:27 $
 * 
 */
public class LogoffAction extends AbstractAction {

	/**
	 * 
	 */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm form, 
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        /*
         * invalidates session
         */
        request.getSession().invalidate();
        return actionMapping.findForward("success");
    }

}
