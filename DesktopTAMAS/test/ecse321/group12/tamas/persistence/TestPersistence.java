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
	    
	    //create job
	    TAjob j = new TAjob(31, 31, date, "We don't care!", "3.00", "3.00", "who cares", comp250, 20, false);
	    
	    //create applications
	    Application app = new Application(false, "nothing", "3.10", a, j);
	    Application app2 = new Application(false, "nothing", "3.11", a2, j);

	    // register participants to event
	    Registration re = new Registration(pa, ev);
	    Registration re2 = new Registration(pa2, ev);

	    // manage registrations
	    rm.addRegistration(re);
	    rm.addRegistration(re2);
	    rm.addEvent(ev);
	    rm.addParticipant(pa);
	    rm.addParticipant(pa2);
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
	    if (!PersistenceXStream.saveToXMLwithXStream(rm))
	        fail("Could not save file.");

	    // clear the model in memory
	    rm.delete();
	    assertEquals(0, rm.getParticipants().size());
	    assertEquals(0, rm.getEvents().size());
	    assertEquals(0, rm.getRegistrations().size());

	    // load model
	    rm = (ResourceManager) PersistenceXStream.loadFromXMLwithXStream();
	    if (rm == null)
	        fail("Could not load file.");

	    // check participants
	    assertEquals(2, rm.getParticipants().size());
	    assertEquals("Martin", rm.getParticipant(0).getName());
	    assertEquals("Jennifer", rm.getParticipant(1).getName());
	    // check event
	    assertEquals(1, rm.getEvents().size());
	    assertEquals("Concert", rm.getEvent(0).getName());
	    Calendar c = Calendar.getInstance();
	    c.set(2015,Calendar.SEPTEMBER,15,8,30,0);
	    Date eventDate = new Date(c.getTimeInMillis());
	    Time startTime = new Time(c.getTimeInMillis());
	    c.set(2015,Calendar.SEPTEMBER,15,10,0,0);
	    Time endTime = new Time(c.getTimeInMillis());
	    assertEquals(eventDate.toString(), rm.getEvent(0).getEventDate().toString());
	    assertEquals(startTime.toString(), rm.getEvent(0).getStartTime().toString());
	    assertEquals(endTime.toString(), rm.getEvent(0).getEndTime().toString());
	    // check registrations
	    assertEquals(2, rm.getRegistrations().size());
	    assertEquals(rm.getEvent(0), rm.getRegistration(0).getEvent());
	    assertEquals(rm.getParticipant(0), rm.getRegistration(0).getParticipant());
	    assertEquals(rm.getEvent(0), rm.getRegistration(1).getEvent());
	    assertEquals(rm.getParticipant(1), rm.getRegistration(1).getParticipant());
	}

}
