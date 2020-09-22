package com.jds.architecture.service.dao.stmtgenerator;

import org.apache.log4j.Logger;
import com.jds.apps.beans.EmployeeSkillProfile;
import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.assembler.EmpskillsAssembler;

public class StatementGenEmpSkills extends StatementGenerator{
	
	Logger log = Logger.getLogger(StatementGenEmpSkills.class);
	
	@Override
	public String transformStmt(Object object, int stmtType) throws Exception {
		StringBuffer strBuffer = new StringBuffer();
		String strTemp = null;
		String strConstant =  DAOConstants.STR_AND;
		boolean isSet = false;
		EmployeeSkillProfile obj = (EmployeeSkillProfile)object;
        
		if (stmtType == DAOConstants.STMT_TYPE_SET) {
			strConstant = DAOConstants.CHAR_COMMA;
			isSet = true;
			EmpskillsAssembler.toEmptyStringAllNull(obj);
		}
		
		if(obj.getEmployeeId() != null) {			
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_EMPNO, 
					obj.getEmployeeId(), strConstant));				
		} 
		if(obj.getSkillsInformation() != null && obj.getSkillsInformation().getId() !=null) {			
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_SKILLID, 
					obj.getSkillsInformation().getId(), strConstant));				
		} 
		if(obj.getProficiency() != 0) {			
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_PROFICIENCY, 
					""+obj.getProficiency()+"", strConstant));				
		}  	
		
		if(obj.getDetails() != null) {			
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_DETAILS, 
					obj.getDetails(), strConstant));				
		} 
		if(obj.getYrsOfExperience() != 0) {			
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_YRSOFEXP, 
					""+obj.getYrsOfExperience()+"", strConstant));				
		} 
		if(obj.getLevelOfInterest() != 0) {			
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_LEVELOFINT, 
					""+obj.getLevelOfInterest()+"", strConstant));				
		} 
		if(obj.getYearLastUsed() != 0) {			
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_YRLASTUSED, 
					""+obj.getYearLastUsed()+"", strConstant));				
		} 
		
		
		if ( strBuffer.toString().length() > 0) {
			if (stmtType == DAOConstants.STMT_TYPE_SET) { 
				strTemp = strBuffer.toString().substring(0,
				strBuffer.toString().length()-1);
			} else {
				strTemp = strBuffer.toString().substring(0, 
				strBuffer.toString().lastIndexOf(DAOConstants.STR_AND));
			}
		}

		log.debug("transform strTemp:" + strTemp);
		return strTemp;
		
	}

}