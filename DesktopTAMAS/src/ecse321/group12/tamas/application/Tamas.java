package ecse321.group12.tamas.application;

import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Department;
import ecse321.group12.tamas.model.Instructor;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.persistence.PersistenceXStream;
import ecse321.group12.tamas.view.*;

public class Tamas {
	
	private static String fileName = "output/tamas.xml";

	public static void main(String[] args) {
		final ResourceManager rm = PersistenceXStream.initializeModelManager(fileName);

	    // start UI
	    java.awt.EventQueue.invokeLater(new Runnable() {
	        public void run() {
	        	if(rm.getLoggedIn() == null) {
	        		new LogInPage(rm).setVisible(true);
	        	} else if(rm.getLoggedIn() instanceof Department) {
	        		//TODO: Open in department view
	        	} else if(rm.getLoggedIn() instanceof Instructor) {
	        		//TODO: Open in instructor view
	        	} else if(rm.getLoggedIn() instanceof Applicant) {
	        		//TODO: Open in applicant view
	        	}
	        }
	    });
	}

}
