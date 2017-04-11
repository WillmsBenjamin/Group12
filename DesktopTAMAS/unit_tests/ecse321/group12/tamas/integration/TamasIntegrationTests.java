package ecse321.group12.tamas.integration;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ecse321.group12.tamas.persistence.PersistenceXStream;
import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.Instructor;
import ecse321.group12.tamas.model.ResourceManager;

public class TamasIntegrationTests {

	private ResourceManager rm;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"integration.xml");
	}
	
	@Before
	public void setUp() throws Exception {
		rm = ResourceManager.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		rm.delete();
		PersistenceXStream.saveToXMLwithXStream(rm);
	}
	
	@Test
	public void testLoginDepartment() {
		loginDepartment();
	}
	
	private void loginDepartment() {
		TamasController tc = new TamasController(rm);
		String error = "";
		
		Calendar c = Calendar.getInstance();
	    c.set(2019,Calendar.SEPTEMBER,15,8,30,0);
	    Date date = new Date(c.getTimeInMillis());
	    
		try {
			tc.registerDepartment("ECSE", "000000000", date);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("", error);
		try {
			tc.logIn("000000000");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("", error);
	}

}
