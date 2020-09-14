/*
 * Created on Feb 9, 2005
 */
package com.jds.architecture.service.listener;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.jds.architecture.service.dbaccess.DBAccess;
import com.jds.architecture.service.dbaccess.DBAccessException;


/**
 * @author JDSSvr
 */
public class DBAccessDefaultTask extends TimerTask{
	private int connectMax = 0;
	private DBAccess dbaccess = null;
	private Logger log = Logger.getLogger(DBAccessDefaultTask.class);
	
	public DBAccessDefaultTask() {
		try {
			dbaccess = DBAccess.getDBAccess();
		} catch (DBAccessException e) {
			e.printStackTrace();
		}

	}
	
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	public void run()   {

		log.warn("#################trying to connect to database server.."+ connectMax);
		if (connectMax == 3) {
			log.error("Application is terminated due to database connection problem");
			System.exit(0);
		}
		
		connectMax++;
		
		try{
			dbaccess.connectDB();
		} catch (Exception e) {
			log.error("Unable to connect to dbaccess");
		}
		

		
	}
	
	
}
