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

public class TestRemoveCourse {

private ResourceManager rm;
	
	private Course aCourse;
	
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
	public void testaCourseInputs() {
		
		TamasController tc = new TamasController(rm);
		
		try {
			rm.removeCourse(aCourse);
			fail("No invalid input exception for null course!");
			
		} catch (NullPointerException e){
		
			assertEquals(new NullPointerException(), e);
		}
		
	}

}
