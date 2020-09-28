package com.jds.businesscomponent.hr;

import java.io.IOException;

import com.jds.apps.Constants;
import com.jds.architecture.Logger;
import com.jds.architecture.exceptions.HRSSystemException;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.DataAccessObjectInterface;
import com.jds.architecture.service.dao.SkillDAO;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;

public class SkillBC {
	private Constants cons;
	private SkillDAO skillDAO;
	private DataAccessObjectInterface catDAO;
	private DBAccess dbAccess;
	private Logger log;
	
	public SkillBC() throws DAOException,ClassNotFoundException,IOException, DBAccessException, HRSSystemException {
		try{
			dbAccess = DBAccess.getDBAccess();
		}catch(DBAccessException e){
			throw new HRSSystemException("Errore", e);
			}
		}
	
}

