package ecse321.group12.tamas.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ecse321.group12.tamas.model.Job;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class TestRemoveJob {

private ResourceManager rm;
	
	private Job aJob;
	
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
	public void testaJobInputs() {
		
		TamasController tc = new TamasController(rm);
		
		try {
			rm.removeJob(aJob);
			fail("No invalid input exception for null Job!");
			
		} catch (NullPointerException e){
		
			assertEquals(new NullPointerException(), e);
		}
		
	}

}
