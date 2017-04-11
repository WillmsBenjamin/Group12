package ecse321.group12.tamas.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Assignment;
import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class TestRemoveAssignment {

private ResourceManager rm;
	
	private Assignment aAssignment;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"test.xml");
	}

	
	@Before
	public void setUp() throws Exception {
	}

	
	@After
	public void tearDown() throws Exception {
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	
	@Test
	public void testaAssignmentInputs() {
		
		TamasController tc = new TamasController(rm);
		
		try {
			rm.removeAssignment(aAssignment);
			fail("No invalid input exception for null assignment!");
			
		} catch (NullPointerException e){
		
			assertEquals(new NullPointerException(), e);
		}
		
	}

}
