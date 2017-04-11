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

public class TestPostTAJobRequiredCGPA {

private ResourceManager rm;
	
private double wage;
private int maxHours;
private java.sql.Date deadline;
private String requiredSkills;
private String requiredCourseGPA;
private String requiredCourseCGPA;
private String requiredExperience;
private Course course;
private int minHours;

private boolean aIsLab;
private boolean approval;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"test.xml");
	}
	
	
	@Before
	public void setUp() throws Exception {
		rm = ResourceManager.getInstance();
		
		maxHours = 3;
		wage = 100;
		int year = 2017;
		int month = 11;
		int day = 19;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		 
		deadline = new java.sql.Date(calendar.getTimeInMillis());
		requiredSkills = "None";
		requiredCourseGPA = "3.00";
		
		requiredExperience = "None";
		course = new Course("ecse321", 1, 1, 1, 1);
		minHours = 45;
		
		aIsLab = true;
		approval = true;
	}

	
	@After
	public void tearDown() throws Exception {
		rm.delete();
		PersistenceXStream.saveToXMLwithXStream(rm);
	}
	
	
	@Test
	public void testRequiredCGPAInputs() {
		
		TamasController tc = new TamasController(rm);
		
		try {
			tc.postTAJob(maxHours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCourseCGPA, requiredExperience, course, minHours, aIsLab, approval);
			fail("No invalid input exception for null RequiredCGPA!");
			
		} catch (InvalidInputException e) {
			
			assertEquals("Required CGPA cannot be empty!", e.getMessage());
		}
		catch (NullPointerException e){
		
			assertEquals(new NullPointerException(), e);
		}
		
	}
	
	@Test
	 
	public void testPostTAJobInvalidCGPA(){
		
		TamasController tc = new TamasController(rm);
		String error = null;
		String requiredCourseCGPA = "3.##";
		
		try{
			tc.postTAJob(maxHours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCourseCGPA, requiredExperience, course, minHours, aIsLab, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required CGPA must be a decimal number!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	
	public void testPostTAJobInvalidCourseGPA2(){
		
		TamasController tc = new TamasController(rm);
		String error = null;
		String requiredCourseCGPA = "6.65";
		
		try{
			tc.postTAJob(maxHours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCourseCGPA, requiredExperience, course, minHours, aIsLab, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required CGPA cannot be greater than 4.00!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	
	public void testPostTAJobInvalidGPA3(){
		
		TamasController tc = new TamasController(rm);
		String error = null;
		String requiredCourseCGPA = "3320";
		
		
		try{
			tc.postTAJob(maxHours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCourseCGPA, requiredExperience, course, minHours, aIsLab, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();
			  
		
		assertEquals("Required CGPA's second character must be a decimal!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	}
	
	@Test
	
	public void testPostTAJobInvalidCGPA4(){
		
		TamasController tc = new TamasController(rm);
		String error = null;
		String requiredCourseCGPA = null;
		
		try{
			tc.postTAJob(maxHours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCourseCGPA, requiredExperience, course, minHours, aIsLab, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required CGPA cannot be empty!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
@Test
	
	public void testPostTAJobInvalidCGPA5(){
		
		TamasController tc = new TamasController(rm);
		String error = null;
		String requiredCourseCGPA = "12345";
		
		try{
			tc.postTAJob(maxHours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCourseCGPA, requiredExperience, course, minHours, aIsLab, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required CGPA must be 4 characters long!", error);	
		assertEquals(0, rm.getJobs().size());
	}
}



