package com.jds.architecture.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.Logger;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;

public class SkillDAO extends DAOConstants implements DataAccessObjectInterface {

	DBAccess dbAcces;
	Logger log;

	protected SkillDAO() throws DBAccessException {
		dbAcces = DBAccess.getDBAccess();
	}

	@Override
	public void create(Connection conn, Object obj) throws DAOException {
		if (obj instanceof SkillsInformation) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SKILL_CREATE);
				ps.setString(1, ((SkillsInformation) obj).getId());
				ps.setString(2, ((SkillsInformation) obj).getCategoryId());
				ps.setString(3, ((SkillsInformation) obj).getName());
				ps.setString(4, ((SkillsInformation) obj).getDescription());
				ps.setString(5, ((SkillsInformation) obj).getStatus());
				ps.execute();
			} catch (SQLException e) {
				throw new DAOException("sql.create.exception.skilldao", e);
			} catch (Exception e) {
				throw new DAOException("create.exception.skilldao", e);
			}
		} else {
			throw new DAOException("invalid.object.skilldao", null);
		}
	}

	@Override
	public boolean remove(Connection conn, Object obj) throws DAOException { // DA RIVEDERE
		if (obj instanceof String && obj != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(SKILL_DELETE);
				ps.setString(1, ((SkillsInformation) obj).getId());
				if (ps.execute() == false)// questo
					return true;
				else
					return false;
			} catch (SQLException e) {
				throw new DAOException("sql.remove.exception.skilldao", e);
			}
		}

		else
			throw new DAOException("invalid.object.skilldao", null);

	}

	@Override
	public Object findByPK(Object obj) throws DAOException {
		if (obj instanceof String && obj != null) {
			try {
				CachedRowSet rs = RowSetProvider.newFactory().createCachedRowSet();
				Connection conn;
				conn = dbAcces.getConnection();
				PreparedStatement ps = conn.prepareStatement(SKILL_FIND_BYPK);
				ps.setString(1, ((String) obj));
				rs = (CachedRowSet) ps.executeQuery();
				conn.close();
				return rs;
			} catch (DBAccessException e) {
				throw new DAOException("cause message key", e);
			} catch (SQLException e) {
				throw new DAOException("sql.findpk.exception.skilldao", e);
			}
		} else {
			throw new DAOException("invalid.object.skilldao", null);
		}
	}

	@Override
	public RowSet findByAll() throws DAOException {
		Connection conn;
		try {
			CachedRowSet rs = RowSetProvider.newFactory().createCachedRowSet();
			conn = dbAcces.getConnection();
			PreparedStatement ps = conn.prepareStatement(SKILL_FIND_ALL);
			rs = (CachedRowSet) ps.executeQuery();
			conn.close();
			return rs;
		} catch (DBAccessException e) {
			throw new DAOException("cause message key", e);
		} catch (SQLException e) {
			throw new DAOException("sql.findall.exception.skilldao", e);
		}
	}

	@Override
	public RowSet find(Object obj) throws DAOException {// DA RIVEDERE
		if (obj instanceof SkillsInformation) {
			try {// non è corretto
				RowSet rs;
				Connection conn;
				conn = dbAcces.getConnection();
				PreparedStatement ps = conn.prepareStatement(SKILL_FIND_BYPK);
				ps.setString(1, ((SkillsInformation) obj).getId());
				ps.execute();

				conn.close();
				return null;
			} catch (DBAccessException e) {
				throw new DAOException("cause message key", e);
			} catch (SQLException e) {
				throw new DAOException("sql.find.exception.skilldao", e);
			}
		} else {
			throw new DAOException("invalid.object.skilldao", null);
		}

	}

	@Override
	public boolean update(Connection conn, Object obj1, Object obj2) throws DAOException {// DA FARE
		if (obj1 != null && obj2 != null && conn != null && obj1 instanceof SkillsInformation
				&& obj2 instanceof SkillsInformation) {
			try {
				PreparedStatement ps = conn.prepareStatement(SKILL_UPDATE_MAIN);
				ps.setString(1, "id = " + ((SkillsInformation) obj1).getId() + ",catId = ");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return false;
		} else
			throw new DAOException("invalid.object.skilldao", null);

	}

}
