package test.com.jds.architecture.service.dao.stmtgenerator;

import junit.framework.TestCase;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jds.apps.beans.SkillCategory;
import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dao.assembler.SkillCategoryAssembler;
import com.jds.architecture.service.dao.stmtgenerator.StatementGeneratorSkillCategory;
import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;

public class StatementGeneratorSkillCategoryTest extends TestCase {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private DBAccess dba;
	
	public void setUp() throws DAOException,ClassNotFoundException,IOException, DBAccessException {
		dba = DBAccess.getDBAccess();
		conn = dba.getConnection();
		System.out.println("ConnessioneStabilita");
	}
	
	public void testCreaGenSkillCategory() {
		SkillCategory skillCategory = new SkillCategory();
		skillCategory.setCategoryId("7hfhf");
		skillCategory.setCategoryName("plio");
		skillCategory.setDescription("clio");
		skillCategory.setStatus("Approved");
		StatementGeneratorSkillCategory sgs = new StatementGeneratorSkillCategory();
		try {
			stmt = conn.prepareStatement("INSERT INTO skillcategory(id,name,description,status) VALUES (?,?,?,?)");
			SkillCategoryAssembler.getPreparedStatement(skillCategory, stmt);
			rs = stmt.executeQuery();
			System.out.println("riuscito");
			rs.close();
			conn.rollback();
			conn.close();
		}catch(Exception e) {
			fail("Fallimento");
		}
	}
	
}
