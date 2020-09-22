package com.jds.architecture.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenSkills;

public class SkillDAO extends DAOConstants{
	
	public static SkillDAO getFactory() throws DAOException{
		return new SkillDAO();
	}
	
	private CachedRowSet rowSet;
	
	private SkillDAO() throws DAOException{
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		}catch(SQLException sql) {
			throw new DAOException("Messaggio", sql);
		}
	}
	
	
	public void create(Connection conn,SkillsInformation entity,StatementGenSkills skill) throws DAOException{
		try {
			rowSet.setCommand(SKILL_FIND_ALL);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			//rowSet.updateString(1, skill.);
			System.out.println("Problema");
			rowSet.updateString(1, entity.getSkillId());
			rowSet.updateString(2,entity.getCategoryId());
			rowSet.updateString(3,entity.getSkillName());
			rowSet.updateString(4,entity.getSkillDescription());
			rowSet.updateString(5, entity.getStatus());
			rowSet.moveToInsertRow();
			rowSet.acceptChanges();
			//conn.commit();
		}catch(SQLException sql) {
			throw new DAOException("Errore",sql);
		}
	}
	
	public void update(Connection conn,SkillsInformation entity) throws DAOException{
		try {
			PreparedStatement ps = conn.prepareStatement(SKILL_UPDATE_MAIN );
			ps.setString(1, entity.getCategoryId());
			ps.setString(2, entity.getSkillName());
			ps.setString(3, entity.getSkillDescription());
			ps.setString(4, entity.getStatus());
			ps.execute();
			conn.commit();
		}catch(SQLException sql) {
			throw new DAOException("Errore",sql);
		}
	}
	
	public void delete(Connection conn,String id) throws DAOException {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SKILL_DELETE);
			ps.setString(1, id);
			ps.execute();
			conn.commit();
		}catch(SQLException sql) {
			throw new DAOException("Errore",sql);
		}
	}
	
	public SkillsInformation[] getAll(Connection conn) throws DAOException{
		SkillsInformation[] skills = null;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SKILL_FIND_ALL);
			rs.last();
			skills = new SkillsInformation[rs.getRow()];
			for(int i=0; rs.next();i++) {
				SkillsInformation skill = new SkillsInformation();
				skill.setSkillId(rs.getString(1));
				skill.setCategoryId(rs.getString(2));
				skill.setCategoryName(rs.getString(3));
				skill.setStatus(rs.getString(4));
				skills[i] = skill;
			}
			rs.close();
		}catch(SQLException sql) {
			throw new DAOException("ERRORE",sql);
		}
		return skills;
	}
	
	public SkillsInformation getById(Connection conn,String id) throws DAOException{
		SkillsInformation skill = null;
		try {
			PreparedStatement ps;
			ps = conn.prepareStatement(SKILL_FIND_BYPK);
			ps.setString(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				skill = new SkillsInformation();
				skill.setSkillId(rs.getString(1));
				skill.setCategoryId(rs.getString(2));
				skill.setCategoryName(rs.getString(3));
				skill.setStatus(rs.getString(4));
			}
		}catch(SQLException sql) {
			throw new DAOException("ERRORE",sql);
		}
		return skill;
	}
	
}
