package ecse321.group12.tamas.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.GraderJob;
import ecse321.group12.tamas.model.Job;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.model.TAjob;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;

public class JobApprovalPage extends JFrame {

	private static final long serialVersionUID = -8121199538996286182L;
	
	private ResourceManager rm;
	
	private JPanel contentPane;
	
	private JLabel jobLabel;
	private JLabel courseLabel;
	
	private JComboBox<String> jobComboBox;
	private JComboBox<String> courseComboBox;
	
	private JLabel jobInfoLabel;
	private JLabel courseInfoLabel;
	private JTextArea courseInfoTextArea;
	private JTextArea jobInfoTextArea;
	
	private JScrollPane jobInfoScrollPane;
	
	private JButton approvalButton;	
	private JButton backButton;
	private JButton logOutButton;
	
	private Integer selectedJob = -1;
	private Integer selectedCourse = -1;
	private List<Job> jobList = new ArrayList<Job>();
	
	private String error = null;
	private JLabel errorMessage;

	/** Creates new form JobApprovalPage */
	public JobApprovalPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	public void initComponents() {
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		jobLabel = new JLabel("Job:");
		courseLabel = new JLabel("Course:");
		
		jobComboBox = new JComboBox<String>(new String[0]);
		courseComboBox = new JComboBox<String>(new String[0]);
		
		jobInfoLabel = new JLabel("Job Info:");
		courseInfoLabel = new JLabel("Course Info:");
		
		courseInfoTextArea = new JTextArea();
		courseInfoTextArea.setWrapStyleWord(true);
		courseInfoTextArea.setEditable(false);
		courseInfoTextArea.setLineWrap(true);
		
		jobInfoScrollPane = new JScrollPane();
		
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
		
	    //layout
	    GroupLayout gl_contentPane = new GroupLayout(contentPane);
	    gl_contentPane.setHorizontalGroup(
	    	gl_contentPane.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
	    					.addContainerGap())
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    						.addGroup(gl_contentPane.createSequentialGroup()
	    							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
	    								.addComponent(jobInfoLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    								.addComponent(courseInfoLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    								.addComponent(jobInfoScrollPane, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
	    								.addComponent(courseInfoTextArea, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)))
	    						.addGroup(gl_contentPane.createSequentialGroup()
	    							.addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(approvalButton, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
	    					.addContainerGap())
	    				.addGroup(gl_contentPane.createSequentialGroup()
	    					.addComponent(courseLabel, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
	    					.addPreferredGap(ComponentPlacement.UNRELATED)
	    					.addComponent(jobLabel)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(jobComboBox, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
	    					.addGap(119))))
	    );
	    gl_contentPane.setVerticalGroup(
	    	gl_contentPane.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_contentPane.createSequentialGroup()
	    			.addComponent(errorMessage)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(jobLabel)
	    				.addComponent(jobComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(courseLabel))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(courseInfoLabel)
	    				.addComponent(courseInfoTextArea, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    				.addComponent(jobInfoLabel)
	    				.addComponent(jobInfoScrollPane, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	    					.addComponent(approvalButton)
	    					.addComponent(backButton))
	    				.addComponent(logOutButton))
	    			.addContainerGap())
	    );
	    
	    jobInfoTextArea = new JTextArea();
	    jobInfoTextArea.setWrapStyleWord(true);
	    jobInfoTextArea.setEditable(false);
	    jobInfoTextArea.setLineWrap(true);
	    jobInfoScrollPane.setViewportView(jobInfoTextArea);
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
		courseComboBox.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedCourse = cb.getSelectedIndex();
	            displayCourseInfo();
	            jobList.clear();
		    	if (selectedCourse != -1) {
					for (Job j : rm.getCourse(selectedCourse).getJobs()) {
						if (!j.getIsApproved()) {
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
				}
	        }
	    });
	}

	protected void displayCourseInfo() {
		String courseInfo;
		if (selectedCourse >= 0) {
			courseInfo = "Total Budget: $" + rm.getCourse(selectedCourse).getBudget();
			courseInfo = courseInfo + " | Remaining Budget: $";
			int usedBudget = 0;
			for(Job j : rm.getCourse(selectedCourse).getJobs()) {
				if (j.getIsApproved()) {
					usedBudget += (int) (j.getMaxHours() * j.getWage());
				}
			}
			courseInfo = courseInfo + (rm.getCourse(selectedCourse).getBudget()-usedBudget) + "\n";
			courseInfo = courseInfo + "Number of Students: " + rm.getCourse(selectedCourse).getNumStudents();
			courseInfo = courseInfo + " | Number of Labs: " + rm.getCourse(selectedCourse).getNumLabSections() + "\n";
			courseInfo = courseInfo + "Number of Tutorials: " + rm.getCourse(selectedCourse).getNumTutorialSections();
		} else {
			courseInfo = "";
		}
		courseInfoTextArea.setText(courseInfo);
		pack();
	}

	protected void approvalButtonActionPerformed() {
		TamasController tc = new TamasController(rm);
		error = null;
		if (selectedJob < 0) {
			error = "Job needs to be selected!";
		}
		if (error == null) {
			tc.approveJob(jobList.get(selectedJob));
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
			if (rm.getJob(selectedJob) instanceof TAjob) {
				if (((TAjob) jobList.get(selectedJob)).getIsLab()) {
					jobInfo ="TA Lab";
				} else {
					jobInfo ="TA Tutorial";
				}
			} else {
				jobInfo = "Grader";
			}
			jobInfo = jobInfo + " | Instructors: ";
			for (int i = 0; i < jobList.get(selectedJob).getCourse().getInstructors().size(); i++) {
				if (i != jobList.get(selectedJob).getCourse().getInstructors().size() - 1) {
					jobInfo = jobInfo + jobList.get(selectedJob).getCourse().getInstructor(i).getName() + ", ";
				} else {
					jobInfo = jobInfo + jobList.get(selectedJob).getCourse().getInstructor(i).getName();
				}
			}
			jobInfo = jobInfo + "\n";
			jobInfo = jobInfo + "Required CGPA: " + jobList.get(selectedJob).getRequiredCGPA()
					+ " | Required Course GPA: " + jobList.get(selectedJob).getRequiredCourseGPA() + "\n";
			jobInfo = jobInfo + "Required Skills: " + jobList.get(selectedJob).getRequiredSkills() + "\n";
			jobInfo = jobInfo + "Required Experience: " + jobList.get(selectedJob).getRequiredExperience() + "\n";
			jobInfo = jobInfo + "Wage ($/Hr): " + jobList.get(selectedJob).getWage()
					+ " | Max Hours: " + jobList.get(selectedJob).getMaxHours();
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
	    	jobComboBox.removeAllItems();
	    	selectedJob = -1;
	    	jobComboBox.setSelectedIndex(selectedJob);
	    	
	    	courseComboBox.removeAllItems();
	    	for(Course c : rm.getCourses()) {
	    		courseComboBox.addItem(c.getName());
	    	}
	    	selectedCourse = -1;
	    	courseComboBox.setSelectedIndex(selectedCourse);
	    	// text reset
	    	jobInfoTextArea.setText("");
	    	courseInfoTextArea.setText("");
	    }
	    // this is needed because the size of the window changes depending on whether an error message is shown or not
	    pack();
	}
}
