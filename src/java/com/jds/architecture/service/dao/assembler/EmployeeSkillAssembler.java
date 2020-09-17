package com.jds.architecture.service.dao.assembler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSet;
import com.jds.apps.beans.EmployeeSkillProfile;
import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.DAOConstants;

public class EmployeeSkillAssembler {
	
	public static void getPreparedStatement(EmployeeSkillProfile info,SkillsInformation info2,PreparedStatement stmt) 
			throws SQLException{
		stmt.setString(1, info.getEmployeeId());
		stmt.setString(2,info2.getId());
		stmt.setObject(2, info.getSkillsInformation());
		stmt.setInt(3, info.getProficiency());
		stmt.setString(4, info.getDetails());
		stmt.setInt(5, info.getYrsOfExperience());
		stmt.setInt(6, info.getLevelOfInterest());
		stmt.setInt(7, info.getYearLastUsed());
	}
	
	public static EmployeeSkillProfile getInfo(ResultSet rs) throws SQLException {
		EmployeeSkillProfile empSkillReturn = new EmployeeSkillProfile();
		empSkillReturn.setEmployeeId(rs.getString("empNo"));
		//empSkillReturn.setSkillsInformation(rs.getString("skillId"));
		empSkillReturn.setProficiency(rs.getInt("proficiency"));
		empSkillReturn.setDetails(rs.getString("details"));
		empSkillReturn.setYrsOfExperience(rs.getInt("YRSOFEXPERIENCE"));
		empSkillReturn.setLevelOfInterest(rs.getInt("LEVELOFINTEREST"));
		empSkillReturn.setYearLastUsed(rs.getInt("YEARLASTUSED"));
		return empSkillReturn;
	}
	
	public static EmployeeSkillProfile getInfo(RowSet rs) throws SQLException {
		EmployeeSkillProfile empSkillReturn = new EmployeeSkillProfile();
		empSkillReturn.setEmployeeId(rs.getString("empNo"));
		//empSkillReturn.setSkillsInformation(rs.getObject("empno"));
		empSkillReturn.setProficiency(rs.getInt("proficiency"));
		empSkillReturn.setDetails(rs.getString("details"));
		empSkillReturn.setYrsOfExperience(rs.getInt("YRSOFEXPERIENCE"));
		empSkillReturn.setLevelOfInterest(rs.getInt("LEVELOFINTEREST"));
		empSkillReturn.setYearLastUsed(rs.getInt("YEARLASTUSED"));
		return empSkillReturn;
	}
	
	
}
