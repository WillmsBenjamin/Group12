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

public class TestRegisterDepartmentId {

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
		 name = "None";
	}

	
	@After
	public void tearDown() throws Exception {
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	
@Test
	
	public void testidInputs() {
		
		TamasController tc = new TamasController(rm);
		id = null;
		
		try {
			tc.registerDepartment(name, id, deadline);
			fail("No invalid input exception for null id!");
			
		} catch (InvalidInputException e) {
			
			assertEquals("Department id cannot be empty!", e.getMessage());
		}
		catch (NullPointerException e){
		
			fail("No invalid input exception for null id!");
		}
		
	}
	
@Test 
	
	public void testRegisterDepartmentIDLength(){
		
		TamasController tc = new TamasController(rm); 
		id = "26052977";
		String error = null;
		
		try{
			tc.registerDepartment(name, id, deadline);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		assertEquals("Department id must be 9 numbers long!", error);	
	}

@Test 

	public void testRegisterDepartmentIDFormat(){
	
		TamasController tc = new TamasController(rm); 
		id = "2605yas79";
		String error = null;
		
		try{
			tc.registerDepartment(name, id, deadline);
		}
		catch (InvalidInputException e){
		  error = e.getMessage();			
		}	
		assertEquals("Department id must be all numbers!", error);	
	}


}
