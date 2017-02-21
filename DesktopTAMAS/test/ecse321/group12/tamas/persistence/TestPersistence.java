package ecse321.group12.tamas.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ecse321.group12.tamas.model.*;

public class TestPersistence {
	
	private ResourceManager rm;

	@Before
	public void setUp() throws Exception {
		rm = ResourceManager.getInstance();

	    // create participants
	    Applicant a = new Applicant("Roger", "260690005", "3.70", "I suck at everything!", false);
	    Applicant a2 = new Applicant("Jennifer", "231232145", "2.20", "I'm good at everything", true);

	    //create instructor
	    Instructor i = new Instructor("Donald Davis", "1337");
	    
	    // create Course
	    Calendar c = Calendar.getInstance();
	    c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
	    Date date = new Date(c.getTimeInMillis());
	    Time time = new Time(c.getTimeInMillis());
	    Course comp250 = new Course("Comp 250", 2, 2);
	    comp250.addContactTime(date, time, time);
	    comp250.addInstructor(i);
	    
	    //create jobs
	    TAjob j = new TAjob(31, 31, date, "We don't care!", "3.00", "3.00", "who cares", comp250, 20, false);
	    GraderJob j2 = new GraderJob(15, 15, date, "Nothing.", "2.50", "2.80", "nope", comp250);
	    
	    //create applications
	    Application app = new Application(false, "nothing", "3.10", a, j);
	    Application app2 = new Application(false, "nothing", "3.11", a2, j2);

	    //create department
	    Department d = new Department("ECSE", "01");
	    
	    // Assign applicants to jobs
	    Assignment assign = new Assignment("", a, j);
	    Assignment assign2 = new Assignment("", a2, j2);
	    app.setIsAccepted(true);
	    app2.setIsAccepted(true);

	    // manage everything
	    rm.addApplicant(a);
	    rm.addApplicant(a2);
	    rm.addInstructor(i);
	    rm.addCourse(comp250);
	    rm.addJob(j);
	    rm.addJob(j2);
	    rm.addApplication(app);
	    rm.addApplication(app2);
	    rm.setDepartment(d);
	    rm.addAssignment(assign);
	    rm.addAssignment(assign2);
	    rm.setLoggedIn(d);
	}

	@After
	public void tearDown() throws Exception {
		rm.delete();
	}

	@Test
	public void test() {
		// initialize model file
	    PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
	    // save model that is loaded during test setup
	    if (!PersistenceXStream.saveToXMLwithXStream(rm)) {
	        fail("Could not save file.");
	    }
	    
	    // clear the model in memory
	    rm.delete();
	    assertEquals(0, rm.getApplicants().size());
	    assertEquals(0, rm.getInstructors().size());
	    assertEquals(0, rm.getCourses().size());
	    assertEquals(0, rm.getJobs().size());
	    assertEquals(0, rm.getApplications().size());
	    assertEquals(null, rm.getDepartment());
	    assertEquals(0, rm.getAssignments().size());
	    assertEquals(null, rm.getLoggedIn());

	    // load model
	    rm = (ResourceManager) PersistenceXStream.loadFromXMLwithXStream();
	    if (rm == null) {
	        fail("Could not load file.");
	    }
	    
	    // check applicants
	    assertEquals(2, rm.getApplicants().size());
	    assertEquals("Roger", rm.getApplicant(0).getName());
	    assertEquals("Jennifer", rm.getApplicant(1).getName());
	    
	    // check instructor
	    assertEquals(1, rm.getInstructors().size());
	    assertEquals("Donald Davis", rm.getInstructor(0).getName());
	    assertEquals(1, rm.getInstructor(0).getCourses().size());
	    
	    //check course
	    assertEquals(1, rm.getCourses().size());
	    assertEquals("Comp 250", rm.getCourse(0).getName());
	    
	    Calendar c = Calendar.getInstance();
	    c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
	    Date date = new Date(c.getTimeInMillis());
	    Time time = new Time(c.getTimeInMillis());
	
	    assertEquals(date.toString(), rm.getCourse(0).getContactTime(0).getDate().toString());
	    assertEquals(time.toString(), rm.getCourse(0).getContactTime(0).getStartTime().toString());
	    assertEquals(time.toString(), rm.getCourse(0).getContactTime(0).getEndTime().toString());
	    
	    //check jobs
	    assertEquals(2, rm.getJobs().size());
	    assertEquals("3.00", rm.getJob(0).getRequiredCGPA());
	    assertEquals("2.80", rm.getJob(1).getRequiredCGPA());
	    
	    //check applications
	    assertEquals(2, rm.getApplications().size());
	    assertEquals("3.10", rm.getApplication(0).getCourseGPA());
	    assertEquals("3.11", rm.getApplication(1).getCourseGPA());
	    
	    //check department
	    assertEquals("ECSE", rm.getDepartment().getName());
	    
	    //check assignments
	    assertEquals(2, rm.getAssignments().size());
	    assertEquals(rm.getApplicant(0), rm.getAssignment(0).getApplicant());
	    assertEquals(rm.getApplicant(1), rm.getAssignment(1).getApplicant());
	    
	    //check loggedIn
	    assertEquals("ECSE", rm.getLoggedIn().getName());
	    
	}

}
