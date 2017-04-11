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
		id = "123456789";
		
		skills = "None";
		isGraduate = true;
	}

	
	@After
	public void tearDown() throws Exception {
		rm.delete();
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	
	
	@Test
	 
	public void testRegisterApplicantCGPADecimal(){
		
		TamasController tc = new TamasController(rm);
		String error = null;
		String cGPA = "3.##";
		
		try{
			tc.registerApplicant(name, id, cGPA, skills, isGraduate);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Student CGPA must be a decimal number!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	
	public void testRegisterApplicantCGPAMax(){
		
		TamasController tc = new TamasController(rm);
		String error = null;
		String cGPA = "6.65";
		
		try{
			tc.registerApplicant(name, id, cGPA, skills, isGraduate);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		assertEquals("Student CGPA cannot be geater than 4.00!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	
	public void testRegisterApplicantCGPAFormat(){
		
		TamasController tc = new TamasController(rm);
		String error = null;
		String cGPA = "3320";
		
		
		try{
			tc.registerApplicant(name, id, cGPA, skills, isGraduate);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		assertEquals("Student CGPA's second character must be a decimal!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	
	@Test
	
	public void testRegisterApplicantCGPANull(){
		
		TamasController tc = new TamasController(rm);
		String error = null;
		String cGPA = null;
		
		try{
			tc.registerApplicant(name, id, cGPA, skills, isGraduate);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		assertEquals("Student CGPA cannot be empty!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
@Test
	
	public void testPostTAJobInvalidCGPALength(){
		
		TamasController tc = new TamasController(rm);
		String error = null;
		String cGPA = "12345";
		
		try{
			tc.registerApplicant(name, id, cGPA, skills, isGraduate);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		assertEquals("Student CGPA must be 4 characters long!", error);	
		assertEquals(0, rm.getJobs().size());
	}
}
