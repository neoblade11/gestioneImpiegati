package test.com.jds.architecture.service.dbaccess;

import java.io.IOException;

import com.jds.architecture.service.dao.DAOException;
import com.jds.architecture.service.dbaccess.DBAccessException;
import com.jds.architecture.service.idgenerator.IdGeneratorException;

import com.jds.architecture.service.idgenerator.EmployeeIdGenerator;

import junit.framework.TestCase;

public class EmployeeIdGeneratorTest extends TestCase {
	long idReturned = 0;
	
	public void testIdGenerator() {
			try {
				idReturned = EmployeeIdGenerator.getIstance().getNextId();
				System.out.println(idReturned);
			} catch (ClassNotFoundException | IOException | DAOException | DBAccessException | IdGeneratorException e) {
				fail("ERRORE NEL TEST");
				e.printStackTrace();
			}
}
	}
