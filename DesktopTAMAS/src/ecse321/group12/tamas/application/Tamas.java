package ecse321.group12.tamas.application;

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
	            new LogInPage(rm).setVisible(true);
	        }
	    });
	}

}
