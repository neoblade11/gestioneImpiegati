package com.jds.architecture.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.jds.apps.beans.EmployeeInfo;
import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.Logger;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;

public class EmployeeDAO implements DataAccessObjectInterface {
	
	DBAccess dbAcces;
	Logger log;

	protected EmployeeDAO() throws DBAccessException {
		dbAcces = DBAccess.getDBAccess();
	}
	
	
	@Override
	public void create(Connection conn, Object obj) throws DAOException {
		if (obj instanceof EmployeeInfo) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(DAOConstants.EMPSQL_CREATE);
				ps.setString(1, ((EmployeeInfo) obj).getEmpNo());
				ps.setString(2, ((EmployeeInfo) obj).getFirstName());
				ps.setString(3, ((EmployeeInfo) obj).getLastName());
				ps.setString(4, ((EmployeeInfo) obj).getMiddleName());
				ps.setDate(5, new java.sql.Date(((EmployeeInfo) obj).getDob().getTime()));
				ps.setInt(6, ((EmployeeInfo) obj).getAge());
				ps.setString(7, (Character.valueOf(((EmployeeInfo) obj).getGender()).toString()));
				ps.setString(8, ((EmployeeInfo) obj).getCivilStatus());
				ps.setString(9, ((EmployeeInfo) obj).getCitizenship());
				ps.setString(10, ((EmployeeInfo) obj).getSssNo());
				ps.setString(11, ((EmployeeInfo) obj).getTinNo());
				ps.setString(12, ((EmployeeInfo) obj).getMobilePhoneNo());
				ps.setString(13, ((EmployeeInfo) obj).getHomePhoneNo());
				ps.setString(14, ((EmployeeInfo) obj).getStreet1());
				ps.setString(15, ((EmployeeInfo) obj).getStreet2());
				ps.setString(16, ((EmployeeInfo) obj).getCity());
				ps.setString(17, ((EmployeeInfo) obj).getState());
				ps.setString(18, ((EmployeeInfo) obj).getCountry());
				ps.setString(19, ((EmployeeInfo) obj).getEducationalAttainment());
				ps.setString(20, ((EmployeeInfo) obj).getRecognitions());
				ps.execute();
			} catch (SQLException e) {
				throw new DAOException("sql.create.exception.employeedao", e);
			} catch (Exception e) {
				throw new DAOException("create.exception.employeedao", e);
			}
		} else {
			throw new DAOException("invalid.object.employeedao", null);
		}
		
	}

	@Override
	public boolean remove(Connection conn, Object obj) throws DAOException {
		if (obj instanceof String && obj != null) {
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement(DAOConstants.EMPSQL_DELETE);
				ps.setString(1, ((EmployeeInfo) obj).getEmpNo());
				if (ps.execute() == false)// questo
					return true;
				else
					return false;
			} catch (SQLException e) {
				throw new DAOException("sql.remove.exception.employeedao", e);
			}
		}

		else
			throw new DAOException("invalid.object.employeedao", null);
	}

	@Override
	public Object findByPK(Object obj) throws DAOException {
		if (obj instanceof String && obj != null) {
			try {
				CachedRowSet rs = RowSetProvider.newFactory().createCachedRowSet();
				Connection conn;
				conn = dbAcces.getConnection();
				PreparedStatement ps = conn.prepareStatement(DAOConstants.EMPSQL_FIND_BYPK);
				ps.setString(1, ((String) obj));
				rs = (CachedRowSet) ps.executeQuery();
				conn.close();
				return rs;
			} catch (DBAccessException e) {
				throw new DAOException("cause message key", e);
			} catch (SQLException e) {
				throw new DAOException("sql.findpk.exception.employeedao", e);
			}
		} else {
			throw new DAOException("invalid.object.employeedao", null);
		}
	}

	@Override
	public RowSet findByAll() throws DAOException {
		Connection conn;
		try {
			CachedRowSet rs = RowSetProvider.newFactory().createCachedRowSet();
			conn = dbAcces.getConnection();
			PreparedStatement ps = conn.prepareStatement(DAOConstants.EMPSQL_FIND_ALL);
			rs = (CachedRowSet) ps.executeQuery();
			conn.close();
			return rs;
		} catch (DBAccessException e) {
			throw new DAOException("cause message key", e);
		} catch (SQLException e) {
			throw new DAOException("sql.findall.exception.employeedao", e);
		}
	}

	@Override
	public RowSet find(Object obj) throws DAOException {
		if (obj instanceof EmployeeInfo) {
			try {// non è corretto
				RowSet rs;
				Connection conn;
				//String st = "";
				//try {
				//	if(((EmployeeInfo) obj).getEmpNo() != null) {
				//		st=st.concat("empno"+((EmployeeInfo)obj).getEmpNo()+" AND");
				//	}
				//}
				conn = dbAcces.getConnection();
				PreparedStatement ps = conn.prepareStatement(DAOConstants.EMPSQL_FIND_BYPK);
				ps.setString(1, ((EmployeeInfo) obj).getEmpNo());
				ps.execute();
				conn.close();
				return null;
			} catch (DBAccessException e) {
				throw new DAOException("cause message key", e);
			} catch (SQLException e) {
				throw new DAOException("sql.find.exception.employeedao", e);
			}
		} else {
			throw new DAOException("invalid.object.employeedao", null);
		}
	}

	@Override
	public boolean update(Connection conn, Object obj1, Object obj2) throws DAOException {
		if (obj1 != null && obj2 != null && conn != null && obj1 instanceof SkillsInformation
				&& obj2 instanceof EmployeeInfo) {
			try {
				PreparedStatement ps = conn.prepareStatement(DAOConstants.EMPSQL_UPDATE);
				ps.setString(1, "empno = " + ((EmployeeInfo) obj1).getEmpNo() + ",catId = ");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return false;
		} else
			throw new DAOException("invalid.object.employeedao", null);
	}

}
