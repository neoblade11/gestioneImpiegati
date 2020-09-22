package com.jds.architecture.service.dao.assembler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.RowSet;
import com.jds.apps.beans.EmployeeSkillProfile;
import com.jds.apps.beans.SkillsInformation;
import com.jds.architecture.service.dao.DAOConstants;

public class EmpskillsAssembler {

	public static void getPreparedStatement(EmployeeSkillProfile employeeSkillProfile, PreparedStatement stmt)
			throws SQLException {
		stmt.setString(1, employeeSkillProfile.getEmployeeId());
		stmt.setString(2, employeeSkillProfile.getSkillsInformation().getId());
		stmt.setInt(3, employeeSkillProfile.getProficiency());
		stmt.setString(4, employeeSkillProfile.getDetails());
		stmt.setInt(5, employeeSkillProfile.getYrsOfExperience());
		stmt.setInt(6, employeeSkillProfile.getLevelOfInterest());
		stmt.setInt(7, employeeSkillProfile.getYearLastUsed());
	}

	public static EmployeeSkillProfile getInfo(ResultSet rs) throws SQLException {
		EmployeeSkillProfile employeeSkillProfile = new EmployeeSkillProfile();
		employeeSkillProfile.setEmployeeId(rs.getString("EMPNO"));
		SkillsInformation skillsInformation = new SkillsInformation();
		skillsInformation.setId(rs.getString("SKILLID"));
		employeeSkillProfile.setSkillsInformation(skillsInformation);
		employeeSkillProfile.setProficiency(rs.getInt("PROFICIENCY"));
		employeeSkillProfile.setDetails(rs.getString("DETAILS"));
		employeeSkillProfile.setYrsOfExperience(rs.getInt("YRSOFEXPERIENCE"));
		employeeSkillProfile.setLevelOfInterest(rs.getInt("LEVELOFINTEREST"));
		employeeSkillProfile.setYearLastUsed(rs.getInt("YEARLASTUSED"));
		return employeeSkillProfile;
	}

	public static EmployeeSkillProfile getInfo(RowSet rs) throws SQLException {
		EmployeeSkillProfile employeeSkillProfile = new EmployeeSkillProfile();
		employeeSkillProfile.setEmployeeId(rs.getString("EMPNO"));
		SkillsInformation skillsInformation = new SkillsInformation();
		skillsInformation.setId(rs.getString("SKILLID"));
		employeeSkillProfile.setSkillsInformation(skillsInformation);
		employeeSkillProfile.setProficiency(rs.getInt("PROFICIENCY"));
		employeeSkillProfile.setDetails(rs.getString("DETAILS"));
		employeeSkillProfile.setYrsOfExperience(rs.getInt("YRSOFEXPERIENCE"));
		employeeSkillProfile.setLevelOfInterest(rs.getInt("LEVELOFINTEREST"));
		employeeSkillProfile.setYearLastUsed(rs.getInt("YEARLASTUSED"));
		return employeeSkillProfile;
	}

	public static void toEmptyStringAllNull(EmployeeSkillProfile obj) {
		if (obj.getEmployeeId() == null)
			obj.setEmployeeId(DAOConstants.STR_SPACE);
		if (obj.getSkillsInformation() == null) {
			SkillsInformation skillsInformation = new SkillsInformation();
			skillsInformation.setId(DAOConstants.STR_SPACE);
		} else if (obj.getSkillsInformation().getId() == null)
			obj.getSkillsInformation().setId(DAOConstants.STR_SPACE);
		if (obj.getDetails() == null)
			obj.setDetails(DAOConstants.STR_SPACE);
	}
}
