package ecse321.group12.tamas.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class TestRegisterApplicantId {

private ResourceManager rm;
	
	private String name;
	private String id;
	private String cGPA;
	private String skills;
	
	private boolean isGraduate;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"test.xml");
	}

	
	@Before
	public void setUp() throws Exception {
		rm = ResourceManager.getInstance();
		name = "None";
		
		cGPA = "3.00";
		skills = "None";
		isGraduate = true;
	}

	
	@After
	public void tearDown() throws Exception {
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	
	@Test
	public void testidInputs() {
		
		TamasController tc = new TamasController(rm);
		
		try {
			tc.registerApplicant(name, id, cGPA, skills, isGraduate);
			fail("No invalid input exception for null id!");
			
		} catch (InvalidInputException e) {
			
			assertEquals("Student id cannot be empty!", e.getMessage());
		}
		catch (NullPointerException e){
		
			fail("No invalid input exception for null id!");
		}
		
	}
	
	@Test 
	
	public void testRegisterApplicantIDLength(){
		
		TamasController tc = new TamasController(rm); 
		String id = "26052977";
		String error = null;
		
		try{
			tc.registerApplicant(name, id, cGPA, skills, isGraduate);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		assertEquals("Student id must be 9 numbers long!", error);	
	}

}







