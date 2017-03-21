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
import ecse321.group12.tamas.model.GraderJob;
import ecse321.group12.tamas.model.Instructor;
import ecse321.group12.tamas.model.Job;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.model.TAjob;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
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

public class ApplyToJobPage extends JFrame {

	private static final long serialVersionUID = -3762818727258574249L;

	private JTextArea jobInfoTextArea;
	private JLabel jobInfoLabel;
	private JTextArea experienceTextArea;
	private JLabel experienceLabel;
	private JLabel courseGPALabel;
	private JTextField courseGPATextField;
	private JLabel applicantLabel;
	private JComboBox<String> applicantList;
	private JLabel jobLabel;
	private JComboBox<String> jobList;
	private JButton applyButton;
	private JButton backButton;
	private JButton logOutButton;
	
	private JLabel applicationDeadlineLabel;

	private ResourceManager rm;
	
	private String error = null;
	private JLabel errorMessage;
	
	private Integer selectedApplicant = -1;
	private Integer selectedJob = -1;
	
	private GroupLayout layout;
	
	/** Creates new form CourseManagementPage */
	public ApplyToJobPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	private void initComponents() {
		jobInfoTextArea = new JTextArea(6, 40);
		jobInfoTextArea.setEditable(false);
		jobInfoLabel = new JLabel("Job Info:");
		experienceTextArea = new JTextArea(4, 40);
		experienceLabel = new JLabel("Experience:");
		
		courseGPALabel = new JLabel("Course GPA:");
		courseGPATextField = new JTextField();
		
		applicantLabel = new JLabel("Applicant:");
		applicantList = new JComboBox<String>(new String[0]);
		
		jobLabel = new JLabel("Job:");
		jobList = new JComboBox<String>(new String[0]);
		
		applyButton = new JButton("Apply");
		backButton = new JButton("Back");
		logOutButton = new JButton("Sign Out");
		
		applicationDeadlineLabel = new JLabel("Application Deadline:");
	    
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
	        .addComponent(applicationDeadlineLabel)
	        .addGroup(layout.createSequentialGroup()
	        		.addComponent(applicantLabel)
	        		.addComponent(applicantList))
	        .addGroup(layout.createParallelGroup()
	    	        .addGroup(layout.createSequentialGroup()
	    	        		.addComponent(jobLabel)
	    	        		.addComponent(jobList))
	    	        .addGroup(layout.createSequentialGroup()
	    	        		.addComponent(courseGPALabel)
	    	        		.addComponent(courseGPATextField))
	    	        .addGroup(layout.createSequentialGroup()
	    	        		.addComponent(jobInfoLabel)
	    	        		.addComponent(jobInfoTextArea))
	    	        .addGroup(layout.createSequentialGroup()
	    	        		.addComponent(experienceLabel)
	    	        		.addComponent(experienceTextArea)))
	        .addGroup(layout.createSequentialGroup()
	        		.addComponent(logOutButton)
	        		.addComponent(backButton)
	        		.addComponent(applyButton))
	        );

	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {applicantLabel, jobLabel, jobInfoLabel, courseGPALabel, experienceLabel});
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {applicantList, jobList, courseGPATextField});
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {jobInfoTextArea, experienceTextArea});
	    
	    layout.setVerticalGroup(
	    		layout.createSequentialGroup()
		        .addComponent(errorMessage)
		        .addComponent(applicationDeadlineLabel)
		        .addGroup(layout.createParallelGroup()
		        		.addComponent(applicantLabel)
		        		.addComponent(applicantList))
		        .addGroup(layout.createSequentialGroup()
		    	        .addGroup(layout.createParallelGroup()
		    	        		.addComponent(jobLabel)
		    	        		.addComponent(jobList))
		    	        .addGroup(layout.createParallelGroup()
		    	        		.addComponent(courseGPALabel)
		    	        		.addComponent(courseGPATextField))
		    	        .addGroup(layout.createParallelGroup()
		    	        		.addComponent(jobInfoLabel)
		    	        		.addComponent(jobInfoTextArea))
		    	        .addGroup(layout.createParallelGroup()
		    	        		.addComponent(experienceLabel)
		    	        		.addComponent(experienceTextArea)))
		        .addGroup(layout.createParallelGroup()
		        		.addComponent(logOutButton)
		        		.addComponent(backButton)
		        		.addComponent(applyButton))
		        );

	    layout.setHonorsVisibility(false);
	    if(rm.getLoggedIn() instanceof Department) {
	    	applicantList.setVisible(true);
	    	applicantLabel.setVisible(true);
	    } else if(rm.getLoggedIn() instanceof Applicant) {
	    	applicantList.setVisible(false);
	    	applicantLabel.setVisible(false);
	    }
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
	    applyButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	applyButtonActionPerformed();
	        }
	    });
	    applicantList.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedApplicant = cb.getSelectedIndex();
	        }
	    });
	    jobList.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedJob = cb.getSelectedIndex();
	            displayJobInfo();
	            displayApplicationDeadline();
	        }
	    });
	}
	
	protected void displayApplicationDeadline() {
		applicationDeadlineLabel.setText("Application Deadline: " + rm.getJob(selectedJob).getDeadline());
		pack();
	}

	protected void displayJobInfo() {
		String jobInfo;
		if (selectedJob >= 0) {
			jobInfo = rm.getJob(selectedJob).getCourse().getName();
			if (rm.getJob(selectedJob) instanceof TAjob) {
				if (((TAjob) rm.getJob(selectedJob)).getIsLab()) {
					jobInfo = jobInfo + " | TA Lab";
				} else {
					jobInfo = jobInfo + " | TA Tutorial";
				}
			} else {
				jobInfo = jobInfo + " | Grader";
			}
			jobInfo = jobInfo + " | Instructors: ";
			for (int i = 0; i < rm.getJob(selectedJob).getCourse().getInstructors().size(); i++) {
				if (i != rm.getJob(selectedJob).getCourse().getInstructors().size() - 1) {
					jobInfo = jobInfo + rm.getJob(selectedJob).getCourse().getInstructor(i).getName() + ", ";
				} else {
					jobInfo = jobInfo + rm.getJob(selectedJob).getCourse().getInstructor(i).getName();
				}
			}
			jobInfo = jobInfo + "\n";
			jobInfo = jobInfo + "Required CGPA: " + rm.getJob(selectedJob).getRequiredCGPA()
					+ " | Required Course GPA: " + rm.getJob(selectedJob).getRequiredCourseGPA() + "\n";
			jobInfo = jobInfo + "Required Skills: " + rm.getJob(selectedJob).getRequiredSkills() + "\n";
			jobInfo = jobInfo + "Required Experience: " + rm.getJob(selectedJob).getRequiredExperience();
			jobInfo = jobInfo + "Wage ($/Hr): " + rm.getJob(selectedJob).getWage()
					+ " | Max Hours: " + rm.getJob(selectedJob).getMaxHours();
		} else {
			jobInfo = "";
		}
		jobInfoTextArea.setText(jobInfo);
		pack();
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
		if (rm.getLoggedIn() instanceof Applicant) {
			ApplicantMainPage amp = new ApplicantMainPage(rm);
			this.dispose();
			amp.setVisible(true);
		} else if(rm.getLoggedIn() instanceof Department) {
			DepartmentMainPage dmp = new DepartmentMainPage(rm);
			this.dispose();
			dmp.setVisible(true);
		}
	}
	
	protected void applyButtonActionPerformed() {
		// create and call the controller 
		TamasController tc = new TamasController(rm);
		error = null;
		if (rm.getLoggedIn() instanceof Department) {
			if (selectedApplicant < 0) {
				error = "Applicant needs to be selected!";
			}
			if (selectedJob < 0) {
				error = "Job needs to be selected!";
			}
			if (error == null) {
				try {
					tc.applyToJob(experienceTextArea.getText(), courseGPATextField.getText(), rm.getApplicant(selectedApplicant), rm.getJob(selectedJob));
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
			} 
		} else if (rm.getLoggedIn() instanceof Applicant) {
			if (selectedJob < 0) {
				error = "Job needs to be selected!";
			}
			if (error == null) {
				try {
					tc.applyToJob(experienceTextArea.getText(), courseGPATextField.getText(),(Applicant)rm.getLoggedIn(), rm.getJob(selectedJob));
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
			} 
		}
		//update visuals
		refreshData();
	}

	private void refreshData() {
	    // error
	    errorMessage.setText(error);
	    if (error == null || error.length() == 0) {
	    	jobList.removeAllItems();
	    	int i = 0;
	    	for (Job j : rm.getJobs()) {
	    		if (j.getIsApproved()) {
					if (j instanceof TAjob) {
						if (((TAjob) j).getIsLab()) {
							jobList.addItem(j.getCourse().getName() + " " + "TA Lab " + i);
						} else {
							jobList.addItem(j.getCourse().getName() + " " + "TA Tutorial " + i);
						}
					} else if (j instanceof GraderJob) {
						jobList.addItem(j.getCourse().getName() + " " + "Grader " + i);
					} 
				}
				i++;
	    	}
	    	selectedJob = -1;
	    	jobList.setSelectedIndex(selectedJob);
	    	applicantList.removeAllItems();
	    	for (Applicant a : rm.getApplicants()) {
	    		applicantList.addItem(a.getName());
	    	}
	    	selectedApplicant = -1;
	    	applicantList.setSelectedIndex(selectedApplicant);
	    	// text reset
	    	jobInfoTextArea.setText("");
	    	experienceTextArea.setText("");
	    	courseGPATextField.setText("");
	    }
	    // this is needed because the size of the window changes depending on whether an error message is shown or not
	    pack();
	}
}

