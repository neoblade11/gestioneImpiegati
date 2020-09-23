package com.jds.architecture.service.dao.assembler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;
import com.jds.apps.beans.SkillCategory;

import com.jds.architecture.service.dao.DAOConstants;

public class SkillCategoryAssembler {

	public static void getPreparedStatement(SkillCategory info,PreparedStatement stmt) 
			throws SQLException{
		stmt.setString(1, info.getCategoryId());
		stmt.setString(2, info.getCategoryName());
		stmt.setString(3, info.getDescription());
		stmt.setNString(4, info.getStatus());
	}
	
	public static SkillCategory getInfo(ResultSet rs) throws SQLException {
		SkillCategory skillsCtReturn = new SkillCategory();
		skillsCtReturn.setCategoryId(rs.getString("id"));
		skillsCtReturn.setCategoryName(rs.getString("name"));
		skillsCtReturn.setDescription(rs.getString("description"));
		skillsCtReturn.setStatus(rs.getString("status"));
		return skillsCtReturn;
	}
	
	public static SkillCategory getInfo(RowSet rs) throws SQLException {
		SkillCategory skillsCtReturn = new SkillCategory();
		skillsCtReturn.setCategoryId(rs.getString("id"));
		skillsCtReturn.setCategoryName(rs.getString("name"));
		skillsCtReturn.setDescription(rs.getString("description"));
		skillsCtReturn.setStatus(rs.getString("status"));
		return skillsCtReturn;
	}
	
}
