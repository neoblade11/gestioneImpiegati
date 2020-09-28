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
import com.jds.architecture.Logger;
import com.jds.architecture.service.dao.assembler.SkillCategoryAssembler;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;

public class SkillCategoryDAO extends DAOConstants implements DataAccessObjectInterface {
	
	DBAccess dbAcces;
	Logger log;

	public SkillCategoryDAO() throws DBAccessException {
		dbAcces = DBAccess.getDBAccess();
	}

	@Override
	public void create(Connection conn, Object obj) throws DAOException {
		if (obj instanceof SkillCategory) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SKILLCAT_CREATE);
				ps.setString(1, ((SkillCategory) obj).getCategoryId());
				ps.setString(2, ((SkillCategory) obj).getCategoryName());
				ps.setString(3, ((SkillCategory) obj).getCategoryDescription());
				//ps.setString(4, ((SkillCategory) obj).getStatus());
				ps.execute();
			} catch (SQLException e) {
				throw new DAOException("sql.create.exception.skillcategorydao", e);
			} catch (Exception e) {
				throw new DAOException("create.exception.skillcategorydao", e);
			}
		} else {
			throw new DAOException("invalid.object.skillcategorydao", null);
		}
		
	}

	@Override
	public boolean remove(Connection conn, Object obj) throws DAOException {
		if (obj instanceof String && obj != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SKILLCAT_DELETE);
				ps.setString(1, ((SkillCategory) obj).getCategoryId());
				if (ps.execute() == false)// questo
					return true;
				else
					return false;
			} catch (SQLException e) {
				throw new DAOException("sql.remove.exception.skillcategorydao", e);
			}
		}
		else
			throw new DAOException("invalid.object.skillcategorydao", null);
	}
	
	

	@Override
	public Object findByPK(Object obj) throws DAOException {
		if (obj instanceof String && obj != null) {
			try {
				CachedRowSet rs = RowSetProvider.newFactory().createCachedRowSet();
				Connection conn;
				conn = dbAcces.getConnection();
				PreparedStatement ps = conn.prepareStatement(SKILLCAT_FIND_BYPK);
				ps.setString(1, ((String) obj));
				rs = (CachedRowSet) ps.executeQuery();
				conn.close();
				return rs;
			} catch (DBAccessException e) {
				throw new DAOException("cause message key", e);
			} catch (SQLException e) {
				throw new DAOException("sql.findpk.exception.skillcategorydao", e);
			}
		} else {
			throw new DAOException("invalid.object.skillcategorydao", null);
		}
	}

	
	@Override
	public RowSet findByAll() throws DAOException {
		Connection conn;
		try {
			CachedRowSet rs = RowSetProvider.newFactory().createCachedRowSet();
			conn = dbAcces.getConnection();
			PreparedStatement ps = conn.prepareStatement(SKILLCAT_FIND_ALL);
			rs = (CachedRowSet) ps.executeQuery();
			conn.close();
			return rs;
		} catch (DBAccessException e) {
			throw new DAOException("cause message key", e);
		} catch (SQLException e) {
			throw new DAOException("sql.findall.exception.skillcategorydao", e);
		}
	}

	@Override
	public RowSet find(Object obj) throws DAOException {
		if (obj instanceof SkillCategory) {
			try {// non è corretto
				RowSet rs;
				Connection conn;
				conn = dbAcces.getConnection();
				PreparedStatement ps = conn.prepareStatement(SKILLCAT_FIND_BYPK);
				ps.setString(1, ((SkillCategory) obj).getId());
				ps.execute();
				conn.close();
				return null;
			} catch (DBAccessException e) {
				throw new DAOException("cause message key", e);
			} catch (SQLException e) {
				throw new DAOException("sql.find.exception.skillcategorydao", e);
			}
		} else {
			throw new DAOException("invalid.object.skillcategorydao", null);
		}
	}

	@Override
	public boolean update(Connection conn, Object obj1, Object obj2) throws DAOException {
		if (obj1 != null && obj2 != null && conn != null && obj1 instanceof SkillCategory
				&& obj2 instanceof SkillCategory) {
			try {
				PreparedStatement ps = conn.prepareStatement(SKILLCAT_UPDATE_MAIN);
				ps.setString(1, "id = " + ((SkillCategory) obj1).getCategoryId() + ",catId = ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		} else
			throw new DAOException("invalid.object.skillcategorydao", null);
	}
	
	
}
