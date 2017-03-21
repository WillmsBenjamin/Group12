package ecse321.group12.tamas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Department;
import ecse321.group12.tamas.model.GraderJob;
import ecse321.group12.tamas.model.Job;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.model.TAjob;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class jobApprovalPage extends JFrame {

	private static final long serialVersionUID = -8121199538996286182L;
	
	private ResourceManager rm;
	
	private JPanel contentPane;
	
	private JLabel jobLabel;
	
	private JComboBox<String> jobComboBox;
	
	private JLabel jobInfoLabel;
	
	private JTextArea jobInfoTextArea;
	
	private JButton approvalButton;	
	private JButton backButton;
	private JButton logOutButton;
	
	private Integer selectedJob = -1;
	private List<Job> jobList = new ArrayList<Job>();
	
	private String error = null;
	private JLabel errorMessage;

	/** Creates new form ApplicantMainPage */
	public jobApprovalPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	public void initComponents() {
		
		setBounds(100, 100, 450, 232);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		jobLabel = new JLabel("Job:");
		
		jobComboBox = new JComboBox<String>(new String[0]);
		
		jobInfoLabel = new JLabel("Job Info:");
		
		jobInfoTextArea = new JTextArea(4, 40);
		jobInfoTextArea.setEditable(false);
		
		approvalButton = new JButton("Approve");	
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(backButton, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(approvalButton, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
						.addComponent(errorMessage, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(jobLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jobInfoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(jobComboBox, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(jobInfoTextArea, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(jobLabel)
						.addComponent(jobComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(jobInfoLabel)
						.addComponent(jobInfoTextArea, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(approvalButton)
						.addComponent(logOutButton)
						.addComponent(backButton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
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
		approvalButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				approvalButtonActionPerformed();
			}
		});
		jobComboBox.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedJob = cb.getSelectedIndex();
	            displayJobInfo();
	        }
	    });
	}

	protected void approvalButtonActionPerformed() {
		error = null;
		if (selectedJob < 0) {
			error = "Job needs to be selected!";
		}
		if (error == null) {
			jobList.get(selectedJob).setIsApproved(true);
		} 
		//update visuals
		refreshData();
	}

	protected void exitProcedure() {
	    TamasController tc = new TamasController(rm);
	    tc.logOut();
		this.dispose();
	    System.exit(0);
	}
	
	protected void logOutButtonActionPerformed() {
		TamasController tc = new TamasController(rm);
		LogInPage lip = new LogInPage(rm);
		tc.logOut();
		this.dispose();
		lip.setVisible(true);
	}
	
	protected void backButtonActionPerformed() {
		DepartmentMainPage dmp = new DepartmentMainPage(rm);
		this.dispose();
		dmp.setVisible(true);
	}
	
	protected void displayJobInfo() {
		String jobInfo;
		if (selectedJob >= 0) {
			jobInfo = jobList.get(selectedJob).getCourse().getName();
			if (rm.getJob(selectedJob) instanceof TAjob) {
				if (((TAjob) jobList.get(selectedJob)).getIsLab()) {
					jobInfo = jobInfo + " | TA Lab";
				} else {
					jobInfo = jobInfo + " | TA Tutorial";
				}
			} else {
				jobInfo = jobInfo + " | Grader";
			}
			jobInfo = jobInfo + " | Instructors: ";
			for (int i = 0; i < jobList.get(selectedJob).getCourse().getInstructors().size(); i++) {
				if (i != jobList.get(selectedJob).getCourse().getInstructors().size() - 1) {
					jobInfo = jobInfo + jobList.get(selectedJob).getCourse().getInstructor(i).getName() + ", ";
				} else {
					jobInfo = jobInfo + jobList.get(selectedJob).getCourse().getInstructor(i).getName() + "\n";
				}
			}
			jobInfo = jobInfo + "Required CGPA: " + jobList.get(selectedJob).getRequiredCGPA()
					+ " | Required Course GPA: " + jobList.get(selectedJob).getRequiredCourseGPA() + "\n";
			jobInfo = jobInfo + "Required Skills: " + jobList.get(selectedJob).getRequiredSkills() + "\n";
			jobInfo = jobInfo + "Required Experience: " + jobList.get(selectedJob).getRequiredExperience();
		} else {
			jobInfo = "";
		}
		jobInfoTextArea.setText(jobInfo);
		pack();
	}
	
	private void refreshData() {
	    // error
	    errorMessage.setText(error);
	    if (error == null || error.length() == 0) {
	    	jobList.clear();
	    	for(Job j : rm.getJobs()) {
	    		if(!j.getIsApproved()) {
	    			jobList.add(j);
	    		}
	    	}
	    	jobComboBox.removeAllItems();
	    	int i = 0;
	    	for (Job j : jobList) {
	    		if (j instanceof TAjob) {
	    			if (((TAjob) j).getIsLab()) {
	    				jobComboBox.addItem(j.getCourse().getName() + " " + "TA Lab " + i);
	    			} else {
	    				jobComboBox.addItem(j.getCourse().getName() + " " + "TA Tutorial " + i);
	    			}
	    		} else if (j instanceof GraderJob) {
	    			jobComboBox.addItem(j.getCourse().getName() + " " + "Grader " + i);
	    		} 
				i++;
	    	}
	    	selectedJob = -1;
	    	jobComboBox.setSelectedIndex(selectedJob);
	    	// text reset
	    	jobInfoTextArea.setText("");
	    }
	    // this is needed because the size of the window changes depending on whether an error message is shown or not
	    pack();
	}
}
