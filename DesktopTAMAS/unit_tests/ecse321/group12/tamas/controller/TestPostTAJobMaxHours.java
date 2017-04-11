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

public class TestPostTAJobMaxHours {

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
		
		minHours = 3;
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
		requiredCourseCGPA = "3.00";
		requiredExperience = "None";
		course = new Course("ecse321", 1, 1, 1, 1);
		
		aIsLab = true;
		approval = true;
	}

	
	@After
	public void tearDown() throws Exception {
		rm.delete();
		PersistenceXStream.saveToXMLwithXStream(rm);
	}
	
	
	
	@Test
	
	public void testPostTAJobInvalidCGPA5(){
		
		TamasController tc = new TamasController(rm);
		String error = null;
		maxHours = 0;
		
		try{
			tc.postTAJob(maxHours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCourseCGPA, requiredExperience, course, minHours, aIsLab, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Maximum hours cannot be 0!", error);	
		assertEquals(0, rm.getJobs().size());
	}

}