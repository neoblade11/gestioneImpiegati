package com.jds.architecture.service.dao.assembler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import com.jds.apps.beans.ProjectRole;

public class ProjectRoleAssembler {

	public static void getPreparedStatement(ProjectRole role, PreparedStatement stmt) throws SQLException {
		stmt.setString(3, role.getRole());
		stmt.setDate(4, (Date) role.getRollinDate());
		stmt.setDate(5, (Date) role.getRolloffDate());
		stmt.setString(6, role.getTask());
	}
	
	public static ProjectRole getInfo(ResultSet rs) throws SQLException {
		ProjectRole pr = new ProjectRole();
		pr.setRole(rs.getString("role"));
		pr.setRollinDate(rs.getDate("rollinDate"));
		pr.setRolloffDate(rs.getDate("rollOffDate"));
		pr.setTask(rs.getString("taskDescrition"));
		return pr;
	}
	
	public static ProjectRole getInfo(RowSet rs) throws SQLException {
		ProjectRole pr = new ProjectRole();
		pr.setRole(rs.getString("role"));
		pr.setRollinDate(rs.getDate("rollinDate"));
		pr.setRolloffDate(rs.getDate("rollOffDate"));
		pr.setTask(rs.getString("taskDescrition"));
		return pr;
	}
}
