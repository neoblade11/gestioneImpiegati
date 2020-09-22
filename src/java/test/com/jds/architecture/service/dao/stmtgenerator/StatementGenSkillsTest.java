package test.com.jds.architecture.service.dao.stmtgenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.assembler.SkillAssembler;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenSkills;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;

import junit.framework.TestCase;

public class StatementGenSkillsTest extends TestCase {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBAccess dba;
	
	
	public void setUp() throws DAOException,ClassNotFoundException,IOException, DBAccessException {
		dba = DBAccess.getDBAccess();
		conn = dba.getConnection();
		System.out.println("ConnessioneStabilita");
	}
	
	public void testCreaGenSkill() {
		SkillsInformation skill = new SkillsInformation();
		skill.setSkillId("a12a");
		skill.setCategoryId("1081");
		skill.setSkillName("asdrubaleo");
		skill.setSkillDescription("pffdsdffsd");
		skill.setStatus("APPROVED");
		StatementGenSkills sgs = new StatementGenSkills();
		try {
			stmt = conn.prepareStatement("INSERT INTO skill(id,catId,name,description,status) VALUES (?,?,?,?,?)");
			SkillAssembler.getPreparedStatement(skill, stmt);
			rs = stmt.executeQuery();
			System.out.println("riuscito");
			rs.close();
			//conn.rollback();
			conn.close();
		}catch(Exception e) {
			System.out.println("fallito");
		}
	}
	
	
}
