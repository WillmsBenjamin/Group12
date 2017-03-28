package ecse321.group12.tamas.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.Application;
import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.Department;
import ecse321.group12.tamas.model.GraderJob;
import ecse321.group12.tamas.model.Job;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.model.TAjob;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class OfferJobsPage extends JFrame {

	private static final long serialVersionUID = 9055837201225197595L;

	private JPanel contentPane;

	private ResourceManager rm;
	
	private JLabel courseLabel;
	private JLabel jobLabel;
	private JLabel applicationLabel;
	private JLabel filterLabel;
	private JLabel jobInfoLabel;
	private JLabel applicationInfoLabel;
	
	private JComboBox<String> courseComboBox;
	private JComboBox<String> jobComboBox;
	private JComboBox<String> applicationComboBox;

	private JCheckBox labTutCheckBox;	
	private JCheckBox gradCheckBox;
	private boolean isLabTutChecked;
	private boolean isGradChecked;

	private JTextArea jobInfoTextArea;
	private JTextArea applicationInfoTextArea;

	private JButton offerJobButton;
	private JButton logOutButton;
	private JButton backButton;
	private JButton rejectApplicationButton;
	
	private Integer selectedJob = -1;
	private Integer selectedCourse = -1;
	private Integer selectedApplication = -1;
	private List<Job> jobList = new ArrayList<Job>();
	private ArrayList<ArrayList<Application>> applicationLists = new ArrayList<ArrayList<Application>>();
	private Integer selectedList = 0;
	
	private String error = null;
	private JLabel errorMessage;
	
	/** Creates new form OfferJobsPage */
	public OfferJobsPage(ResourceManager rm) {
		this.rm = rm;
	    initComponents();
	}
	
	public void initComponents() {
		
		setBounds(100, 100, 456, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		errorMessage = new JLabel("New label");
		
		courseLabel = new JLabel("Course:");
		courseLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		courseComboBox = new JComboBox<String>(new String[0]);
		
		jobLabel = new JLabel("Job:");
		jobLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		jobComboBox = new JComboBox<String>(new String[0]);
		
		applicationLabel = new JLabel("Application:");
		
		applicationComboBox = new JComboBox<String>(new String[0]);
		
		filterLabel = new JLabel("Application Filter:");
		
		labTutCheckBox = new JCheckBox("Lab and Tutorial");
		labTutCheckBox.setSelected(true);
		isLabTutChecked = true;
		
		gradCheckBox = new JCheckBox("Graduate Students");
		gradCheckBox.setSelected(true);
		isGradChecked = true;
		
		jobInfoLabel = new JLabel("Job Info:");
		
		jobInfoTextArea = new JTextArea();
		jobInfoTextArea.setEditable(false);
		jobInfoTextArea.setLineWrap(true);
		
		applicationInfoLabel = new JLabel("Application Info:");
		
		applicationInfoTextArea = new JTextArea();
		applicationInfoTextArea.setEditable(false);
		
		offerJobButton = new JButton("Offer Job");
		logOutButton = new JButton("Sign Out");
		backButton = new JButton("Back");
		rejectApplicationButton = new JButton("Reject Application");
		
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
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(errorMessage, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(courseLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
										.addComponent(applicationLabel))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(applicationComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(courseComboBox, 0, 80, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(jobLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jobComboBox, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(filterLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(gradCheckBox)
												.addComponent(labTutCheckBox)))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(jobInfoLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jobInfoTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
							.addGap(9))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(applicationInfoLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(applicationInfoTextArea, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(backButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rejectApplicationButton, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(offerJobButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(courseLabel)
						.addComponent(jobLabel)
						.addComponent(jobComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(applicationLabel)
						.addComponent(applicationComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(filterLabel)
						.addComponent(labTutCheckBox))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gradCheckBox)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(jobInfoLabel)
						.addComponent(jobInfoTextArea, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(applicationInfoLabel)
						.addComponent(applicationInfoTextArea, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(offerJobButton)
						.addComponent(logOutButton)
						.addComponent(rejectApplicationButton)
						.addComponent(backButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
		offerJobButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				offerJobButtonActionPerformed();
			}
		});
		rejectApplicationButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				rejectApplicationButtonActionPerformed();
			}
		});
		jobComboBox.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedJob = cb.getSelectedIndex();
	            displayJobInfo();
	            TamasController tc = new TamasController(rm);
	            if (selectedJob != -1) {
					applicationLists = tc.rankApplications(rm.getCourse(selectedCourse).getJob(selectedJob));
				}
				listApplications();
	        }
	    });
		courseComboBox.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedCourse = cb.getSelectedIndex();
	            jobList.clear();
		    	if (selectedCourse != -1) {
					for (Job j : rm.getCourse(selectedCourse).getJobs()) {
						if (j.getIsApproved()) {
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
		applicationComboBox.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedApplication = cb.getSelectedIndex();
	            displayApplicationInfo();
	        }
	    });
		labTutCheckBox.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				if (evt.getStateChange() == ItemEvent.DESELECTED) {
					isLabTutChecked = false;
				} else if (evt.getStateChange() == ItemEvent.SELECTED) {
					isLabTutChecked = true;
				}
				listApplications();
			}
	    });
		gradCheckBox.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				if (evt.getStateChange() == ItemEvent.DESELECTED) {
					isGradChecked = false;
				} else if (evt.getStateChange() == ItemEvent.SELECTED) {
					isGradChecked = true;
				}
				listApplications();
			}
	    });
	}
	
	protected void rejectApplicationButtonActionPerformed() {
		TamasController tc = new TamasController(rm);
		error = null;
		if (selectedApplication < 0) {
			error = "Application needs to be selected!";
		}
		if(applicationLists.get(selectedList).get(selectedApplication).getIsOffered()) {
			error = "This application has already been offered the job!";
		}
		if (error == null) {
			tc.rejectApplication(applicationLists.get(selectedList).get(selectedApplication));
		} 
		//update visuals
		refreshData();
	}

	protected void listApplications() {
		if (isLabTutChecked && isGradChecked) {
			selectedList = 0;
		} else if (isLabTutChecked && !isGradChecked) {
			selectedList = 1;
		} else if (!isLabTutChecked && isGradChecked) {
			selectedList = 2;
		} else {
			selectedList = 3;
		}
		applicationComboBox.removeAllItems();
    	if (selectedJob != -1) {
			if ((applicationLists.get(selectedList) != null) || (applicationLists.get(selectedList).size() != 0)) {
				for (Application a : applicationLists.get(selectedList)) {
					applicationComboBox.addItem(a.getApplicant().getId());
				}
			} 
		}
		selectedApplication = -1;
    	applicationComboBox.setSelectedIndex(selectedApplication);
	}

	protected void offerJobButtonActionPerformed() {
		TamasController tc = new TamasController(rm);
		error = null;
		if (selectedApplication < 0) {
			error = "Application needs to be selected!";
		}
		if(applicationLists.get(selectedList).get(selectedApplication).getIsOffered()) {
			error = "This application has already been offered the job!";
		}
		if (error == null) {
			tc.offerJob(applicationLists.get(selectedList).get(selectedApplication));
		} 
		//update visuals
		refreshData();
	}

	protected void displayApplicationInfo() {
		String applicationInfo = "";
		if (selectedApplication >= 0) {
			applicationInfo = applicationInfo + applicationLists.get(selectedList).get(selectedApplication).getApplicant().getName();
			applicationInfo = applicationInfo + " | CGPA: " + applicationLists.get(selectedList).get(selectedApplication).getApplicant().getCGPA();
			applicationInfo = applicationInfo + " | Course GPA: " + applicationLists.get(selectedList).get(selectedApplication).getCourseGPA() + "\n";
			applicationInfo = applicationInfo + "Skills: " + applicationLists.get(selectedList).get(selectedApplication).getApplicant().getSkills() + "\n";
			applicationInfo = applicationInfo + "Experience: " + applicationLists.get(selectedList).get(selectedApplication).getExperience();
		} else {
			applicationInfo = "";
		}
		applicationInfoTextArea.setText(applicationInfo);
		pack();
		
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
		if (rm.getLoggedIn() instanceof Department) {
			DepartmentMainPage dmp = new DepartmentMainPage(rm);
			this.dispose();
			dmp.setVisible(true);
		} else {
			InstructorMainPage imp = new InstructorMainPage(rm);
			this.dispose();
			imp.setVisible(true);
		}
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
	    	
	    	applicationComboBox.removeAllItems();
	    	if ((applicationLists != null) && (applicationLists.size() != 0)) {
				if ((applicationLists.get(selectedList) != null) && (applicationLists.get(selectedList).size() != 0)) {
					for (Application a : applicationLists.get(selectedList)) {
						courseComboBox.addItem(a.getApplicant().getId());
					}
				} 
			}
			selectedApplication = -1;
	    	applicationComboBox.setSelectedIndex(selectedApplication);
	    	// text reset
	    	jobInfoTextArea.setText("");
	    	applicationInfoTextArea.setText("");
	    }
	    // this is needed because the size of the window changes depending on whether an error message is shown or not
	    pack();
	}
}
