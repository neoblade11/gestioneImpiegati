package com.jds.architecture.service.idgenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;

public class SkillIdGenerator implements IdGeneratorInterface{
	private static SkillIdGenerator istance;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private DBAccess dba;
	
	private SkillIdGenerator() throws DAOException,ClassNotFoundException,IOException, DBAccessException{
		dba = DBAccess.getDBAccess();
		conn = dba.getConnection();
	}
	
	public static SkillIdGenerator getIstance() throws DAOException,ClassNotFoundException,IOException, DBAccessException{
		if (istance == null)
			istance = new SkillIdGenerator();
		return istance;
	}
	
	@Override
	public long getNextId() throws IdGeneratorException, ClassNotFoundException, IOException, DAOException {
		long id = 0;
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(DAOConstants.NEXT_SQN_SKILLS);
			rs.next();
			id = rs.getLong(1);
		}catch(SQLException sql){
			throw new DAOException("ERRORE NELLA CONNESSIONE NON SIAMO RIUSCITI A REPERIRE L'ID DI PROJECT",sql,10,true);
		}
		return id;
	}
}
