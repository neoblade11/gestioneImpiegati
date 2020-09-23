package com.jds.architecture.service.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.jds.apps.beans.SkillCategory;
import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.assembler.SkillCategoryAssembler;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;

public class SkillCategoryDAO extends DAOConstants {
	private DBAccess dba;
	
	
	
	public static SkillCategoryDAO getFactory() throws DAOException,ClassNotFoundException,IOException, DBAccessException{
		return new SkillCategoryDAO();
	}
	
	
	
	private CachedRowSet rowSet;
	
	private SkillCategoryDAO() throws DAOException,ClassNotFoundException,IOException, DBAccessException{
		try {
			dba = DBAccess.getDBAccess();
			//conn = dba.getConnection();
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		}catch(SQLException sql) {
			throw new DAOException("Messaggio", sql);
		}
	}
	
	
	
	//private SkillCategoryAssembler sca = new SkillCategoryAssembler();
	
	
	public void create(Connection conn,Object object) throws DAOException{
		try {
			rowSet.setCommand(SKILLCAT_FIND_ALL);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateString(1,((SkillCategory) object).getCategoryId());
			rowSet.updateString(2,((SkillCategory) object).getCategoryName());
			rowSet.updateString(3,((SkillCategory) object).getDescription());
			rowSet.updateString(4,((SkillCategory) object).getStatus());
			rowSet.moveToInsertRow();
			rowSet.acceptChanges();
			conn.commit();
		}catch(SQLException sql) {
			throw new DAOException("Errore",sql);
		}
	}
	
	public SkillCategory getById(Object object) throws DAOException,ClassNotFoundException,IOException, DBAccessException{
		RowSet find;
		Connection conn = dba.getConnection();
		SkillCategory skill = null;
		try {
			PreparedStatement ps;
			ps = conn.prepareStatement(SKILLCAT_FIND_BYPK);
			ps.setString(1,((SkillCategory) object).getCategoryId());
		}catch(SQLException sql){
			throw new DAOException("Errore",sql);
		}
		return null;
	}
	
	
}
