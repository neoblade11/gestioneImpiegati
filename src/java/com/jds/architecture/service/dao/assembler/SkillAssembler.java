package com.jds.architecture.service.dao.assembler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;

import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.DAOConstants;

public class SkillAssembler {
	
	public static void getPreparedStatement(SkillsInformation info,PreparedStatement stmt) 
			throws SQLException{
		stmt.setString(1, info.getSkillId());
		stmt.setString(2, info.getCategoryId());
		stmt.setString(3, info.getSkillName());
		stmt.setString(4, info.getSkillDescription());
		stmt.setString(5, info.getStatus());
		
	}
	
	public static SkillsInformation getInfo(ResultSet rs) throws SQLException {
		SkillsInformation skillsReturn = new SkillsInformation();
		skillsReturn.setSkillId(rs.getString("id"));
		skillsReturn.setCategoryId(rs.getString("catId"));
		skillsReturn.setSkillName(rs.getString("name"));
		skillsReturn.setSkillDescription(rs.getString("description"));
		skillsReturn.setStatus(rs.getString("status"));
		return skillsReturn;
	}
	
	public static SkillsInformation getInfo(RowSet rs) throws SQLException {
		SkillsInformation skillsReturn = new SkillsInformation();
		skillsReturn.setSkillId(rs.getString("id"));
		skillsReturn.setCategoryId(rs.getString("catId"));
		skillsReturn.setSkillName(rs.getString("name"));
		skillsReturn.setSkillDescription(rs.getString("description"));
		skillsReturn.setStatus(rs.getString("status"));
		return skillsReturn;
	}
}
