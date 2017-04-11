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

public class TestRegisterInstructorId {

private ResourceManager rm;
	
	private String name;
	private String id;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"test.xml");
	}
	
	
	@Before
	public void setUp() throws Exception {
		name = "None";
		rm = ResourceManager.getInstance();
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
			tc.registerInstructor(name, id);
			fail("No invalid input exception for null id!");
			
		} catch (InvalidInputException e) {
			
			assertEquals("Instructor id cannot be empty!", e.getMessage());
		}
		catch (NullPointerException e){
		
			fail("No invalid input exception for null id!");
		}
		
	}
	
@Test 
	
	public void testRegisterInstructorIDLength(){
		
		TamasController tc = new TamasController(rm); 
		id = "26052977";
		String error = null;
		
		try{
			tc.registerInstructor(name, id);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		assertEquals("Instructor id must be 9 numbers long!", error);	
	}

@Test 

	public void testRegisterInstructorIDFormat(){
	
		TamasController tc = new TamasController(rm); 
		id = "2605yas79";
		String error = null;
		
		try{
			tc.registerInstructor(name, id);
		}
		catch (InvalidInputException e){
		  error = e.getMessage();			
		}	
		assertEquals("Instructor id must be all numbers!", error);	
	}


}
