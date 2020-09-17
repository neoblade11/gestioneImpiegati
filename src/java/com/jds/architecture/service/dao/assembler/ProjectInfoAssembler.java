package com.jds.architecture.service.dao.assembler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import com.jds.apps.beans.ProjectInfo;

public class ProjectInfoAssembler {

	public static void getPreparedStatement(ProjectInfo info, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, info.getProjectId());
		stmt.setString(2, info.getProjectName());
		stmt.setString(3, info.getProjectDescrition());
		stmt.setDate(4, (Date) info.getStartDate());// chiedi
		stmt.setDate(5, (Date) info.getEndDate());
		stmt.setString(6, info.getClient());
		stmt.setString(7, info.getStatus());
	}
	
	//PER RESULTSET
	public static ProjectInfo getInfo(ResultSet rs) throws SQLException {
		ProjectInfo projectInfo = new ProjectInfo();
		
		projectInfo.setProjectId(rs.getString("id"));
		projectInfo.setProjectName(rs.getString("name"));
		projectInfo.setDescription(rs.getString("desription"));
		projectInfo.setStartDate(rs.getDate("startDate"));
		projectInfo.setEndDate(rs.getDate("endDate"));
		projectInfo.setClient(rs.getString("clientName"));
		projectInfo.setStatus(rs.getString("status"));
		
		return projectInfo;
	}
	
	//PER ROWSET
	public static ProjectInfo getInfo(RowSet rs) throws SQLException {
		ProjectInfo projectInfo = new ProjectInfo();
		
		projectInfo.setProjectId(rs.getString("id"));
		projectInfo.setProjectName(rs.getString("name"));
		projectInfo.setDescription(rs.getString("desription"));
		projectInfo.setStartDate(rs.getDate("startDate"));
		projectInfo.setEndDate(rs.getDate("endDate"));
		projectInfo.setClient(rs.getString("clientName"));
		projectInfo.setStatus(rs.getString("status"));
		
		return projectInfo;
	}
}
