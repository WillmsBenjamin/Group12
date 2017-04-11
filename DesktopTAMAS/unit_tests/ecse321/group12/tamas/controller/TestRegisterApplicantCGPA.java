package ecse321.group12.tamas.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class TestRegisterApplicantCGPA {

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
		
		name = "None";
		id = "123456789";
		
		skills = "None";
		isGraduate = true;
	}

	
	@After
	public void tearDown() throws Exception {
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	
	@Test
	public void testcGPAInputs() {
		
		TamasController tc = new TamasController(rm);
		
		try {
			tc.registerApplicant(name, id, cGPA, skills, isGraduate);
			fail("No invalid input exception for null cGPA!");
			
		} catch (InvalidInputException e) {
			
			assertEquals("cGPA cannot be null!", e.getMessage());
			
		}
		catch (NullPointerException e){
		
			fail("No invalid input exception for null cGPA!");
		}
		
	}

}







