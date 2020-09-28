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
import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.Logger;
import com.jds.architecture.exceptions.HRSLogicalException;
import com.jds.architecture.exceptions.HRSSystemException;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.DataAccessObjectInterface;
import com.jds.architecture.service.dao.SkillCategoryDAO;
import com.jds.architecture.service.dao.SkillDAO;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.jds.architecture.service.idgenerator.IdGeneratorException;
import com.jds.architecture.service.idgenerator.SkillIdGenerator;

public class SkillBC {
	private Constants cons;
	private SkillDAO skillDAO;
	private SkillCategoryDAO catDAO;
	private Connection conn;
	//private DataAccessObjectInterface catDAO;
	private DBAccess dbAccess;
	private Logger log;
	
	
	public SkillBC() throws DAOException,ClassNotFoundException,IOException, DBAccessException, HRSSystemException {
		try{
			dbAccess = DBAccess.getDBAccess();
			skillDAO = (SkillDAO) new SkillDAO();
			catDAO = (SkillCategoryDAO) new SkillCategoryDAO();
			cons = new Constants();
		}catch(DBAccessException e){
			throw new HRSSystemException("Errore", e);
		}	
	}
	
	public void create(SkillsInformation info)  throws DAOException,ClassNotFoundException,IOException, DBAccessException, HRSSystemException, IdGeneratorException  {
		try {
			conn = dbAccess.getConnection();
			if (info.getSkillId() != null && skillDAO.findByPK(info) == null ) {
				long x;
				x = SkillIdGenerator.getIstance().getNextId();
				//info.setSkillId(id);
				//Implementare l'id generation
				skillDAO.create(conn, info);
				conn.commit();
			}
		}catch(Exception e) {
			throw new HRSSystemException("Errore", e);
		}
	}
	
	public SkillsInformation searchSkill(String id) throws DAOException,ClassNotFoundException,IOException, DBAccessException, HRSSystemException  {
		SkillsInformation si = new SkillsInformation();
		RowSet rs;
		if(id != null) {
			try {
				conn = dbAccess.getConnection();
				si.setSkillId(id);
				rs = (RowSet) skillDAO.findByPK(si);
				//return ((SkillInformation) rs);
				si.setSkillId(rs.getString(1));
				si.setCategoryId(rs.getString(2));
				si.setSkillName(rs.getString(3));
				si.setDescription(rs.getString(4));
				si.setStatus(rs.getString(5));
				//return si;
				//si = (SkillsInformation) rs;
			}catch(Exception e) {
				throw new HRSSystemException("Errore", e);
			}
		}
		return si;
	}
	
	//@SuppressWarnings("unchecked")
	public Collection<SkillsInformation> searchApprovedSkills(SkillsInformation dataFind) throws DAOException,ClassNotFoundException,IOException, DBAccessException, HRSSystemException{
		RowSet rs;
		Collection<SkillsInformation> list;
		try {
			conn = dbAccess.getConnection();
			rs = (RowSet) skillDAO.findByAll();
			list =  ((Collection<SkillsInformation>) rs);
		}catch(Exception e) {
			throw new HRSSystemException("Errore", e);		
		}
		return list;
	} 
	
	public Collection<SkillsInformation> searchReferenceData(AbstractReferenceData dataFind, String approvalType) 
			throws DAOException,ClassNotFoundException,IOException, DBAccessException, HRSSystemException{
				//return null;
		RowSet rs;
		Collection<SkillsInformation> list;
		SkillsInformation si = (SkillsInformation ) new SkillsInformation();
			if (dataFind == null) {			
				try {
					conn = dbAccess.getConnection();
					rs = (RowSet) skillDAO.findByAll();
					list =  ((Collection<SkillsInformation>) rs);
					return list;
				}catch(Exception e) {
					throw new HRSSystemException("Errore", e);		
				}
			}else {
				conn = dbAccess.getConnection();
				rs = (RowSet) skillDAO.find(si);
				list =  ((Collection<SkillsInformation>) rs);
				//Da rivedere
			}
			return null;
	}
	
	public void updateSkill(SkillsInformation info) 
			throws DAOException,ClassNotFoundException,IOException, DBAccessException, HRSSystemException, HRSLogicalException{
		if (info != null) {
			try {
				conn = dbAccess.getConnection();
				skillDAO.update(conn, info, catDAO);
				conn.commit();
			}catch(Exception e) {
				throw new HRSLogicalException("record.not.updated.exception", e);
			}
		}if (info.getSkillId() == null){
			throw new HRSLogicalException("id.required.exception");
		}else {
			throw new HRSLogicalException("invalid.input.exception");
		}
	
	}
	
}

