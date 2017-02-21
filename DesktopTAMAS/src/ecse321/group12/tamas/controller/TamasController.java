package ecse321.group12.tamas.controller;

import ecse321.group12.tamas.controller.InvalidInputException;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

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
					throw new InvalidInputException("Student CGPA must be all numbers!");
				}
			}
		}
		Applicant a = new Applicant(name, id, cGPA, skills, isGraduate);
		rm.addApplicant(a);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}
	
	public void checkDepartmentExistence() throws DepartmentRegisteredException {
		if(rm.getDepartment() instanceof Department) {
			throw new DepartmentRegisteredException("A department exists. No more can be added!");
		}
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
		Instructor d = new Instructor(name, id);
		rm.addInstructor(d);
		PersistenceXStream.saveToXMLwithXStream(rm);
		
	}

	public void postTAJob(int aMaxHours, double aWage, Date aDeadline, String aRequiredSkills, String aRequiredCourseGPA,
			String aRequiredCGPA, String aRequiredExperience, Course aCourse, int aMinHours, boolean aIsLab) throws InvalidInputException {
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
					throw new InvalidInputException("Required Course GPA must be all numbers!");
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
					throw new InvalidInputException("Required CGPA must be all numbers!");
				}
			}
		}
		if (aCourse == null) {
			throw new InvalidInputException("Course cannot be empty!");
		}
		
		TAjob j = new TAjob(aMaxHours, aWage, aDeadline, aRequiredSkills, aRequiredCourseGPA,
				aRequiredCGPA, aRequiredExperience, aCourse, aMinHours, aIsLab);
	    rm.addJob(j);
	    PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void postGraderJob(int aMaxHours, double aWage, Date aDeadline, String aRequiredSkills, String aRequiredCourseGPA,
			String aRequiredCGPA, String aRequiredExperience, Course aCourse) throws InvalidInputException {
		//TODO: Error checking, functionality, and persistence
		
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
		course.addInstructor(instructor);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

	public void addContactTime(Date date, Time startTime, Time endTime, Instructor instructor) throws InvalidInputException {
		if (date == null) {
			throw new InvalidInputException("Contact date cannot be empty!");
		}
		if (startTime == null) {
			throw new InvalidInputException("Start time cannot be empty!");
		}
		if (endTime == null) {
			throw new InvalidInputException("End time cannot be empty!");
		}
		if (endTime.getTime() < startTime.getTime()) {
			throw new InvalidInputException("End time cannot be before start time!");
		}
		Hours h = new Hours(date, startTime, endTime, instructor);
		rm.addContactTime(h);
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
		Course c = new Course(name, numTuts, numLabs, numStuds);
		rm.addCourse(c);
		PersistenceXStream.saveToXMLwithXStream(rm);
	}

}
