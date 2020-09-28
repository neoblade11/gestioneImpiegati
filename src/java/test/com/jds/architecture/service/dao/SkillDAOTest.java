
package test.com.jds.architecture.service.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.SkillDAO;
import com.jds.architecture.service.dao.assembler.SkillAssembler;
import com.jds.architecture.service.dao.stmtgenerator.StatementGenSkills;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.jds.architecture.service.dao.SkillDAO;

import junit.framework.TestCase;

public class SkillDAOTest extends TestCase {
	private static SkillAssembler sa;
	private static StatementGenSkills st;
	private static SkillsInformation skills;
	private static Connection conn;
	private static DBAccess dba;
	private static PreparedStatement stmt;
	private static CachedRowSet rowSet;
	
	@Override
	public void setUp() throws DAOException,ClassNotFoundException,IOException, DBAccessException{
		sa = new SkillAssembler();
		dba = DBAccess.getDBAccess();
		conn = dba.getConnection();
		skills = (SkillsInformation) new SkillsInformation();
		skills.setSkillId("a1a2");
		skills.setCategoryId("1081");
		skills.setSkillName("ggghhhjjj");
		skills.setSkillDescription("òlkjhgfdsaASDFGH");
		skills.setStatus("APPROVED");
	}

	
	public void testCreate () throws DBAccessException, DAOException, ClassNotFoundException, IOException{
		try {
			SkillDAO sd = new SkillDAO();
			System.out.println("SD creato");
			sd.create(conn, skills);
			conn.commit();
			System.out.println("test ok");
		}catch(SQLException sql) {
			throw new DAOException("sql.create.exception.skilldao", sql);
		}
	}
	
	

}