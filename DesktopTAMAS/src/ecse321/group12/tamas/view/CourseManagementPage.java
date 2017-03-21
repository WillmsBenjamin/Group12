package ecse321.group12.tamas.view;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.controller.DepartmentRegisteredException;
import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.UserType;
import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.Department;
import ecse321.group12.tamas.model.Instructor;
import ecse321.group12.tamas.model.ResourceManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

public class CourseManagementPage extends JFrame {

	private static final long serialVersionUID = -3813818727258574249L;

	private JTextField nameTextField;
	private JLabel nameLabel;
	
	private JLabel numberOfTutorialsLabel;
	private JLabel numberOfLabsLabel;
	private JLabel numberOfStudentsLabel;
	private JLabel budgetLabel;
	private JSpinner numberOfTutorialsSpinner;
	private JSpinner numberOfLabsSpinner;
	private JSpinner numberOfStudentsSpinner;
	private JSpinner budgetSpinner;
	
	private JLabel courseLabel;
	private JComboBox<String> courseList;
	
	private JLabel instructorLabel;
	private JComboBox<String> instructorList;
	private JButton addInstructorButton;
	
	private JButton createCourseButton;
	private JButton backButton;
	private JButton logOutButton;
	
	private ResourceManager rm;
	
	private String error = null;
	private JLabel errorMessage;
	
	private Integer selectedCourse = -1;
	private Integer selectedInstructor = -1;
	
	/** Creates new form CourseManagementPage */
	public CourseManagementPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	private void initComponents() {
		nameTextField = new JTextField();
		nameLabel = new JLabel("Course Name:");
		
		numberOfTutorialsLabel = new JLabel("Tutorial Sections:");
		numberOfLabsLabel = new JLabel("Lab Sections:");
		numberOfStudentsLabel = new JLabel("Number of Students:");
		budgetLabel = new JLabel("Budget (hours):");
		numberOfTutorialsSpinner = new JSpinner( new SpinnerNumberModel(0, 0, 10, 1) );
		numberOfLabsSpinner = new JSpinner( new SpinnerNumberModel(0, 0, 20, 1) );
		numberOfStudentsSpinner = new JSpinner( new SpinnerNumberModel(0, 0, 1000, 1) );
		budgetSpinner = new JSpinner( new SpinnerNumberModel(0, 0, 1000, 1));
		
		courseLabel = new JLabel("Course:");
		courseList = new JComboBox<String>(new String[0]);
		
		instructorLabel = new JLabel("Instructor:");
		instructorList = new JComboBox<String>(new String[0]);
		addInstructorButton = new JButton("Add Instructor");
		
		createCourseButton = new JButton("Create Course");
		backButton = new JButton("Back");
		logOutButton = new JButton("Sign Out");
	    
	    // elements for error message
	    errorMessage = new JLabel();
	    errorMessage.setForeground(Color.RED);

	    // global settings and listeners
	    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    this.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	            exitProcedure();
	        }
	    });
	    setTitle(rm.getLoggedIn().getName());

	    // layout
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    

	    layout.setHorizontalGroup(
	    	layout.createParallelGroup()
	        .addComponent(errorMessage)
	        .addGroup(layout.createSequentialGroup()
	        	.addGroup(layout.createParallelGroup()
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(nameLabel)
	        					.addComponent(nameTextField))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(numberOfTutorialsLabel)
	        					.addComponent(numberOfTutorialsSpinner))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(numberOfLabsLabel)
	        					.addComponent(numberOfLabsSpinner))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(numberOfStudentsLabel)
	        					.addComponent(numberOfStudentsSpinner))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(budgetLabel)
	        					.addComponent(budgetSpinner))
	        			.addComponent(createCourseButton))
	        	.addGroup(layout.createParallelGroup()
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(courseLabel)
	        					.addComponent(courseList))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(instructorLabel)
	        					.addComponent(instructorList)))
	        			.addComponent(addInstructorButton))
	        .addGroup(layout.createSequentialGroup()
	        		.addComponent(logOutButton)
	        		.addComponent(backButton))
	        );

	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {nameLabel, numberOfTutorialsLabel, numberOfLabsLabel, numberOfStudentsLabel});
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {nameTextField, numberOfTutorialsSpinner, numberOfLabsSpinner, numberOfStudentsSpinner});
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {courseLabel, instructorLabel});
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {courseList, instructorList});
	    
	    layout.setVerticalGroup(
	    		layout.createSequentialGroup()
		        .addComponent(errorMessage)
		        .addGroup(layout.createParallelGroup()
		        	.addGroup(layout.createSequentialGroup()
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(nameLabel)
		        					.addComponent(nameTextField))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(numberOfTutorialsLabel)
		        					.addComponent(numberOfTutorialsSpinner))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(numberOfLabsLabel)
		        					.addComponent(numberOfLabsSpinner))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(numberOfStudentsLabel)
		        					.addComponent(numberOfStudentsSpinner))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(budgetLabel)
		        					.addComponent(budgetSpinner))
		        			.addComponent(createCourseButton))
		        	.addGroup(layout.createSequentialGroup()
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(courseLabel)
		        					.addComponent(courseList))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(instructorLabel)
		        					.addComponent(instructorList)))
		        			.addComponent(addInstructorButton))
		        .addGroup(layout.createParallelGroup()
		        		.addComponent(logOutButton)
		        		.addComponent(backButton))
		        );
	    
	    layout.linkSize(SwingConstants.VERTICAL, new java.awt.Component[] {courseLabel, instructorLabel, courseList, instructorList, nameLabel,
	    		numberOfTutorialsLabel, numberOfLabsLabel, numberOfStudentsLabel, nameTextField, numberOfTutorialsSpinner, numberOfLabsSpinner, numberOfStudentsSpinner});

	    
	    this.setLocationRelativeTo(null);
	    pack();
	    refreshData();
	    
	    logOutButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            logOutButtonActionPerformed();
	        }
	    });
	    backButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            backButtonActionPerformed();
	        }
	    });
	    addInstructorButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	addInstructorButtonActionPerformed();
	        }
	    });
	    createCourseButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	createCourseButtonActionPerformed();
	        }
	    });
	    courseList.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedCourse = cb.getSelectedIndex();
	        }
	    });
	    instructorList.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedInstructor = cb.getSelectedIndex();
	        }
	    });
	}
	
	protected void logOutButtonActionPerformed() {
		TamasController tc = new TamasController(rm);
		LogInPage lip = new LogInPage(rm);
		tc.logOut();
		this.dispose();
		lip.setVisible(true);
	}
	
	protected void exitProcedure() {
	    TamasController tc = new TamasController(rm);
	    tc.logOut();
		this.dispose();
	    System.exit(0);
	}
	
	protected void backButtonActionPerformed() {
		DepartmentMainPage dmp = new DepartmentMainPage(rm);
		this.dispose();
		dmp.setVisible(true);
	}
	
	protected void addInstructorButtonActionPerformed() {
		// create and call the controller 
		TamasController tc = new TamasController(rm);
		error = null;
		if (selectedCourse < 0) {
	        error = "Course needs to be selected!";
		}
		if (selectedInstructor < 0) {
	        error = "Instructor needs to be selected!";
		}
		if (error == null) {
			try {
				tc.addInstructorToCourse(rm.getInstructor(selectedInstructor), rm.getCourse(selectedCourse));
			} catch (InvalidInputException e) {
				error = e.getMessage();
			} 
		}
		//update visuals
		refreshData();
	}

	protected void createCourseButtonActionPerformed() {
		// create and call the controller 
		TamasController tc = new TamasController(rm);
		error = null;
		try {
			tc.createCourse(nameTextField.getText(), (int)numberOfTutorialsSpinner.getValue(), (int)numberOfLabsSpinner.getValue(), (int)numberOfStudentsSpinner.getValue(), (int)budgetSpinner.getValue());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//update visuals if there is an error
		refreshData();
	}

	private void refreshData() {
	    // error
	    errorMessage.setText(error);
	    if (error == null || error.length() == 0) {
	    	courseList.removeAllItems();
	    	for (Course c : rm.getCourses()) {
	    		courseList.addItem(c.getName());
	    	}
	    	selectedCourse = -1;
	    	courseList.setSelectedIndex(selectedCourse);
	    	// event list
	    	instructorList.removeAllItems();
	    	for (Instructor i : rm.getInstructors()) {
	    		instructorList.addItem(i.getName());
	    	}
	    	selectedInstructor = -1;
	    	instructorList.setSelectedIndex(selectedInstructor);
	    	// text reset
	    	nameTextField.setText("");
	    	// spinner reset
	    	numberOfTutorialsSpinner.setValue(0);
	    	numberOfLabsSpinner.setValue(0);
	    	numberOfStudentsSpinner.setValue(0);
	    	budgetSpinner.setValue(0);
	    }
	    // this is needed because the size of the window changes depending on whether an error message is shown or not
	    pack();
	}
}

