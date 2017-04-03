package ecse321.group12.tamas.controller;

import ecse321.group12.tamas.controller.InvalidInputException;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ecse321.group12.tamas.controller.DepartmentRegisteredException;
import ecse321.group12.tamas.model.*;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class TamasController {
	private ResourceManager rm;

	public TamasController(ResourceManager rm) {
		this.rm = rm;
	}

	public void logIn(String id) throws InvalidInputException {
		if (id == null || id.trim().length() == 0) {
			throw new InvalidInputException("Id cannot be empty!");
		}
		if(id.length() != 9) {
			throw new InvalidInputException("Id must be 9 numbers long!");
		} else {
			for(int i = 0; i < 9; i++) {
				if(id.charAt(i) < 48 || id.charAt(i) > 57) {
					throw new InvalidInputException("Id must be all numbers!");
				}
			}
		}
		for(Applicant a : rm.getApplicants()) {
			if(a.getId().equals(id)) {
				rm.setLoggedIn(a);
				PersistenceXStream.saveToXMLwithXStream(rm);
				return;
			}
		}
		for(Instructor i : rm.getInstructors()) {
			if(i.getId().equals(id)) {
				rm.setLoggedIn(i);
				PersistenceXStream.saveToXMLwithXStream(rm);
				return;
			}
		}
		if(rm.getDepartment() != null) {
			if(rm.getDepartment().getId().equals(id)) {
				rm.setLoggedIn(rm.getDepartment());
				PersistenceXStream.saveToXMLwithXStream(rm);
				return;
			}
		}
		throw new InvalidInputException("Id doesn't match a registered user!");
	}

	public void logOut() {
		rm.setLoggedIn(null);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}
	
	public void registerApplicant(String name, String id, String cGPA, String skills, Boolean isGraduate) throws InvalidInputException {
		if (name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Student name cannot be empty!");
		}
		if (id == null || id.trim().length() == 0) {
			throw new InvalidInputException("Student id cannot be empty!");
		}
		if(id.length() != 9) {
			throw new InvalidInputException("Student id must be 9 numbers long!");
		} else {
			for(int i = 0; i < 9; i++) {
				if(id.charAt(i) < 48 || id.charAt(i) > 57) {
					throw new InvalidInputException("Student id must be all numbers!");
				}
			}
		}
		for(Applicant a : rm.getApplicants()) {
			if(a.getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		for(Instructor i : rm.getInstructors()) {
			if(i.getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		if(rm.getDepartment() != null) {
			if(rm.getDepartment().getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		if (cGPA == null || cGPA.trim().length() == 0) {
			throw new InvalidInputException("Student CGPA cannot be empty!");
		}
		if(cGPA.length() != 4) {
			throw new InvalidInputException("Student CGPA must be 4 characters long!");
		} else {
			for(int i = 0; i < 4; i++) {
				if(i == 1 && cGPA.charAt(i) != '.') {
					throw new InvalidInputException("Student CGPA's second character must be a decimal!");
				} else if((i != 1) && (cGPA.charAt(i) < 48 || cGPA.charAt(i) > 57)) {
					throw new InvalidInputException("Student CGPA must be a decimal number!");
				} else if(i == 0 && cGPA.charAt(i) > 52) {
					throw new InvalidInputException("Student CGPA cannot be geater than 4.00!");
				} else if((i==2 || i==3) && (cGPA.charAt(0) == '4') && (cGPA.charAt(i) > 48)) {
					throw new InvalidInputException("Student CGPA cannot be geater than 4.00!");
				}
			}
		}
		Applicant a = new Applicant(name, id, cGPA, skills, isGraduate);
		rm.addApplicant(a);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void modifyApplicant(String name, String id, String cGPA, String skills, Boolean isGraduate) throws InvalidInputException 
	{
		if (name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Student name cannot be empty!");
		}
		if (id == null || id.trim().length() == 0) {
			throw new InvalidInputException("Student id cannot be empty!");
		}
		if(id.length() != 9) {
			throw new InvalidInputException("Student id must be 9 numbers long!");
		} else {
			for(int i = 0; i < 9; i++) {
				if(id.charAt(i) < 48 || id.charAt(i) > 57) {
					throw new InvalidInputException("Student id must be all numbers!");
				}
			}
		}
		for(Applicant a : rm.getApplicants()) {
			if(a.getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		for(Instructor i : rm.getInstructors()) {
			if(i.getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		if(rm.getDepartment() != null) {
			if(rm.getDepartment().getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		if (cGPA == null || cGPA.trim().length() == 0) {
			throw new InvalidInputException("Student CGPA cannot be empty!");
		}
		if(cGPA.length() != 4) {
			throw new InvalidInputException("Student CGPA must be 4 characters long!");
		} else {
			for(int i = 0; i < 4; i++) {
				if(i == 1 && cGPA.charAt(i) != '.') {
					throw new InvalidInputException("Student CGPA's second character must be a decimal!");
				} else if((i != 1) && (cGPA.charAt(i) < 48 || cGPA.charAt(i) > 57)) {
					throw new InvalidInputException("Student CGPA must be a decimal number!");
				} else if(i == 0 && cGPA.charAt(i) > 52) {
					throw new InvalidInputException("Student CGPA cannot be geater than 4.00!");
				} else if((i==2 || i==3) && (cGPA.charAt(0) == '4') && (cGPA.charAt(i) > 48)) {
					throw new InvalidInputException("Student CGPA cannot be geater than 4.00!");
				}
			}
		}
		Applicant A = (Applicant)rm.getLoggedIn();
		A.setName(name);
		A.setId(id);
		A.setSkills(skills);
		A.setIsGraduate(isGraduate);
		
		PersistenceXStream.saveToXMLwithXStream(rm);
	}
	public void acceptJobOffer(Application a) throws InvalidInputException {
		if (!a.getIsOffered()) {
			throw new InvalidInputException("This application was not offered a job!");
		}
		if (a.getIsAccepted()) {
			throw new InvalidInputException("This offer has already been accepted! ");
		}
		int hours=0;
		for (Application app: a.getApplicant().getApplications()) {
			Job j = app.getJob();
			hours += j.getMaxHours();
		}
		if (hours>180) {
			throw new InvalidInputException("Accepting this TA job puts you over the 180 hour maximum for a single TA!");
		} else {
			a.setIsAccepted(true);
			PersistenceXStream.saveToXMLwithXStream(rm);
		}
	}

	public void checkDepartmentExistence() throws DepartmentRegisteredException {
		if(rm.getDepartment() instanceof Department) {
			throw new DepartmentRegisteredException("A department exists. No more can be added!");
		}
	}
	
	public void assignApplicantToJob(Application a) throws InvalidInputException {
		if (!a.getIsAccepted()) {
			throw new InvalidInputException("This job offer has not been accepted!");
		} else if(!a.getIsOffered()) {
			throw new InvalidInputException("This application has not been offered the job!");
		} else {
			Assignment asmt = new Assignment("", a.getApplicant(), a.getJob());
			rm.addAssignment(asmt);
			//once a job has been assigned, remove all other applications to said job
			for (int i = a.getJob().getApplications().size()-1; i >=0; i--) {
				Application app = a.getJob().getApplication(i);
				if (app != a) {
					app.delete();
				}
			}
		}
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void registerDepartment(String name, String id, Date deadline) throws InvalidInputException {
		if (name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Department name cannot be empty!");
		}
		if (id == null || id.trim().length() == 0) {
			throw new InvalidInputException("Department id cannot be empty!");
		}
		if(id.length() != 9) {
			throw new InvalidInputException("Department id must be 9 numbers long!");
		} else {
			for(int i = 0; i < 9; i++) {
				if(id.charAt(i) < 48 || id.charAt(i) > 57) {
					throw new InvalidInputException("Department id must be all numbers!");
				}
			}
		}
		for(Applicant a : rm.getApplicants()) {
			if(a.getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		for(Instructor i : rm.getInstructors()) {
			if(i.getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		if(rm.getDepartment() != null) {
			if(rm.getDepartment().getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		Calendar calobj = Calendar.getInstance();
		if (deadline == null) {
			throw new InvalidInputException("Deadline cannot be empty!");
		}
		if (deadline.before(calobj.getTime())) {
			throw new InvalidInputException("Deadline cannot be before today!");
		}
		Department d = new Department(name, id, deadline);
		rm.setDepartment(d);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void modifyDepartment(String name, String id, Date deadline) throws InvalidInputException 
	{
		//since this is the admin, they can change whatever they want!
		if (name == null || name.trim().length() == 0) 
		{
			throw new InvalidInputException("Department name cannot be empty!");
		}
		if (id == null || id.trim().length() == 0) 
		{
			throw new InvalidInputException("Department id cannot be empty!");
		}
		if(id.length() != 9) 
		{
			throw new InvalidInputException("Department id must be 9 numbers long!");
		} 
		else 
		{
			for(int i = 0; i < 9; i++) 
			{
				if(id.charAt(i) < 48 || id.charAt(i) > 57) {
					throw new InvalidInputException("Department id must be all numbers!");
				}
			}
		}
		for(Applicant a : rm.getApplicants()) 
		{
			if(a.getId().equals(id)) 
			{
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		for(Instructor i : rm.getInstructors()) 
		{
			if(i.getId().equals(id)) 
			{
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		if(rm.getDepartment() != null) 
		{
			if(rm.getDepartment().getId().equals(id)) 
			{
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		Calendar calobj = Calendar.getInstance();
		if (deadline == null) {
			throw new InvalidInputException("Deadline cannot be empty!");
		}
		if (deadline.before(calobj.getTime())) {
			throw new InvalidInputException("Deadline cannot be before today!");
		}
		Department D=(Department)rm.getLoggedIn();
		D.setName(name);
		D.setId(id);
		D.setDeadline(deadline);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void registerInstructor(String name, String id) throws InvalidInputException {
		if (name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Instructor name cannot be empty!");
		}
		if (id == null || id.trim().length() == 0) {
			throw new InvalidInputException("Instructor id cannot be empty!");
		}
		if(id.length() != 9) {
			throw new InvalidInputException("Instructor id must be 9 numbers long!");
		} else {
			for(int i = 0; i < 9; i++) {
				if(id.charAt(i) < 48 || id.charAt(i) > 57) {
					throw new InvalidInputException("Instructor id must be all numbers!");
				}
			}
		}
		for(Applicant a : rm.getApplicants()) {
			if(a.getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		for(Instructor i : rm.getInstructors()) {
			if(i.getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		if(rm.getDepartment() != null) {
			if(rm.getDepartment().getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		Instructor d = new Instructor(name, id);
		rm.addInstructor(d);
		PersistenceXStream.saveToXMLwithXStream(rm);
		
	}

	public void modifyInstructor(String name, String id) throws InvalidInputException {
		if (name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Instructor name cannot be empty!");
		}
		if (id == null || id.trim().length() == 0) {
			throw new InvalidInputException("Instructor id cannot be empty!");
		}
		if(id.length() != 9) {
			throw new InvalidInputException("Instructor id must be 9 numbers long!");
		} else {
			for(int i = 0; i < 9; i++) {
				if(id.charAt(i) < 48 || id.charAt(i) > 57) {
					throw new InvalidInputException("Instructor id must be all numbers!");
				}
			}
		}
		for(Applicant a : rm.getApplicants()) {
			if(a.getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		for(Instructor i : rm.getInstructors()) {
			if(i.getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		if(rm.getDepartment() != null) {
			if(rm.getDepartment().getId().equals(id)) {
				throw new InvalidInputException("That ID is already being used!");
			}
		}
		Instructor I = (Instructor) rm.getLoggedIn();
		I.setName(name);
		I.setId(id);
		PersistenceXStream.saveToXMLwithXStream(rm);
		
	}
	
	public void postTAJob(int aMaxHours, double aWage, Date aDeadline, String aRequiredSkills, String aRequiredCourseGPA,
			String aRequiredCGPA, String aRequiredExperience, Course aCourse, int aMinHours, boolean aIsLab, boolean approval) throws InvalidInputException {
		Calendar calobj = Calendar.getInstance();
	    
		if (aDeadline == null) {
			throw new InvalidInputException("Deadline cannot be empty!");
		}
		if (aDeadline.before(calobj.getTime())) {
			throw new InvalidInputException("Deadline cannot be before today!");
		}
		if (aRequiredCourseGPA == null || aRequiredCourseGPA.trim().length() == 0) {
			throw new InvalidInputException("Required Course GPA cannot be empty!");
		}
		if(aRequiredCourseGPA.length() != 4) {
			throw new InvalidInputException("Required Course GPA must be 4 characters long!");
		} else {
			for(int i = 0; i < 4; i++) {
				if(i == 1 && aRequiredCourseGPA.charAt(i) != '.') {
					throw new InvalidInputException("Required Course GPA's second character must be a decimal!");
				} else if((i != 1) && (aRequiredCourseGPA.charAt(i) < 48 || aRequiredCourseGPA.charAt(i) > 57)) {
					throw new InvalidInputException("Required Course GPA must be a decimal number!");
				} else if(i == 0 && aRequiredCourseGPA.charAt(i) > 52) {
					throw new InvalidInputException("Required Course GPA cannot be geater than 4.00!");
				} else if((i==2 || i==3) && (aRequiredCourseGPA.charAt(0) == '4') && (aRequiredCourseGPA.charAt(i) > 48)) {
					throw new InvalidInputException("Required Course GPA cannot be geater than 4.00!");
				}
			}
		}
		if (aRequiredCGPA == null || aRequiredCGPA.trim().length() == 0) {
			throw new InvalidInputException("Required CGPA cannot be empty!");
		}
		if(aRequiredCGPA.length() != 4) {
			throw new InvalidInputException("Required CGPA must be 4 characters long!");
		} else {
			for(int i = 0; i < 4; i++) {
				if(i == 1 && aRequiredCGPA.charAt(i) != '.') {
					throw new InvalidInputException("Required CGPA's second character must be a decimal!");
				} else if((i != 1) && (aRequiredCGPA.charAt(i) < 48 || aRequiredCGPA.charAt(i) > 57)) {
					throw new InvalidInputException("Required CGPA must be a decimal number!");
				} else if(i == 0 && aRequiredCGPA.charAt(i) > 52) {
					throw new InvalidInputException("Required CGPA cannot be geater than 4.00!");
				} else if((i==2 || i==3) && (aRequiredCGPA.charAt(0) == '4') && (aRequiredCGPA.charAt(i) > 48)) {
					throw new InvalidInputException("Required CGPA cannot be geater than 4.00!");
				}
			}
		}
		if (aCourse == null) {
			throw new InvalidInputException("Course cannot be empty!");
		} else {
			int numTAJobs = 0;
			int numLabJobs = 0;
			for(Job j : aCourse.getJobs()) {
				if(j instanceof TAjob) {
					numTAJobs++;
					if(((TAjob)j).getIsLab()) {
						numLabJobs++;
					}
				}
			}
			if(numTAJobs == aCourse.getNumTutorialSections() && !aIsLab) {
				throw new InvalidInputException("The selected course has the maximum number of tutorial Jobs!");
			}
			if(numLabJobs == aCourse.getNumLabSections() && aIsLab) {
				throw new InvalidInputException("The selected course has the maximum number of lab Jobs!");
			}
		}
		if(aMaxHours == 0) {
			throw new InvalidInputException("Maximum hours cannot be 0!");
		}
		if(aMaxHours < aMinHours) {
			throw new InvalidInputException("Maximum hours cannot be less than minimum hours!");
		}
		
		int usedBudget = 0;
		for (Job j : aCourse.getJobs()) {
			usedBudget += (int)(j.getMaxHours()*j.getWage());
		}
		if (usedBudget > aCourse.getBudget()) {
			throw new InvalidInputException("This posting would put the course over budget!");
		}
		
		TAjob j = new TAjob(aMaxHours, aWage, aDeadline, approval, aRequiredSkills, aRequiredCourseGPA,
				aRequiredCGPA, aRequiredExperience, aCourse, aMinHours, aIsLab);
	    rm.addJob(j);
	    PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void postGraderJob(int hours, double aWage, Date aDeadline, String aRequiredSkills, String aRequiredCourseGPA,
			String aRequiredCGPA, String aRequiredExperience, Course aCourse, boolean approval) throws InvalidInputException {
		Calendar calobj = Calendar.getInstance();
	    
		if (aDeadline == null) {
			throw new InvalidInputException("Deadline cannot be empty!");
		}
		if (aDeadline.before(calobj.getTime())) {
			throw new InvalidInputException("Deadline cannot be before today!");
		}
		if (aRequiredCourseGPA == null || aRequiredCourseGPA.trim().length() == 0) {
			throw new InvalidInputException("Required Course GPA cannot be empty!");
		}
		if(aRequiredCourseGPA.length() != 4) {
			throw new InvalidInputException("Required Course GPA must be 4 characters long!");
		} else {
			for(int i = 0; i < 4; i++) {
				if(i == 1 && aRequiredCourseGPA.charAt(i) != '.') {
					throw new InvalidInputException("Required Course GPA's second character must be a decimal!");
				} else if((i != 1) && (aRequiredCourseGPA.charAt(i) < 48 || aRequiredCourseGPA.charAt(i) > 57)) {
					throw new InvalidInputException("Required Course GPA must be a decimal number!");
				} else if(i == 0 && aRequiredCourseGPA.charAt(i) > 52) {
					throw new InvalidInputException("Required Course GPA cannot be geater than 4.00!");
				} else if((i==2 || i==3) && (aRequiredCourseGPA.charAt(0) == '4') && (aRequiredCourseGPA.charAt(i) > 48)) {
					throw new InvalidInputException("Required Course GPA cannot be geater than 4.00!");
				}
			}
		}
		if (aRequiredCGPA == null || aRequiredCGPA.trim().length() == 0) {
			throw new InvalidInputException("Required CGPA cannot be empty!");
		}
		if(aRequiredCGPA.length() != 4) {
			throw new InvalidInputException("Required CGPA must be 4 characters long!");
		} else {
			for(int i = 0; i < 4; i++) {
				if(i == 1 && aRequiredCGPA.charAt(i) != '.') {
					throw new InvalidInputException("Required CGPA's second character must be a decimal!");
				} else if((i != 1) && (aRequiredCGPA.charAt(i) < 48 || aRequiredCGPA.charAt(i) > 57)) {
					throw new InvalidInputException("Required CGPA must be a decimal number!");
				} else if(i == 0 && aRequiredCGPA.charAt(i) > 52) {
					throw new InvalidInputException("Required CGPA cannot be geater than 4.00!");
				} else if((i==2 || i==3) && (aRequiredCGPA.charAt(0) == '4') && (aRequiredCGPA.charAt(i) > 48)) {
					throw new InvalidInputException("Required CGPA cannot be geater than 4.00!");
				}
			}
		}
		if (aCourse == null) {
			throw new InvalidInputException("Course cannot be empty!");
		}
		if (hours == 0) {
			throw new InvalidInputException("Hours cannot be 0!");
		}
		
		int usedBudget = 0;
		for (Job j : aCourse.getJobs()) {
			usedBudget += (int)(j.getMaxHours()*j.getWage());
		}
		if (usedBudget > aCourse.getBudget()) {
			throw new InvalidInputException("This posting would put the course over budget!");
		}
		
		GraderJob j = new GraderJob(hours, aWage, aDeadline, approval, aRequiredSkills, aRequiredCourseGPA,
				aRequiredCGPA, aRequiredExperience, aCourse);
	    rm.addJob(j);
	    PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void addInstructorToCourse(Instructor instructor, Course course) throws InvalidInputException {
		if(instructor == null) {
			throw new InvalidInputException("An instructor must be selected!");
		}
		if(course == null) {
			throw new InvalidInputException("A course must be selected!");
		}
		if(course.getInstructors().size() == 4) {
			throw new InvalidInputException("This course has the maximum number of instructors (4)!");
		}
		if(course.getInstructors().contains(instructor)) {
			throw new InvalidInputException("This instructor has already been added to this course!");
		}
		course.addInstructor(instructor);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void createCourse(String name, int numTuts, int numLabs, int numStuds) throws InvalidInputException {
		if (name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Course name cannot be empty!");
		}
		if (numTuts == 0 && numLabs == 0) {
			throw new InvalidInputException("There must be at least one tutorial or lab section!");
		}
		if(numStuds == 0) {
			throw new InvalidInputException("There must be students enrolled in the course!");
		}
		
		int budget = 0;
		budget = (int)(numTuts*45 + numLabs*60 + numStuds*(0.5))*10; //45 hours for a tutorial job, 60 for a lab, and 0.5 hours/semester/student for grading. $10/hour
		
		Course c = new Course(name, numTuts, numLabs, numStuds, budget);
		rm.addCourse(c);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void applyToJob(String experience, String courseGPA, Applicant applicant, Job job) throws InvalidInputException {
		Calendar calobj = Calendar.getInstance();
	    //error checking can be cumbersome...
		if(applicant == null) {
			throw new InvalidInputException("Applicant cannot be empty!");
		}
		if(job == null) {
			throw new InvalidInputException("Job cannot be empty!");
		}
		if (job.getDeadline().before(calobj.getTime())) {
			throw new InvalidInputException("The application deadline for this job has passed!");
		}
		for(Application a : job.getApplications()) {
			if(a.getApplicant() == applicant) {
				throw new InvalidInputException("This applicant has already applied to the selected job!");
			}
		}
		if (courseGPA == null || courseGPA.trim().length() == 0) {
			throw new InvalidInputException("Course GPA cannot be empty!");
		}
		if(courseGPA.length() != 4) {
			throw new InvalidInputException("Course GPA must be 4 characters long!");
		} else {
			for(int i = 0; i < 4; i++) {
				if(i == 1 && courseGPA.charAt(i) != '.') {
					throw new InvalidInputException("Course GPA's second character must be a decimal!");
				} else if((i != 1) && (courseGPA.charAt(i) < 48 || courseGPA.charAt(i) > 57)) {
					throw new InvalidInputException("CourseGPA must be a decimal number!");
				} else if(i == 0 && courseGPA.charAt(i) > 52) {
					throw new InvalidInputException("Course GPA cannot be geater than 4.00!");
				} else if((i==2 || i==3) && (courseGPA.charAt(0) == '4') && (courseGPA.charAt(i) > 48)) {
					throw new InvalidInputException("Course GPA cannot be geater than 4.00!");
				}
			}
		}
		if(applicant.getApplications().size() == 3) {
			throw new InvalidInputException("This applicant has made the maximum number of applications!");
		}
		Application a = new Application(false, false, experience, courseGPA, applicant, job);
		rm.addApplication(a);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void approveJob(Job job) {
		job.setIsApproved(true);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}
	
	/*Finds all applications to the input Job, and ranks them by splitting them into 4 groups: 
	*List 0: applications where the applicant has applied to both lab and tutorial sections for the same class that the input is for, and is a grad.
	*List 1: applications where the applicant has applied to both lab and tutorial sections for the same class that the input is for, and is an undergrad.
	*List 2: graduate student applications to only one of the 2 types of TA job.
	*List 3: undergraduate student applications to only one of the 2 types of TA job.
	*/
	public ArrayList<ArrayList<Application>> rankApplications(Job job) {
		ArrayList<ArrayList<Application>> ranked = new ArrayList<ArrayList<Application>>(4);
		ArrayList<Application> tutAndLabGrad = new ArrayList<Application>();
		ArrayList<Application> tutAndLab = new ArrayList<Application>();
		ArrayList<Application> grad = new ArrayList<Application>();
		ArrayList<Application> underGrad = new ArrayList<Application>();
		
		Course c = job.getCourse();
		for(Application a : job.getApplications()) {
			Applicant stud = a.getApplicant();
			Boolean labApp = false;
			Boolean tutApp = false;
			
			//Check each application's applicant to see if they applied to both tutorial and lab jobs for the same course.
			for(Application application : stud.getApplications()) {
				if(application.getJob().getCourse() == c) {
					if(application.getJob() instanceof TAjob) {
						if(((TAjob)application.getJob()).getIsLab()) {
							labApp = true;
						} else {
							tutApp = true;
						}
					}
				}
			}
			//depending on which applications the applicants made, and their graduate status, add their applications to the corresponding list.
			if (stud.getIsGraduate()) {
				if (labApp && tutApp) {
					tutAndLabGrad.add(a);
				} else {
					grad.add(a);
				} 
			} else {
				if (labApp && tutApp) {
					tutAndLab.add(a);
				} else {
					underGrad.add(a);
				} 
			}
		}
		ranked.add(tutAndLabGrad); 	//position 0
		ranked.add(tutAndLab);		//position 1
		ranked.add(grad);			//position 2
		ranked.add(underGrad);		//position 3
		return ranked;
	}
	
	//The same method as the other ranker, except it ranks accepted offers, instead of all applications. 
	public ArrayList<ArrayList<Application>> rankAcceptedOffers(Job job) {
		ArrayList<ArrayList<Application>> ranked = new ArrayList<ArrayList<Application>>(4);
		ArrayList<Application> tutAndLabGrad = new ArrayList<Application>();
		ArrayList<Application> tutAndLab = new ArrayList<Application>();
		ArrayList<Application> grad = new ArrayList<Application>();
		ArrayList<Application> underGrad = new ArrayList<Application>();
		
		Course c = job.getCourse();
		for(Application a : job.getApplications()) {
			Applicant stud = a.getApplicant();
			Boolean labApp = false;
			Boolean tutApp = false;
			
			//Check each application's applicant to see if they have accepted offers to both tutorial and lab jobs for the same course.
			for(Application application : stud.getApplications()) {
				if (application.getIsOffered() && application.getIsAccepted()) {
					if (application.getJob().getCourse() == c) {
						if (application.getJob() instanceof TAjob) {
							if (((TAjob) application.getJob()).getIsLab()) {
								labApp = true;
							} else {
								tutApp = true;
							}
						}
					} 
				}
			}
			//depending on which applications the applicants made, and their graduate status, and the status of the job offers, add their applications to the corresponding list.
			if (a.getIsOffered() && a.getIsAccepted()) {
				if (stud.getIsGraduate()) {
					if (labApp && tutApp) {
						tutAndLabGrad.add(a);
					} else {
						grad.add(a);
					}
				} else {
					if (labApp && tutApp) {
						tutAndLab.add(a);
					} else {
						underGrad.add(a);
					}
				} 
			} 
		}
		ranked.add(tutAndLabGrad); 	//position 0
		ranked.add(tutAndLab);		//position 1
		ranked.add(grad);			//position 2
		ranked.add(underGrad);		//position 3
		return ranked;
	}

	public void offerJob(Application application) {
		application.setIsOffered(true);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void deleteApplication(Application application) {
		rm.removeApplication(application);
		application.delete();
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void submitFeedback(Assignment assignment, String text) {
		assignment.setFeedback(text);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

}
