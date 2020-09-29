package com.jds.businesscomponent.hr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.RowSet;

import com.jds.apps.Constants;
import com.jds.apps.beans.AbstractReferenceData;
import com.jds.apps.beans.SkillCategory;
import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.Logger;
import com.jds.architecture.exceptions.HRSLogicalException;
import com.jds.architecture.exceptions.HRSSystemException;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.SkillCategoryDAO;
import com.jds.architecture.service.dao.SkillDAO;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.jds.architecture.service.idgenerator.IdGeneratorException;
import com.jds.architecture.service.idgenerator.SkillIdGenerator;

import oracle.net.aso.a;

public class SkillCategoryBC {
	private Constants cons;
	private SkillCategoryDAO catDao;
	private DBAccess dbAccess;
	private Logger log;
	private Connection conn;
	
	public SkillCategoryBC() throws DAOException,ClassNotFoundException,IOException, DBAccessException, HRSSystemException {
		try{
			dbAccess = DBAccess.getDBAccess();
			catDao = (SkillCategoryDAO) new SkillCategoryDAO();
			cons = new Constants();
		}catch(DBAccessException e){
			throw new HRSSystemException("Errore", e);
		}	
	}
	
	public void create(SkillCategory info)  throws DAOException,ClassNotFoundException,IOException, DBAccessException, HRSLogicalException, IdGeneratorException, HRSSystemException, SQLException  {
		try {
			if (info == null) 
				throw new HRSLogicalException("invalid.input.exception");
			
				conn = dbAccess.getConnection();
				long x;
				x = SkillIdGenerator.getIstance().getNextId();
				catDao.create(conn, info);
				conn.commit();
				
		}catch (IdGeneratorException e) {
			conn.rollback();
			throw new HRSSystemException("errore", e);
		}catch (DBAccessException e1) {
			conn.rollback();
			throw new HRSSystemException("errore", e1);
		}catch (DAOException e2) {
			conn.rollback();
			throw new HRSSystemException("errore", e2);
		}catch(HRSLogicalException e) {
			e.getMessage();
		}
		finally {
			conn.close();
		}	
	}
	
	public SkillCategory searchCategory(String id) throws DAOException,ClassNotFoundException,IOException, DBAccessException, HRSSystemException  {
		SkillCategory sc = new SkillCategory();
		RowSet rs;
		try {
			if(id == null) {
			throw new HRSLogicalException ("id.required.exception");
			
			conn = dbAccess.getConnection();
			sc.setCategoryId(id);
			rs = (RowSet) ;
			//return ((SkillInformation) rs);
			sc.setSkillId(rs.getString(1));
			sc.setCategoryId(rs.getString(2));
			sc.setSkillName(rs.getString(3));
			sc.setDescription(rs.getString(4));
			sc.setStatus(rs.getString(5));
			//return si;
			//si = (SkillsInformation) rs;
		}
		
		}
		
		
			} catch (HRSLogicalException e1){
				e1.getMessage();
			}
			catch(Exception e) {
				throw new HRSSystemException("Errore", e);
			} 
		}
		return si;
		}
		
	}
	
	//@SuppressWarnings("unchecked")
	public Collection<SkillsInformation> searchApprovedSkills(SkillsInformation dataFind) throws DAOException,ClassNotFoundException,IOException, DBAccessException, HRSSystemException{
		RowSet rs;
		Collection<SkillsInformation> list;
		try {
			conn = dbAccess.getConnection();
			rs = (RowSet) catDao.findByAll();
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
					rs = (RowSet) catDao.findByAll();
					list =  ((Collection<SkillsInformation>) rs);
					return list;
				}catch(Exception e) {
					throw new HRSSystemException("Errore", e);		
				}
			}else {
				conn = dbAccess.getConnection();
				rs = (RowSet) catDao.find(si);
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
				catDao.update(conn, info, catDao);
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
