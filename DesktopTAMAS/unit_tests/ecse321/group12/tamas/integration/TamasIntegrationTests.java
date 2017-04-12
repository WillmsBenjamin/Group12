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
import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.Instructor;
import ecse321.group12.tamas.model.Job;
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
	
	public void loginDepartment() {
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
	
	@Test

	public void testLoginInstructor() {
		loginInstructor();
	}
	
	private void loginInstructor() {
	TamasController tc = new TamasController(rm);
			String error = "";
	
			try {
				tc.registerInstructor("Joe", "123456789");
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertEquals("",  error);
			try {
				tc.logIn("123456789");
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertEquals("",  error);
	}
	
	@Test
	public void testLoginApplicant() {
		loginApplicant();
	}
	
	private void loginApplicant() {
	TamasController tc = new TamasController(rm);
			String error = "";
	
			try {
				tc.registerApplicant("Keven", "987654321", "4.00", "Many", true);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertEquals("",  error);
			try {
				tc.logIn("987654321");
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			assertEquals("",  error);
	}
	
	public void registerInstructor() {
		TamasController tc = new TamasController(rm);
		String error = "";
		
		Calendar c = Calendar.getInstance();
	    c.set(2019,Calendar.SEPTEMBER,15,8,30,0);
	   
	    
		try {
			tc.registerInstructor("Davis", "260529779");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("", error);
		try {
			tc.logIn("260529779");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("", error);
	}
	
	@Test
	public void registerStudent() {
		TamasController tc = new TamasController(rm);
		String error = "";
		
		Calendar c = Calendar.getInstance();
	    c.set(2019,Calendar.SEPTEMBER,15,8,30,0);
	   
	    
		try {
			tc.registerApplicant("Yasmin", "260529779", "3.34", "She is good", false);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("", error);
		try {
			tc.logIn("260529779");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("", error);
	}
	
	@Test
	public void applyForJob() {
		TamasController tc = new TamasController(rm);
		String error = "";
		
		Course dsd = new Course("ECSE323", 2, 2, 200, 1000);
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date date = new Date(c.getTimeInMillis());
	    Applicant yasmin = new Applicant ("Yasmin", "260529779", "3.40", "plc programming",false);
	    Job job = new Job (20, 200.0, date , true, "plc programming", "3.40", "3.20", "programming", dsd);
		try {
			tc.registerApplicant("Yasmin", "260529779", "3.34", "She is good", false);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("", error);
		
		try {
			tc.logIn("260529779");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		try {
			tc.applyToJob("I am good", "3.30", yasmin, job);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("", error);
	}
	
	
	@Test
	public void postGraderJob() {
		TamasController tc = new TamasController(rm);
		String error = "";
		
		Course dsd = new Course("ECSE323", 2, 2, 200, 1000);
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date date = new Date(c.getTimeInMillis());
	    //Instructor yasmin = new Instructor ("Yasmin", "260529779");
	    //Job job = new Job (20, 200.0, date , true, "plc programming", "3.40", "3.20", "programming", dsd);
		try {
			tc.registerInstructor("yasmin", "260529779");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("", error);
		
		try {
			tc.logIn("260529779");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		try {
			tc.postGraderJob(15, 50.0, date, "be good", "3.30", "3.00", "be good", dsd, true);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("", error);
	}
	
	
	@Test
	public void testCreateCourse() {
		loginDepartment();
	}
	
	private void addInstructortoCourse() {
		TamasController tc = new TamasController(rm);
		String error = "";
		Course dsd = new Course("ECSE323", 2, 2, 200, 1000);
		
		Calendar c = Calendar.getInstance();
	    c.set(2019,Calendar.SEPTEMBER,15,8,30,0);
	    Date date = new Date(c.getTimeInMillis());
	    Instructor yasmin = new Instructor ("Yasmin", "260529779");
	    //Job job = new Job (20, 200.0, date , true, "plc programming", "3.40", "3.20", "programming", dsd);
	    
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
		
		try {
			tc.createCourse("dsd", 20, 15, 200);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("", error);
		
		try {
			tc.addInstructorToCourse(yasmin, dsd);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("", error);
		
		try {
			tc.modifyInstructor("Yasmin", "260529779");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("", error);
		
		
		
	}
		
		
	}
	
	
	
	


