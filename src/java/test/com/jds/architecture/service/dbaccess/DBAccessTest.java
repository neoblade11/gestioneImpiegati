package test.com.jds.architecture.service.dbaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.jds.architecture.service.dbaccess.DBAccess;
import junit.framework.TestCase;

public class DBAccessTest extends TestCase {
	DBAccess dbAccess;
	Connection conn;

	public void testConnection() {
		try {
			dbAccess = DBAccess.getDBAccess();
		} catch (Exception e) {
			fail("Error get Instance");
		}
		
		try {
			conn = dbAccess.getConnection();
		} catch (Exception e) {
			fail("Error GetConnection");

		}
		try {
			Statement stmt = conn.createStatement();
			String sql = "create table prova(prova int)";
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {

		}
		
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into prova values(1)";
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			fail("Error Insert");
		}
		
		try{
			dbAccess.rollbackConnection(conn);
		}catch (Exception e) {
			fail("Error Rollback");
		}
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into prova values(2)";
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			fail("Error Insert");
		}
		
		try{
			dbAccess.commitConnection(conn);
		}catch (Exception e) {
			fail("Error commit");
		}
		
		try{
			Statement stmt = conn.createStatement();
			String sql = " drop table prova";
			stmt.executeUpdate(sql);
		}catch (Exception e) {

		}
		
		try {
			dbAccess.closeConnection(conn);
		} catch (Exception e) {
			fail("Error CloseConnection");
		}

	}

}
