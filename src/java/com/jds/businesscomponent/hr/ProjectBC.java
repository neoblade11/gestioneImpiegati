package com.jds.businesscomponent.hr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.jds.apps.Constants;
import com.jds.apps.beans.AbstractReferenceData;
import com.jds.apps.beans.SkillCategory;
import com.jds.apps.beans.ProjectInfo;
import com.jds.architecture.Logger;
import com.jds.architecture.exceptions.HRSLogicalException;
import com.jds.architecture.exceptions.HRSSystemException;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.DataAccessObjectInterface;
import com.jds.architecture.service.dao.SkillCategoryDAO;
import com.jds.architecture.service.dao.ProjectDAO;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.jds.architecture.service.idgenerator.IdGeneratorException;
import com.jds.architecture.service.idgenerator.ProjectIdGenerator;

public class ProjectBC {
	private Constants cons;
	private ProjectDAO projectDAO;

	private Connection conn;

	private DBAccess dbAccess;
	private Logger log;

	public ProjectBC() throws DAOException, ClassNotFoundException, IOException, DBAccessException, HRSSystemException {
		try {
			dbAccess = DBAccess.getDBAccess();
			projectDAO = (ProjectDAO) new ProjectDAO();

			cons = new Constants();
		} catch (DBAccessException e) {
			throw new HRSSystemException("Errore", e);
		}
	}

	public void create(ProjectInfo info) throws DAOException, ClassNotFoundException, IOException, DBAccessException,
			HRSSystemException, IdGeneratorException {
		try {
			conn = dbAccess.getConnection();
			if (info.getProjectId() != null && projectDAO.findByPK(info) == null) {
				long x;
				x = ProjectIdGenerator.getIstance().getNextId();

				projectDAO.create(conn, info);
				conn.commit();
			}
		} catch (Exception e) {
			throw new HRSSystemException("Errore", e);
		}
	}

	public ProjectInfo searchProjectInfo(String id)
			throws DAOException, ClassNotFoundException, IOException, DBAccessException, HRSSystemException {
		ProjectInfo si = new ProjectInfo();
		RowSet rs;
		if (id != null) {
			try {
				conn = dbAccess.getConnection();
				si.setProjectId(id);
				rs = (RowSet) projectDAO.findByPK(si);

				si.setProjectId(rs.getString(1));
				si.setProjectName(rs.getString(2));
				si.setDescription(rs.getString(3));
				si.setStatus(rs.getString(4));
			} catch (Exception e) {
				throw new HRSSystemException("Errore", e);
			}
		}
		return si;
	}

	// @SuppressWarnings("unchecked")
	public Collection<ProjectInfo> searchApprovedProjects(ProjectInfo dataFind)
			throws DAOException, ClassNotFoundException, IOException, DBAccessException, HRSSystemException {
		RowSet rs;
		Collection<ProjectInfo> list;
		try {
			conn = dbAccess.getConnection();
			rs = (RowSet) projectDAO.findByAll();
			list = ((Collection<ProjectInfo>) rs);
		} catch (Exception e) {
			throw new HRSSystemException("Errore", e);
		}
		return list;
	}

	public Collection<ProjectInfo> searchReferenceData(AbstractReferenceData dataFind, String approvalType)
			throws DAOException, ClassNotFoundException, IOException, DBAccessException, HRSSystemException {
		// return null;
		RowSet rs;
		Collection<ProjectInfo> list;
		ProjectInfo si = (ProjectInfo) new ProjectInfo();
		if (dataFind == null) {
			try {
				conn = dbAccess.getConnection();
				rs = (RowSet) projectDAO.findByAll();
				list = ((Collection<ProjectInfo>) rs);
				return list;
			} catch (Exception e) {
				throw new HRSSystemException("Errore", e);
			}
		} else {
			conn = dbAccess.getConnection();
			rs = (RowSet) projectDAO.find(si);
			list = ((Collection<ProjectInfo>) rs);

		}
		return null;
	}

	public void updateProject(ProjectInfo info) throws DAOException, ClassNotFoundException, IOException,
			DBAccessException, HRSSystemException, HRSLogicalException {
		if (info != null) {
			try {
				conn = dbAccess.getConnection();
				projectDAO.update(conn, info, projectDAO);
				conn.commit();
			} catch (Exception e) {
				throw new HRSLogicalException("record.not.updated.exception", e);
			}
		}
		if (info.getProjectId() == null) {
			throw new HRSLogicalException("id.required.exception");
		} else {
			throw new HRSLogicalException("invalid.input.exception");
		}

	}

}
