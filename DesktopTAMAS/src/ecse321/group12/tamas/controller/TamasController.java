package ecse321.group12.tamas.controller;

import ecse321.group12.tamas.controller.InvalidInputException;
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
			if(a.getId() == id) {
				rm.setLoggedIn(a);
				PersistenceXStream.saveToXMLwithXStream(rm);
				return;
			}
		}
		for(Instructor i : rm.getInstructors()) {
			if(i.getId() == id) {
				rm.setLoggedIn(i);
				PersistenceXStream.saveToXMLwithXStream(rm);
				return;
			}
		}
		if(rm.getDepartment() != null) {
			if(rm.getDepartment().getId() == id) {
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

	public void registerDepartment(String name, String id) throws InvalidInputException {
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
		Department d = new Department(name, id);
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
	
}
