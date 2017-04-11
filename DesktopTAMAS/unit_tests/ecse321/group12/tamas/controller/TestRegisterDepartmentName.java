package ecse321.group12.tamas.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class TestRegisterDepartmentName {

private ResourceManager rm;
	
	private java.sql.Date deadline;
	private String name;
	private String id;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"test.xml");
	}
	

	@Before
	public void setUp() throws Exception {
		rm = ResourceManager.getInstance();
		int year = 2017;
		int month = 11;
		int day = 19;
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(Calendar.YEAR, year);
		 calendar.set(Calendar.MONTH, month-1);
		 calendar.set(Calendar.DAY_OF_MONTH, day);
		 
		 deadline = new java.sql.Date(calendar.getTimeInMillis());
		 
		 id = "123456789";	
	}

	
	@After
	public void tearDown() throws Exception {
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	
	@Test
	public void testnameInputs() {
		
		TamasController tc = new TamasController(rm);
		name = null;
		
		try {
			tc.registerDepartment(name, id, deadline);
			fail("No invalid input exception for null name!");
			
		} catch (InvalidInputException e) {
			
			assertEquals("Department name cannot be empty!", e.getMessage());
		}
		catch (NullPointerException e){
		
			fail("No invalid input exception for null name!");
		}
		
	}

}
