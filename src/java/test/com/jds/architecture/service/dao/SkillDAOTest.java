
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

	public void testsetUp() throws DAOException,ClassNotFoundException,IOException, DBAccessException{
		
		sa = new SkillAssembler();
		
	}

	public void testCreate () throws DBAccessException, DAOException{
		try {
			dba = DBAccess.getDBAccess();
			conn = dba.getConnection();
			skills = new SkillsInformation();
			skills.setSkillId("a1a2");
			skills.setCategoryId("1081");
			skills.setSkillName("ggghhhjjj");
			skills.setSkillDescription("òlkjhgfdsaASDFGH");
			skills.setStatus("APPROVED");
			SkillDAO sd = new SkillDAO();
			System.out.println("SD creato");
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
			rowSet.updateString(1, skills.getSkillId());
			rowSet.updateString(2, skills.getCategoryId());
			rowSet.updateString(3, skills.getSkillName());
			rowSet.updateString(4, skills.getDescription());
			rowSet.updateString(5, skills.getStatus());
			//SkillAssembler.getInfo(rowSet);
			sd.create(conn, SkillAssembler.getInfo(rowSet));
			conn.commit();
			System.out.println("test okk");
		}catch(SQLException sql) {
			System.out.println("ErroreSQL");
		}
	}

}