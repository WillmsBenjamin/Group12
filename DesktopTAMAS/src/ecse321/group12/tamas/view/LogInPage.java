package ecse321.group12.tamas.view;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.controller.DepartmentRegisteredException;
import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.UserType;
import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Department;
import ecse321.group12.tamas.model.Instructor;
import ecse321.group12.tamas.model.ResourceManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;


public class LogInPage extends JFrame {

	private static final long serialVersionUID = -3813819647258555349L;

	private JTextField logInIdTextField;
	private JLabel logInIdLabel;
	private JButton registerDepartmentButton;
	private JButton registerInstructorButton;
	private JButton registerStudentButton;
	private JButton logInButton;
	
	private ResourceManager rm;
	
	private String error = null;
	private JLabel errorMessage;
	
	/** Creates new form ParticipantPage */
	public LogInPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	private void initComponents() {
	    // elements for logging in and switching to register views
		logInIdTextField = new JTextField();
		logInIdLabel = new JLabel();
		registerDepartmentButton = new JButton();
		registerInstructorButton = new JButton();
		registerStudentButton = new JButton();
		logInButton = new JButton();
	    
	    // elements for error message
	    errorMessage = new JLabel();
	    errorMessage.setForeground(Color.RED);

	    // global settings and listeners
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("TAMAS LOGIN");

	    logInIdLabel.setText("Id:");
	    registerDepartmentButton.setText("Register Department");
	    registerInstructorButton.setText("Register Instructor");
	    registerStudentButton.setText("Register Student");
	    logInButton.setText("Sign In");

	    // layout
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);

	    layout.setHorizontalGroup(
	    	layout.createParallelGroup()
	        .addComponent(errorMessage)
	        .addGroup(layout.createSequentialGroup()
	        	.addComponent(logInIdLabel)
	        	.addGroup(layout.createParallelGroup()
	        		.addComponent(logInIdTextField, 200, 200, 400)
	        		.addComponent(logInButton))
	        	.addGroup(layout.createParallelGroup()
	        		.addComponent(registerDepartmentButton)
	        		.addComponent(registerInstructorButton)
	        		.addComponent(registerStudentButton))
	        ));

	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {registerDepartmentButton, logInIdTextField, registerInstructorButton, logInButton, registerStudentButton});

	    layout.setVerticalGroup(
	    	layout.createSequentialGroup()
	    	.addComponent(errorMessage)
	        .addGroup(layout.createParallelGroup()
	        	.addComponent(logInIdLabel)
	        	.addGroup(layout.createSequentialGroup()
	        		.addComponent(logInIdTextField)
	        		.addComponent(logInButton))
	        	.addGroup(layout.createSequentialGroup()
	        		.addComponent(registerDepartmentButton)
	        		.addComponent(registerInstructorButton)
	        		.addComponent(registerStudentButton))
	        ));

	    pack();
	    
	    logInButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            logInButtonActionPerformed();
	        }
	    });
	    registerDepartmentButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            registerDepartmentButtonActionPerformed();
	        }
	    });
	    registerInstructorButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            registerInstructorButtonActionPerformed();
	        }
	    });
	    registerStudentButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            registerStudentButtonActionPerformed();
	        }
	    });
	}
	
	protected void registerStudentButtonActionPerformed() {
		error = null;
		RegisterStudentPage rsp = new RegisterStudentPage(rm);
		this.dispose();
		rsp.setVisible(true);
	}

	protected void registerInstructorButtonActionPerformed() {
		error = null;
		RegisterInstructorPage rip = new RegisterInstructorPage(rm);
		this.dispose();
		rip.setVisible(true);
	}

	protected void registerDepartmentButtonActionPerformed() {
		// create and call the controller 
		TamasController tc = new TamasController(rm);
		error = null;
		try {
			tc.checkDepartmentExistence();
		} catch (DepartmentRegisteredException e) {
			error = e.getMessage();
		}
		//update visuals if there is an error
		if(error != null) {
			refreshData();
			return;
		} else {
			RegisterDepartmentPage rdp = new RegisterDepartmentPage(rm);
			this.dispose();
			rdp.setVisible(true);
		}
	}

	protected void logInButtonActionPerformed() {
		// TODO Auto-generated method stub
		// create and call the controller
		TamasController tc = new TamasController(rm);
		error = null;
		try {
			tc.logIn(logInIdTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//update visuals if there is an error
		if(error != null) {
			refreshData();
			return;
		} else if(rm.getLoggedIn() instanceof Department){
			//TODO: Go to department view
			return;
		} else if(rm.getLoggedIn() instanceof Instructor){
			//TODO: Go to instructor view
			return;
		} else if(rm.getLoggedIn() instanceof Applicant){
			//TODO: Go to applicant view
			return;
		}
	}

	private void refreshData() {
		// error
	    errorMessage.setText(error);
	    if (error == null || error.length() == 0) {
	        // participant
	        logInIdTextField.setText("");
	    }
	    pack();
	}
}
