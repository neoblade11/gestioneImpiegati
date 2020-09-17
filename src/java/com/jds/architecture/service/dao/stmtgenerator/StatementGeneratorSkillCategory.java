package com.jds.architecture.service.dao.stmtgenerator;

import org.apache.log4j.Logger;

import com.jds.apps.beans.SkillCategory;
import com.jds.architecture.service.dao.DAOConstants;
import com.jds.architecture.service.dao.assembler.SkillCategoryAssembler;

public class StatementGeneratorSkillCategory extends StatementGenerator {
	
	Logger log = Logger.getLogger(StatementGeneratorSkillCategory.class);

	@Override
	public String transformStmt(Object object, int stmtType) throws Exception {
		StringBuffer strBuffer = new StringBuffer();
		String strTemp = null;
		String strConstant =  DAOConstants.STR_AND;
		boolean isSet = false;
		SkillCategory obj = (SkillCategory)object;
		
		if (stmtType == DAOConstants.STMT_TYPE_SET) {
			strConstant = DAOConstants.CHAR_COMMA;
			isSet = true;
		}
		
		if (obj.getCategoryId() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_ID,
					obj.getCategoryId(),strConstant));
		}
		
		if (obj.getCategoryName() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_NAME,
					obj.getCategoryName(),strConstant));
		}
		
		if (obj.getCategoryDescription() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_DESCRIPTION,
					obj.getCategoryDescription(),strConstant));
		}
		
		if(obj.getStatus() != null) {
			strBuffer.append(getColumnValue(isSet, DAOConstants.COL_STATUS,
					obj.getStatus(),strConstant));
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
