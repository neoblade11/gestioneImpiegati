package test.com.jds.architecture.service.dao;

import java.sql.Connection;

import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.SkillDAO;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenSkills;
import com.jds.architecture.service.dbaccess.DBAccess;

import junit.framework.TestCase;

public class SkillDAOTest extends TestCase {
	private static StatementGenSkills st;
	private static SkillsInformation skills;
	private static Connection conn;
	private static DBAccess dba;
	
	public void setUp() throws Exception{
		dba = DBAccess.getDBAccess();
		conn = dba.getConnection();
		System.out.println("ConnessioneStabilita");
		skills = new SkillsInformation();
		skills.setSkillId("abalonia");
		System.out.println(skills.getSkillId());
		skills.setCategoryId("1081");
		skills.setSkillName("novi");
		skills.setDescription("loolo");
		skills.setStatus("Approved");
	}
	
	public void testCreate() {
		try {
			SkillDAO.getFactory().create(conn, skills,st);
			System.out.println("Skill Creata");
			conn.rollback();
		}catch(Exception e) {
			System.out.println("Problema");
		}
	}
	
}