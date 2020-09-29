package com.jds.architecture.service.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.jds.apps.beans.ProjectInfo;
import com.jds.architecture.Logger;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;

public class ProjectDAO implements DataAccessObjectInterface {
	private DBAccess dbAccess;
	private Logger Log;
	
	public ProjectDAO() throws DAOException  {
			try{
				dbAccess=DBAccess.getDBAccess();
			}catch (DBAccessException e) {
				throw new DAOException("Constructor", e);
			}
	}

	@Override
	public void create(Connection conn, Object obj) throws DAOException {
		if (obj instanceof ProjectInfo && obj!=null && conn!=null) {
			try {
				PreparedStatement ps=conn.prepareStatement(DAOConstants.PROJ_CREATE);
				ps.setString(1,((ProjectInfo)obj).getId());
				ps.setString(2,((ProjectInfo)obj).getName());
				ps.setString(3,((ProjectInfo)obj).getDescription());
				ps.setDate(4, (Date) ((ProjectInfo)obj).getStartDate());
				ps.setDate(5, (Date) ((ProjectInfo)obj).getEndDate());
				ps.setString(6, ((ProjectInfo)obj).getClient());
				ps.setString(7, ((ProjectInfo)obj).getStatus());
				ps.execute();
			} catch (SQLException e) {
				throw new DAOException("sql.create.exception.projdao", e);
			} catch (Exception e) {
				throw new DAOException("create.exception.projdao", e);
			}		
		} else throw new DAOException("invalid.object.projdao", null);
		
	}

	@Override
	public boolean remove(Connection conn, Object obj) throws DAOException {
		if (obj instanceof String && obj!=null) {
			try {
				PreparedStatement ps=conn.prepareStatement(DAOConstants.PROJ_DELETE);
				ps.setString(1,((String)obj));
				if(ps.execute()==false)
					return true;
				else return false;
			} catch (SQLException e) {
				throw new DAOException("sql.remove.exception.projdao", e);
			} catch (Exception e) {
				throw new DAOException("remove.exception.projdao", e);
			}			
		} else 
			throw new DAOException("invalid.object.projdao", null);
	}

	@Override
	public Object findByPK(Object obj) throws DAOException {
		if (obj instanceof String && obj!=null) {
			try {
				CachedRowSet rs = RowSetProvider.newFactory().createCachedRowSet();
				Connection conn;
				conn = dbAccess.getConnection();
				PreparedStatement ps = conn.prepareStatement(DAOConstants.PROJ_FIND_BYPK);
				ps.setString(1, ((String) obj));
				rs = (CachedRowSet) ps.executeQuery();
				conn.close();
				return rs;
			} catch (DBAccessException e) {
				throw new DAOException("cause message key", e);
			} catch (SQLException e) {
				throw new DAOException("sql.findpk.exception.projdao", e);
			}
		}else throw new DAOException("invalid.object.projdao", null);
	}

	@Override
	public RowSet findByAll() throws DAOException {
		Connection conn;
		try {
			CachedRowSet rs = RowSetProvider.newFactory().createCachedRowSet();
			conn = dbAccess.getConnection();
			PreparedStatement ps = conn.prepareStatement(DAOConstants.PROJ_FIND_ALL);
			rs = (CachedRowSet) ps.executeQuery();
			conn.close();
			return rs;
		} catch (DBAccessException e) {
			throw new DAOException("cause message key", e);
		} catch (SQLException e) {
			throw new DAOException("sql.findall.exception.projdao", e);
		}
	}

	@Override
	public RowSet find(Object obj) throws DAOException {
		/*
		 * if (obj instanceof ProjectInfo && obj!=null) { Connection conn; String st="";
		 * int f=0; try { CachedRowSet rs =
		 * RowSetProvider.newFactory().createCachedRowSet(); if
		 * (((ProjectInfo)obj).getId()!=null) { f=1;
		 * st=st.concat("id="+((ProjectInfo)obj).getId()); }
		 * if(((ProjectInfo)obj).getName()!=null) { if(f==1) st=st.concat(" AND "); f=1;
		 * st=st.concat("name="+((ProjectInfo)obj).getName()); }
		 * if(((ProjectInfo)obj).getDescription()!=null) { if(f==1)
		 * st=st.concat(" AND "); f=1;
		 * st=st.concat("description="+((ProjectInfo)obj).getDescription()); } if
		 * (((ProjectInfo)obj).getStartDate()!=null) { if(f==1) st=st.concat(" AND ");
		 * f=1; st=st.concat("startDate=") }
		 * 
		 * conn = dbAccess.getConnection(); PreparedStatement ps =
		 * conn.prepareStatement(DAOConstants.PROJ_FIND_ALL);
		 * 
		 * }catch (DBAccessException e) { throw new DAOException("cause message key",
		 * e); } catch (SQLException e) { throw new
		 * DAOException("sql.find.exception.projdao", e); }
		 * 
		 * }else throw new DAOException("invalid.object.projdao", null);
		 */
		return null;
	}

	@Override
	public boolean update(Connection conn, Object obj1, Object obj2) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

}
