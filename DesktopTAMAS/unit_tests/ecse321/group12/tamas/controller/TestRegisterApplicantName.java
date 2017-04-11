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

public class TestRegisterApplicantName {

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
		
		id = "123456789";
		cGPA = "3.00";
		skills = "None";
		isGraduate = true;
	}

	
	@After
	public void tearDown() throws Exception {
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	
	@Test
	public void testnameInputs() {
		
		TamasController tc = new TamasController(rm);
		
		try {
			tc.registerApplicant(name, id, cGPA, skills, isGraduate);
			fail("No invalid input exception for null name!");
			
		} catch (InvalidInputException e) {
			
			assertEquals("Name cannot be null!", e.getMessage());
		}
		catch (NullPointerException e){
		
			fail("No invalid input exception for null name!");
		}
		
	}

}







