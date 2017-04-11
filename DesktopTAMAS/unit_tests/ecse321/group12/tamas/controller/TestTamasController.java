package ecse321.group12.tamas.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Calendar;
import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.Instructor;
import ecse321.group12.tamas.model.Job;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.model.User;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class TestTamasController {

	private static ResourceManager rm;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 rm = PersistenceXStream.initializeModelManager("output"+File.separator+"test_data.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	} 

	@Before
	public void setUp() throws Exception {
		rm = ResourceManager.getInstance();
	}

	@After
	public  void tearDown() throws Exception {
		PersistenceXStream.saveToXMLwithXStream(rm);
		rm.delete();
		
	}
	
	@Test 
	//adding a null instructor to a course 
	public void testAddNullInstructorToCourse(){
		
		TamasController tc = new TamasController(rm); 
		Course math = new Course("MATH363", 2, 2, 200, 1000);
		rm.addCourse(math);
		Instructor instructor = null;
		String error = null;
		
		for(Course c : rm.getCourses()) {
			if(c == math) {
				assertEquals(0, c.getInstructors().size());
			}
		}
		
		try{
			tc.addInstructorToCourse(instructor, math);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		assertEquals("An instructor must be selected!", error);	      
	    // check model in memory,
		for(Course c : rm.getCourses()) {
			if(c == math) {
				assertEquals(0, c.getInstructors().size());
			}
		}
	}
	
	@Test 
	// adding more than 4 instructors to a course 
	public void testAddMoreThanFourInstructorsToCourse(){
		
		TamasController tc = new TamasController(rm); 
		Course math = new Course("MATH363", 2, 2, 200, 1000);
		Instructor yasmin = new Instructor ("Yasmin Salehi","26051");
		Instructor keven = new Instructor ("Keven Yufei ","26052");
		Instructor ben = new Instructor ("Ben Willms","26053");
		Instructor matt = new Instructor ("Mathew Plouffe","26054");
		Instructor joe = new Instructor ("Joe Gibbs","26054");
		String error = null;
		rm.addInstructor(yasmin);
		rm.addInstructor(keven);
		rm.addInstructor(ben);
		rm.addInstructor(matt);
		rm.addInstructor(joe);
		rm.addCourse(math);

		try{
			tc.addInstructorToCourse(yasmin, math);
			tc.addInstructorToCourse(keven, math);
			tc.addInstructorToCourse(ben, math);
			tc.addInstructorToCourse(matt, math);
			tc.addInstructorToCourse(joe, math);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		assertEquals("This course has the maximum number of instructors (4)!", error);	      
	    // check model in memory,
		for(Course c : rm.getCourses()) {
			if(c == math) {
				assertEquals(4, c.getInstructors().size());
			}
		}
	}
	
	@Test 
	// adding an instructor to a null course 
	public void testAddInstructorToNullCourse(){
		
		TamasController tc = new TamasController(rm); 
		Instructor davis = new Instructor ("Donald Davis","2605");
		rm.addInstructor(davis);
		Course course = null;
		String error = null;
		
		for(Instructor i : rm.getInstructors()) {
			if(i == davis) {
				assertEquals(0, i.getCourses().size());
			}
		}
		try{
			tc.addInstructorToCourse(davis, course);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		assertEquals("A course must be selected!", error);	      
	    // check model in memory,
		for(Instructor i : rm.getInstructors()) {
			if(i == davis) {
				assertEquals(0, i.getCourses().size());
			}
		}		
	}
	
	@Test 
	//adding an instructor to a course twice  
	public void testRepeatAddingInstructorToACourse(){
		
		TamasController tc = new TamasController(rm); 
		Instructor davis = new Instructor ("Donald Davis","2605");
		Course math = new Course("MATH363", 2, 2, 200, 1000);
		rm.addInstructor(davis);
		String error = null;
	
		try{
			tc.addInstructorToCourse(davis, math);
			tc.addInstructorToCourse(davis, math);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		assertEquals("This instructor has already been added to this course!", error);	      
	    // check model in memory,
		for(Instructor i : rm.getInstructors()) {
			if(i == davis) {
				assertEquals(1, i.getCourses().size());
			}
		}		
	}
	
	@Test 
	//adding an instructor to a course   
	public void testAddingInstructorToACourse(){
		
		TamasController tc = new TamasController(rm); 
		Instructor davis = new Instructor ("Donald Davis","2605");
		Course math = new Course("MATH363", 2, 2, 200, 1000);
		rm.addInstructor(davis);
		//String error = null;	
		try{
			tc.addInstructorToCourse(davis, math);
		}
		catch (InvalidInputException e){
			  //error = e.getMessage();			
		}
		for(Instructor i : rm.getInstructors()) {
			if(i == davis) {
				assertEquals(1, i.getCourses().size());
			}
		}		
	}
	
/*	@Test
	//applying to job with null experience 
	public void testApplyToJobNullExperience(){
		TamasController tc = new TamasController(rm); 
		String experience = null;
		String courseGPA = "3.5";
		String error = null;
		Calendar c = Calendar.getInstance();
		c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		Date date = new Date(c.getTimeInMillis());
		Applicant yasmin = new Applicant ("Yasmin", "260529779", "3.34", "plc programming",false);
		Course dsd = new Course("ECSE323", 2, 2, 200, 1000);
		Job job = new Job (20, 200.0, date , true, "plc programming", "3.70", "3.2", "programming", dsd);

		try{
			tc.applyToJob(experience, courseGPA, yasmin, job);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		//undone talk to ben
		assertEquals("", error);	 	
	} */
	
	@Test
	//applying to job with null course GPA 
	public void testApplyToJobNullCourseGPA(){
		TamasController tc = new TamasController(rm); 
		String experience = "very experienced";
		String courseGPA = null;
		String error = null;
		Calendar c = Calendar.getInstance();
		c.set(2025,Calendar.SEPTEMBER,15,8,30,0);
		Date date = new Date(c.getTimeInMillis());
		Applicant yasmin = new Applicant ("Yasmin", "260529779", "3.34", "plc programming",false);
		Course dsd = new Course("ECSE323", 2, 2, 200, 1000);
		Job job = new Job (20, 200.0, date , true, "plc programming", "3.70", "3.20", "programming", dsd);
		
		rm.addApplicant(yasmin);
		rm.addCourse(dsd);
		rm.addJob(job);
		
		try{
			tc.applyToJob(experience, courseGPA, yasmin, job);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}

		assertEquals("Course GPA cannot be empty!", error);	 	
		//check the file 
		
		for(Applicant i : rm.getApplicants()) {
			if(i == yasmin) {
				assertEquals(0, i.getApplications().size());
			}
		}		
	}
	
	@Test
	//applying to job with a course GPA less than 4 characters
	public void testApplyToJobLessThanFourCharacterCourseGPA(){
		TamasController tc = new TamasController(rm); 
		String experience = "very experienced";
		String courseGPA = "3.3";
		String error = null;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date date = new Date(c.getTimeInMillis());
		Applicant yasmin = new Applicant ("Yasmin", "260529779", "3.40", "plc programming",false);
		Course dsd = new Course("ECSE323", 2, 2, 200, 1000);
		Job job = new Job (20, 200.0, date , true, "plc programming", "3.70", "3.20", "programming", dsd);

		rm.addApplicant(yasmin);
		rm.addCourse(dsd);
		rm.addJob(job);
		
		try{
			tc.applyToJob(experience, courseGPA, yasmin, job);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
	
		assertEquals("Course GPA must be 4 characters long!", error);	 	
		//check the file 
		
		for(Applicant i : rm.getApplicants()) {
			if(i == yasmin) {
				assertEquals(0, i.getApplications().size());
			}
		}		
	}
	
	@Test
	//applying to job with a course GPA more than 4 characters
	public void testApplyToJobMoreThanFourCharacterCourseGPA(){
		TamasController tc = new TamasController(rm); 
		String experience = "very experienced";
		String courseGPA = "3.454";
		String error = null;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date date = new Date(c.getTimeInMillis());
		Applicant yasmin = new Applicant ("Yasmin", "260529779", "3.44", "plc programming",false);
		Course dsd = new Course("ECSE323", 2, 2, 200, 1000);
		Job job = new Job (20, 200.0, date , true, "plc programming", "3.70", "3.20", "programming", dsd);

		rm.addApplicant(yasmin);
		rm.addCourse(dsd);
		rm.addJob(job);
		
		try{
			tc.applyToJob(experience, courseGPA, yasmin, job);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		//undone talk to ben
		assertEquals("Course GPA must be 4 characters long!", error);	 	
		//check the file 
		
		for(Applicant i : rm.getApplicants()) {
			if(i == yasmin) {
				assertEquals(0, i.getApplications().size());
			}
		}		
	}
	
	@Test
	//applying to job with a course GPA more than 4.00 
	public void testApplyToJobGreaterThanFourCourseGPA(){
		TamasController tc = new TamasController(rm); 
		String experience = "very experienced";
		String courseGPA = "5.34";
		String error = null;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date date = new Date(c.getTimeInMillis());
		
		Applicant yasmin = new Applicant ("Yasmin", "260529779", "3.40", "plc programming",false);
		Course dsd = new Course("ECSE323", 2, 2, 200, 1000);
		Job job = new Job (20, 200.0, date , true, "plc programming", "3.70", "3.20", "programming", dsd);
		
		rm.addApplicant(yasmin);
		rm.addCourse(dsd);
		rm.addJob(job);
		
		try{
			tc.applyToJob(experience, courseGPA, yasmin, job);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		assertEquals("Course GPA cannot be geater than 4.00!", error);	 	
		//check the file 
		
		for(Applicant i : rm.getApplicants()) {
			if(i == yasmin) {
				assertEquals(0, i.getApplications().size());
			}
		}		
	}
	
	@Test
	//applying to job with a course GPA that is not a decimal number
	public void testApplyToJobNotADecimalNumberCourseGPA(){
		TamasController tc = new TamasController(rm); 
		String experience = "very experienced";
		String courseGPA = "#.##";
		String error = null;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date date = new Date(c.getTimeInMillis());
		
		Applicant yasmin = new Applicant ("Yasmin", "260529779", "3.40", "plc programming",false);
		Course dsd = new Course("ECSE323", 2, 2, 200, 1000);
		Job job = new Job (20, 200.0, date , true, "plc programming", "3.70", "3.20", "programming", dsd);
		
		rm.addApplicant(yasmin);
		rm.addCourse(dsd);
		rm.addJob(job);
		
		try{
			tc.applyToJob(experience, courseGPA, yasmin, job);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		assertEquals("CourseGPA must be a decimal number!", error);	 	
		//check the file 
		for(Applicant i : rm.getApplicants()) {
			if(i == yasmin) {
				assertEquals(0, i.getApplications().size());
			}
		}		
	}
	
	@Test
	//applying to job with a course GPA with a null applicant
	public void testApplyToJobNullApplicant(){
		TamasController tc = new TamasController(rm); 
		String experience = "very experienced";
		String courseGPA = "3.50";
		String error = null;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date date = new Date(c.getTimeInMillis());
		
		Applicant yasmin = null;
		Course dsd = new Course("ECSE323", 2, 2, 200, 1000);
		Job job = new Job (20, 200.0, date , true, "plc programming", "3.40", "3.20", "programming", dsd);
		
		rm.addApplicant(yasmin);
		rm.addCourse(dsd);
		rm.addJob(job);
		
		try{
			tc.applyToJob(experience, courseGPA, yasmin, job);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		assertEquals("Applicant cannot be empty!", error);	 	
		//check the file 
		for(Job i : rm.getJobs()) {
			if(i == job) {
				assertEquals(0, i.getApplications().size());
			}
		}		
	}
	
	
	@Test
	//applying to job with a course GPA with a null job
	public void testApplyToJobNullJob(){
		TamasController tc = new TamasController(rm); 
		String experience = "very experienced";
		String courseGPA = "3.50";
		String error = null;
		
		Applicant yasmin = new Applicant ("Yasmin", "260529779", "3.40", "plc programming",false);
		Course dsd = new Course("ECSE323", 2, 2, 200, 1000);
		Job job = null;
		
		rm.addApplicant(yasmin);
		rm.addCourse(dsd);
		rm.addJob(job);
		
		try{
			tc.applyToJob(experience, courseGPA, yasmin, job);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		
		assertEquals("Job cannot be empty!", error);	 	
		//check the file 
		for(Applicant i : rm.getApplicants()) {
			if(i == yasmin) {
				assertEquals(0, i.getApplications().size());
			}
		}		
	}
	
	
	@Test
	// Creating a course with undefined name
	public void testCreateCourseNullName(){
		TamasController tc = new TamasController(rm); 
		String name = null;
		int numTuts = 12;
		int numLabs = 12;
		int numStuds = 200;
		String error = null;
		
		try{
			tc.createCourse(name, numTuts, numLabs, numStuds);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		assertEquals("Course name cannot be empty!", error);	
		//check the file 
		assertEquals(0, rm.getCourses().size());
	}
	
	
	@Test
	// Creating a course with undefined number of tutorials 
	public void testCreateCourseNullTutorials(){
		TamasController tc = new TamasController(rm); 
		String name = "dsd";
		int numTuts = 0;
		int numLabs = 0;
		int numStuds = 200;
		String error = null;
		
		try{
			tc.createCourse(name, numTuts, numLabs, numStuds);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		assertEquals("There must be at least one tutorial or lab section!", error);	
		//check the file 
		assertEquals(0, rm.getCourses().size());
	}
	
	
	@Test
	// Creating a course with undefined number of students
	public void testCreateCourseNullStudents(){
		TamasController tc = new TamasController(rm); 
		String name = "dsd";
		int numTuts = 10;
		int numLabs = 10;
		int numStuds = 0;
		String error = null;
		
		try{
			tc.createCourse(name, numTuts, numLabs, numStuds);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}
		assertEquals("There must be students enrolled in the course!", error);	
		//check the file 
		assertEquals(0, rm.getCourses().size());
	}
	
	@Test 
	//Testing logIN for a null ID
	public void testNullIDforLogIn(){
		TamasController tc = new TamasController(rm); 
		String id = null;
		String error = null;
		try{
			tc.logIn(id);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		assertEquals("Id cannot be empty!", error);	
	}
	
	@Test 
	//Testing logIN for an empty ID
	public void testEmptyIDforLogIn(){
		TamasController tc = new TamasController(rm); 
		String id = "";
		String error = null;
		try{
			tc.logIn(id);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		assertEquals("Id cannot be empty!", error);	
	}
	

	@Test 
	//Testing logIN for an ID longer than 9 digits
	public void tesLongIDforLogIn(){
		TamasController tc = new TamasController(rm); 
		String id = "2605297799";
		String error = null;
		try{
			tc.logIn(id);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		assertEquals("Id must be 9 numbers long!", error);	
	}
	
	@Test 
	//Testing logIN for an ID shorter than 9 digits
	public void testShortIDforLogIn(){
		TamasController tc = new TamasController(rm); 
		String id = "26052977";
		String error = null;
		try{
			tc.logIn(id);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		assertEquals("Id must be 9 numbers long!", error);	
	}
	
	@Test 
	//Testing logIN for an ID with words
	public void testNotAllNumberIDforLogIn(){
		TamasController tc = new TamasController(rm); 
		String id = "2605yas79";
		String error = null;
		try{
			tc.logIn(id);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		assertEquals("Id must be all numbers!", error);	
	}
	
	
	@Test
	//testing to see if the user can log out
	public void testLogOut(){
		TamasController tc = new TamasController(rm); 
		String name = "Yasmin";
		String aID = "260529779";
		User yasmin = new User( name,  aID);
		rm.setLoggedIn(yasmin);
		tc.logOut();
	}
	

	@Test
	//Testing for post grader job hours with null date 
	public void testPostGraderJobHoursNullHours(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Date deadline = null;
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.30";
		String requiredCGPA = "3.20";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Deadline cannot be empty!", error);	
		assertEquals(0, rm.getJobs().size());
		
	} 
	/*
	@Test
	//Testing for post grader job hours with a date before today
	public void testPostGraderJobHoursBadDate(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.30";
		String requiredCGPA = "3.20";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Deadline cannot be before today!", error);	
		assertEquals(0, rm.getJobs().size());
		
	} */
	
	@Test
	//Testing for post grader job hours with a date before today
	public void testPostGraderJobHoursBadDate(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = null;
		String requiredCGPA = "3.20";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required Course GPA cannot be empty!", error);	
		assertEquals(0, rm.getJobs().size());
		
	} 
	
	
	@Test
	//Testing for post grader job hours with a a long course GPA
	public void testPostGraderJobHoursLongCourseGPA(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.350";
		String requiredCGPA = "3.20";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required Course GPA must be 4 characters long!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	//Testing for post grader job hours without a . as the 2nd char.
	public void testPostGraderJobHoursInvalidCourseGPA(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3335";
		String requiredCGPA = "3.20";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required Course GPA's second character must be a decimal!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	//Testing for post grader job with an invalid date
	public void testPostGraderJobDeadlineBeforeToday(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2010,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.33";
		String requiredCGPA = "3.20";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Deadline cannot be before today!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	
	
	@Test
	//Testing for post grader job hours with invalid characters 
	public void testPostGraderJobHoursInvalid2CourseGPA(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.##";
		String requiredCGPA = "3.20";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required Course GPA must be a decimal number!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	//Testing for post grader job hours with a course GPA greater than 4
	public void testPostGraderJobHoursInvalid3CourseGPA(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "6.65";
		String requiredCGPA = "3.20";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required Course GPA cannot be geater than 4.00!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	//Testing for post grader job hours with a CGPA's2nd char not being a decimal number
	public void testPostGraderJobHoursInvalid1CGPA(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.30";
		String requiredCGPA = "3320";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required CGPA's second character must be a decimal!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	//Testing for post grader job hours with a CGPA not being a decimal number 
	public void testPostGraderJobHoursInvalid2GPA(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.30";
		String requiredCGPA = "#.30";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required CGPA must be a decimal number!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	//Testing for post grader job hours with a course GPA greater than 4
	public void testPostGraderJobHoursInvalid3CGPA(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.30";
		String requiredCGPA = "4.30";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required CGPA cannot be geater than 4.00!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	//Testing for post grader job hours with a course GPA greater than 4
	public void testPostGraderJobHoursInvalid4CGPA(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 50;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.30";
		String requiredCGPA = null;
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Required CGPA cannot be empty!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	//Testing for post grader job hours with a course GPA greater than 4
	public void testPostGraderJobHoursInvalidHours(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 0;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.30";
		String requiredCGPA = "3.30";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Hours cannot be 0!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	//Testing for post grader job hours with a course GPA greater than 4
	public void testPostGraderJobHoursInvalidCourse(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 40;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.30";
		String requiredCGPA = "3.30";
		String requiredExpereince = "Have been a grader once";
		Course course = null;
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("Course cannot be empty!", error);	
		assertEquals(0, rm.getJobs().size());
	}
	
	@Test
	//Testing for post grader job hours with a course GPA greater than 4
	public void testPostGraderJobExceedsCourseBudget(){
		TamasController tc = new TamasController(rm);
		String error = null;
		int hours = 40;
		double wage = 40;
		Calendar c = Calendar.getInstance();
		c.set(2020,Calendar.SEPTEMBER,15,8,30,0);
		Date deadline = new Date(c.getTimeInMillis());
		String requiredSkills = "be good";
		String requiredCourseGPA = "3.30";
		String requiredCGPA = "3.30";
		String requiredExpereince = "Have been a grader once";
		Course course = new Course("ECSE323", 2, 2, 200, 1000);
		boolean approval = true;
		
		try{
			tc.postGraderJob(hours, wage, deadline, requiredSkills, requiredCourseGPA, requiredCGPA, requiredExpereince, course, approval);
		}
		catch (InvalidInputException e){
			  error = e.getMessage();			
		}	
		
		assertEquals("This posting would put the course over budget!", error);	
		assertEquals(0, rm.getJobs().size());
	}
		
}
