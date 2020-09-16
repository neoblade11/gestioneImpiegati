/*
 * Created on Feb 9, 2005
 */
package com.jds.architecture.service.idgenerator;

import java.io.IOException;

import com.jds.architecture.service.dao.DAOException;


/**
 * <p>
 * IdGeneratorInterface is an interface of IdGenerator classes.
 * </p> 
 * 
 *  							
 * @author c.b.balajadia 
 * @version  02/2004 initial draft c.b.balajadia
  * @since 1.1
 */
public interface IdGeneratorInterface {

	public long getNextId() throws IdGeneratorException,ClassNotFoundException,IOException,DAOException;
		
}
