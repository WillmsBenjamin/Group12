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
		for(User u : rm.getApplicants()) {
			if(u.getId() == id) {
				rm.setLoggedIn(u);
				PersistenceXStream.saveToXMLwithXStream(rm);
				return;
			}
		}
		for(User u : rm.getInstructors()) {
			if(u.getId() == id) {
				rm.setLoggedIn(u);
				PersistenceXStream.saveToXMLwithXStream(rm);
				return;
			}
		}
		if(rm.getDepartment() instanceof Department) {
			if(rm.getDepartment().getId() == id) {
				rm.setLoggedIn(rm.getDepartment());
				PersistenceXStream.saveToXMLwithXStream(rm);
				return;
			}
		}
		throw new InvalidInputException("Id doesn't match a registered user!");
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
				} else if(cGPA.charAt(i) < 48 || cGPA.charAt(i) > 57) {
					throw new InvalidInputException("Student CGPA must be all numbers!");
				}
			}
		}
		Applicant a = new Applicant(name, id, cGPA, skills, isGraduate);
		rm.addApplicant(a);
		PersistenceXStream.saveToXMLwithXStream(rm);
		
	}
	

	public void checkDepartmentExistence() throws DepartmentRegisteredException{
		if(rm.getDepartment() instanceof Department) {
			throw new DepartmentRegisteredException("A department exists. No more can be added!");
		}
	}
	
}
