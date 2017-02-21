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
import ecse321.group12.tamas.model.Department;
import ecse321.group12.tamas.model.Instructor;
import ecse321.group12.tamas.model.ResourceManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.Color;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.Alignment;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;


public class PostJobPage extends JFrame {

	private static final long serialVersionUID = -3813867847258218349L;

	private JLabel maxHoursLabel;
	private JLabel minHoursLabel;
	private JLabel hoursLabel;
	private JLabel deadlineLabel;
	private JLabel wageLabel;
	private JLabel requiredSkillsLabel;
	private JLabel requiredCGPALabel;
	private JLabel requiredCourseGPALabel;
	private JLabel requiredExperienceLabel;
	private JLabel courseLabel;
	private JCheckBox isLabCheckBox;
	private JRadioButton TAJobRadioButton;
	private JRadioButton graderJobRadioButton;
	private ButtonGroup jobTypeSelection;
	private JButton postJobButton;
	private JButton backButton;
	private JButton logOutButton;
	private JSpinner maxHoursSpinner;
	private JSpinner minHoursSpinner;
	private JSpinner hoursSpinner;
	private JSpinner wageSpinner;
	private JDatePickerImpl deadlineDatePicker;
	private JTextArea requiredSkillsTextArea;
	private JTextArea requiredExperienceTextArea;
	private JTextField requiredCGPATextField;
	private JTextField requiredCourseGPATextField;
	private JComboBox<String> courseList;
	
	private ResourceManager rm;
	
	private String error = null;
	private JLabel errorMessage;
	
	private Integer selectedCourse = -1;
	
	/** Creates new form PostJobPage */
	public PostJobPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	private void initComponents() {
	    // elements for logging out and posting a job
		maxHoursLabel = new JLabel("Max Hours:");
		minHoursLabel = new JLabel("Min Hours:");
		hoursLabel = new JLabel("Hours:");
		deadlineLabel = new JLabel("Application Deadline:");
		wageLabel = new JLabel("Wage($/hr):");
		requiredSkillsLabel = new JLabel("Required Skills:");
		requiredCGPALabel = new JLabel("Required CGPA:");
		requiredCourseGPALabel = new JLabel("Required Course GPA:");
		requiredExperienceLabel = new JLabel("Required Experience:");
		courseLabel = new JLabel("Course:");
		
		isLabCheckBox = new JCheckBox("Lab Session?");
		
		TAJobRadioButton = new JRadioButton("TA Job");
		graderJobRadioButton = new JRadioButton("Grader Job");
		jobTypeSelection = new ButtonGroup();
		jobTypeSelection.add(TAJobRadioButton);
		jobTypeSelection.add(graderJobRadioButton);
		
		postJobButton = new JButton("Post Job");
		backButton = new JButton("Back");
		logOutButton = new JButton("Sign Out");
		
		maxHoursSpinner = new JSpinner( new SpinnerNumberModel(60, 0, 120, 1) );
		minHoursSpinner = new JSpinner( new SpinnerNumberModel(0, 0, 120, 1) );
		hoursSpinner = new JSpinner( new SpinnerNumberModel(60, 0, 120, 1) );
		wageSpinner = new JSpinner( new SpinnerNumberModel(10.0, 0.0, 50.0, 0.1) );
		
		SqlDateModel model = new SqlDateModel();
	    Properties p = new Properties();
	    p.put("text.today", "Today");
	    p.put("text.month", "Month");
	    p.put("text.year", "Year");
	    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	    deadlineDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		requiredSkillsTextArea = new JTextArea(7, 30);
		requiredExperienceTextArea = new JTextArea(7, 30);
		
		requiredCGPATextField = new JTextField();
		requiredCourseGPATextField = new JTextField();
		
		courseList = new JComboBox<String>(new String[0]);
	    
	    // elements for error message
	    errorMessage = new JLabel();
	    errorMessage.setForeground(Color.RED);

	    // global settings and listeners
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("TAMAS: POST JOB");

	    // layout
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    

	    layout.setHorizontalGroup(
	    	layout.createParallelGroup()
	        .addComponent(errorMessage)
	        .addGroup(layout.createSequentialGroup()
	        	.addComponent(courseLabel)
	        	.addComponent(courseList)
	        	.addComponent(TAJobRadioButton)
	        	.addComponent(graderJobRadioButton)
	        	.addComponent(isLabCheckBox))	
	        .addGroup(layout.createSequentialGroup()
	        	.addComponent(minHoursLabel)
	        	.addComponent(minHoursSpinner)
	        	.addComponent(requiredSkillsLabel)
	        	.addComponent(requiredSkillsTextArea))
	        .addGroup(layout.createSequentialGroup()
	        	.addComponent(maxHoursLabel)
	        	.addComponent(maxHoursSpinner))
	        .addGroup(layout.createSequentialGroup()
	        	.addComponent(wageLabel)
	        	.addComponent(wageSpinner))
	        .addGroup(layout.createSequentialGroup()
	        	.addComponent(requiredCourseGPALabel)
	        	.addComponent(requiredCourseGPATextField)
	        	.addComponent(requiredExperienceLabel)
	        	.addComponent(requiredExperienceTextArea))
	        .addGroup(layout.createSequentialGroup()
	        	.addComponent(requiredCGPALabel)
	        	.addComponent(requiredCGPATextField))
	        .addGroup(layout.createSequentialGroup()
	        	.addComponent(deadlineLabel)
	        	.addComponent(deadlineDatePicker))
	        .addGroup(layout.createSequentialGroup()
	        	.addComponent(logOutButton)
	        	.addComponent(backButton)
	        	.addComponent(postJobButton))
	        );

	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {courseLabel, minHoursLabel, maxHoursLabel, wageLabel, requiredCourseGPALabel, requiredCGPALabel, deadlineLabel});
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {courseList, minHoursSpinner, maxHoursSpinner, wageSpinner, requiredCourseGPATextField, requiredCGPATextField});
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {requiredSkillsLabel, requiredExperienceLabel});
	    
	    layout.setVerticalGroup(
	    	layout.createSequentialGroup()
		    .addComponent(errorMessage)
		    .addGroup(layout.createParallelGroup()
		        .addComponent(courseLabel)
		        .addComponent(courseList)
		        .addComponent(TAJobRadioButton)
		        .addComponent(graderJobRadioButton)
		        .addComponent(isLabCheckBox))	
		    .addGroup(layout.createParallelGroup()
		        .addComponent(minHoursLabel)
		        .addComponent(minHoursSpinner)
		        .addComponent(requiredSkillsLabel)
		        .addComponent(requiredSkillsTextArea))
		    .addGroup(layout.createParallelGroup()
		        .addComponent(maxHoursLabel)
		        .addComponent(maxHoursSpinner))
		    .addGroup(layout.createParallelGroup()
		        .addComponent(wageLabel)
		        .addComponent(wageSpinner))
		    .addGroup(layout.createParallelGroup()
		        .addComponent(requiredCourseGPALabel)
		        .addComponent(requiredCourseGPATextField)
		        .addComponent(requiredExperienceLabel)
		        .addComponent(requiredExperienceTextArea))
		    .addGroup(layout.createParallelGroup()
		        .addComponent(requiredCGPALabel)
		        .addComponent(requiredCGPATextField))
		    .addGroup(layout.createParallelGroup()
		        .addComponent(deadlineLabel)
		        .addComponent(deadlineDatePicker))
		    .addGroup(layout.createParallelGroup()
		        .addComponent(logOutButton)
		        .addComponent(backButton)
		        .addComponent(postJobButton))
		    );

	    
	    this.setLocationRelativeTo(null);
	    pack();
	    
	    logOutButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            logOutButtonActionPerformed();
	        }
	    });
	    postJobButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            postJobButtonActionPerformed();
	        }
	    });
	    backButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            backButtonActionPerformed();
	        }
	    });
	}

	protected void backButtonActionPerformed() {
		error = null;
		RegisterInstructorPage rip = new RegisterInstructorPage(rm);
		this.dispose();
		rip.setVisible(true);
	}

	protected void postJobButtonActionPerformed() { //TODO: finish this
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

	protected void logOutButtonActionPerformed() {
		// create and call the controller
		TamasController tc = new TamasController(rm);
		error = null;
		tc.logOut();
		LogInPage lip = new LogInPage(rm);
		this.dispose();
		lip.setVisible(true);
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

