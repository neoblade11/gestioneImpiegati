package com.jds.architecture.service.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.jds.apps.beans.SkillCategory;
import com.jds.architecture.service.dao.assembler.SkillCategoryAssembler;

public class SkillCategoryDAO extends DAOConstants {
	
	public static SkillCategoryDAO getFactory() throws DAOException{
		return new SkillCategoryDAO();
	}
	
	private CachedRowSet rowSet;
	
	private SkillCategoryDAO() throws DAOException{
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		}catch(SQLException sql) {
			throw new DAOException("Messaggio", sql);
		}
	}
	
	private SkillCategoryAssembler sca = new SkillCategoryAssembler();
	
	public void create(Connection conn,Object object) throws DAOException{
		try {
			rowSet.setCommand(SKILLCAT_FIND_ALL);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
		}catch(SQLException sql) {
			throw new DAOException("Errore",sql);
		}
	}
	
}
