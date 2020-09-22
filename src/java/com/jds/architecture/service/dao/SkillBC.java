package com.jds.architecture.service.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenSkills;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.jds.architecture.service.idgenerator.SkillIdGenerator;

public class SkillBC {
	private Connection conn;
	private SkillIdGenerator idGen;
	private DBAccess dba;
	
	public SkillBC() throws DAOException,ClassNotFoundException,IOException, DBAccessException{
		dba = DBAccess.getDBAccess();
		idGen = SkillIdGenerator.getIstance();
	}
	
	public void createOrUpdate(SkillsInformation skill,StatementGenSkills st) 
			throws DAOException,ClassNotFoundException,IOException, DBAccessException {
		if(skill.getSkillId() == null ) {
			SkillDAO.getFactory().create(conn,skill,st);
		}else {
			SkillDAO.getFactory().update(conn, skill);
		}
	}
	
	public void delete(String id) 
			throws DAOException,ClassNotFoundException,IOException, DBAccessException {
		SkillDAO.getFactory().delete(conn, id);
	}
	
	public SkillsInformation[] getSkill() throws DAOException{
			return SkillDAO.getFactory().getAll(conn);
	}
	
	public SkillsInformation getById(String id) throws DAOException {
		return SkillDAO.getFactory().getById(conn, id);
	}
	
	
	
}
