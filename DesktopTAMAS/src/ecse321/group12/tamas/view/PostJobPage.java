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
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
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
	
	private int selectedCourse = -1;
	private String buttonState = "";
	private boolean isLabChecked = false;
	
	private GroupLayout layout;
	
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
		isLabCheckBox.setSelected(false);
		
		TAJobRadioButton = new JRadioButton("TA Job");
		TAJobRadioButton.setActionCommand("TA");
		graderJobRadioButton = new JRadioButton("Grader Job");
		graderJobRadioButton.setActionCommand("Grader");
		jobTypeSelection = new ButtonGroup();
		jobTypeSelection.add(TAJobRadioButton);
		jobTypeSelection.add(graderJobRadioButton);
		jobTypeSelection.setSelected(TAJobRadioButton.getModel(), true);
		buttonState = "TA";
		
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
	    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    this.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
	            exitProcedure();
	        }
	    });
	    setTitle(rm.getLoggedIn().getName());

	    // layout
	    layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    

	    layout.setHorizontalGroup(
	    	layout.createParallelGroup()
	        .addComponent(errorMessage)
	        .addGroup(layout.createSequentialGroup()
	        	.addGroup(layout.createParallelGroup()
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(courseLabel)
	        		        	.addComponent(courseList))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(minHoursLabel)
	        		        	.addComponent(minHoursSpinner))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(maxHoursLabel)
	        		        	.addComponent(maxHoursSpinner))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(wageLabel)
	        		        	.addComponent(wageSpinner))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(requiredCourseGPALabel)
	        		        	.addComponent(requiredCourseGPATextField))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(requiredCGPALabel)
	        		        	.addComponent(requiredCGPATextField))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(deadlineLabel)
	        		        	.addComponent(deadlineDatePicker)))
	        	.addGroup(layout.createParallelGroup()
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(TAJobRadioButton)
	        		        	.addComponent(graderJobRadioButton)
	        		        	.addComponent(isLabCheckBox))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(requiredSkillsLabel)
	        		        	.addComponent(requiredSkillsTextArea))
	        			.addGroup(layout.createSequentialGroup()
	        					.addComponent(requiredExperienceLabel)
	        		        	.addComponent(requiredExperienceTextArea))))
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
		        	.addGroup(layout.createSequentialGroup()
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(courseLabel)
		        		        	.addComponent(courseList))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(minHoursLabel)
		        		        	.addComponent(minHoursSpinner))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(maxHoursLabel)
		        		        	.addComponent(maxHoursSpinner))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(wageLabel)
		        		        	.addComponent(wageSpinner))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(requiredCourseGPALabel)
		        		        	.addComponent(requiredCourseGPATextField))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(requiredCGPALabel)
		        		        	.addComponent(requiredCGPATextField))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(deadlineLabel)
		        		        	.addComponent(deadlineDatePicker)))
		        	.addGroup(layout.createSequentialGroup()
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(TAJobRadioButton)
		        		        	.addComponent(graderJobRadioButton)
		        		        	.addComponent(isLabCheckBox))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(requiredSkillsLabel)
		        		        	.addComponent(requiredSkillsTextArea))
		        			.addGroup(layout.createParallelGroup()
		        					.addComponent(requiredExperienceLabel)
		        		        	.addComponent(requiredExperienceTextArea))))
		        .addGroup(layout.createParallelGroup()
		        		.addComponent(logOutButton)
		        		.addComponent(backButton)
		        		.addComponent(postJobButton))
		        );

	    layout.setHonorsVisibility(false);
	    this.setLocationRelativeTo(null);
	    pack();
	    refreshData();
	    
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
	    ActionListener RadioButtonListener = new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            RadioButtonActionPerformed(evt);
	        }
	    };
	    TAJobRadioButton.addActionListener(RadioButtonListener);
	    graderJobRadioButton.addActionListener(RadioButtonListener);
	    courseList.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedCourse = cb.getSelectedIndex();
	        }
	    });
	    isLabCheckBox.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				if (evt.getStateChange() == ItemEvent.DESELECTED) {
					isLabChecked = false;
				} else if (evt.getStateChange() == ItemEvent.SELECTED) {
					isLabChecked = true;
				}
			}
	    	
	    });
	}

	protected void RadioButtonActionPerformed(ActionEvent evt) {
		if(evt.getActionCommand().equals("TA") && buttonState.equals("Grader")) {
			layout.replace(hoursLabel, minHoursLabel);
			layout.replace(hoursSpinner, minHoursSpinner);
			maxHoursLabel.setVisible(true);
			maxHoursSpinner.setVisible(true);
			isLabCheckBox.setVisible(true);
			buttonState = "TA";
		} else if(evt.getActionCommand().equals("Grader") && buttonState.equals("TA")) {
			layout.replace(minHoursLabel, hoursLabel);
			layout.replace(minHoursSpinner, hoursSpinner);
			maxHoursLabel.setVisible(false);
			maxHoursSpinner.setVisible(false);
			isLabCheckBox.setVisible(false);
			buttonState = "Grader";
		}
	}

	protected void exitProcedure() {
	    TamasController tc = new TamasController(rm);
	    tc.logOut();
		this.dispose();
	    System.exit(0);
	}

	protected void backButtonActionPerformed() {
		error = null;
		if(rm.getLoggedIn() instanceof Department) {
			DepartmentMainPage dmp = new DepartmentMainPage(rm);
			this.dispose();
			dmp.setVisible(true);
			return;
		} else if(rm.getLoggedIn() instanceof Instructor) {
			InstructorMainPage imp = new InstructorMainPage(rm);
			this.dispose();
			imp.setVisible(true);
			return;
		}
	}

	protected void postJobButtonActionPerformed() {
		// create and call the controller 
		TamasController tc = new TamasController(rm);
		error = null;
		if (selectedCourse < 0) {
	        error = "Course needs to be selected!";
		}
		if (error == null) {
			if (rm.getLoggedIn() instanceof Department) {
				if (buttonState.equals("TA")) {
					try {
						tc.postTAJob((int) maxHoursSpinner.getValue(), (double) wageSpinner.getValue(),
								(java.sql.Date) deadlineDatePicker.getModel().getValue(),
								requiredSkillsTextArea.getText(), requiredCourseGPATextField.getText(),
								requiredCGPATextField.getText(), requiredExperienceTextArea.getText(),
								rm.getCourse(selectedCourse), (int) minHoursSpinner.getValue(), isLabChecked);
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				} else if (buttonState.equalsIgnoreCase("Grader")) {
					try {
						tc.postGraderJob((int) hoursSpinner.getValue(), (double) wageSpinner.getValue(),
								(java.sql.Date) deadlineDatePicker.getModel().getValue(),
								requiredSkillsTextArea.getText(), requiredCourseGPATextField.getText(),
								requiredCGPATextField.getText(), requiredExperienceTextArea.getText(),
								rm.getCourse(selectedCourse));
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				} 
			} else if (rm.getLoggedIn() instanceof Instructor) {
				Instructor inst = null;
				for (Instructor i : rm.getInstructors()) {
					if(i.equals(rm.getLoggedIn())) {
						inst = i;
					}
				}
				if (buttonState.equals("TA")) {
					try {
						tc.postTAJob((int) maxHoursSpinner.getValue(), (double) wageSpinner.getValue(),
								(java.sql.Date) deadlineDatePicker.getModel().getValue(),
								requiredSkillsTextArea.getText(), requiredCourseGPATextField.getText(),
								requiredCGPATextField.getText(), requiredExperienceTextArea.getText(),
								inst.getCourse(selectedCourse), (int) minHoursSpinner.getValue(), isLabChecked);
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				} else if (buttonState.equalsIgnoreCase("Grader")) {
					try {
						tc.postGraderJob((int) hoursSpinner.getValue(), (double) wageSpinner.getValue(),
								(java.sql.Date) deadlineDatePicker.getModel().getValue(),
								requiredSkillsTextArea.getText(), requiredCourseGPATextField.getText(),
								requiredCGPATextField.getText(), requiredExperienceTextArea.getText(),
								inst.getCourse(selectedCourse));
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				} 
			}
		}
		refreshData();
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
	        // course list
			courseList.removeAllItems();
			if (rm.getLoggedIn() instanceof Department) {
				for (Course c : rm.getCourses()) {
					courseList.addItem(c.getName());
				} 
			} else if(rm.getLoggedIn() instanceof Instructor) { //This block makes it so that instructors can only post jobs for their courses.
				Instructor inst = null;
				for (Instructor i : rm.getInstructors()) {
					if(i.equals(rm.getLoggedIn())) {
						inst = i;
					}
				}
				if(inst != null) {
					for (Course c : inst.getCourses()) {
						courseList.addItem(c.getName());
					} 
				}
			}
			selectedCourse = -1;
			courseList.setSelectedIndex(selectedCourse);
			// text resets
			requiredCourseGPATextField.setText("");
			requiredCGPATextField.setText("");
			requiredSkillsTextArea.setText("");
			requiredExperienceTextArea.setText("");
			// deadline
			deadlineDatePicker.getModel().setValue(null);
			//Spinners
			maxHoursSpinner.setValue(60);
			minHoursSpinner.setValue(0);
			hoursSpinner.setValue(60);
			wageSpinner.setValue(10.0);
	    }

	    // this is needed because the size of the window changes depending on whether an error message is shown or not
	    pack();
	}
}

