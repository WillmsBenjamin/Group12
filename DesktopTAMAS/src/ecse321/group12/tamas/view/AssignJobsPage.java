package ecse321.group12.tamas.view;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.Application;
import ecse321.group12.tamas.model.Course;
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
import javax.swing.JScrollPane;

public class AssignJobsPage extends JFrame {

	private static final long serialVersionUID = 9055812201225197595L;

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
	
	private JTextArea applicationInfoTextArea;
	private JTextArea jobInfoTextArea;
	
	private JScrollPane jobInfoScrollPane;
	private JScrollPane applicationInfoScrollPane;
	
	private JCheckBox labTutCheckBox;	
	private JCheckBox gradCheckBox;
	private boolean isLabTutChecked;
	private boolean isGradChecked;

	private JButton assignJobButton;
	private JButton logOutButton;
	private JButton backButton;
	
	private Integer selectedJob = -1;
	private Integer selectedCourse = -1;
	private Integer selectedApplication = -1;
	private List<Job> jobList = new ArrayList<Job>();
	private ArrayList<ArrayList<Application>> applicationLists = new ArrayList<ArrayList<Application>>();
	private Integer selectedList = 0;
	
	private String error = null;
	private JLabel errorMessage;	
	
	/** Creates new form OfferJobsPage */
	public AssignJobsPage(ResourceManager rm) {
		this.rm = rm;
	    initComponents();
	}
	
	public void initComponents() {
		
		setBounds(100, 100, 474, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		courseLabel = new JLabel("Course:");
		courseLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		courseComboBox = new JComboBox<String>(new String[0]);
		
		jobLabel = new JLabel("Job:");
		jobLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		jobComboBox = new JComboBox<String>(new String[0]);
		
		applicationLabel = new JLabel("Applicant:");
		
		applicationComboBox = new JComboBox<String>(new String[0]);
		
		filterLabel = new JLabel("Applicant Filter:");
		
		labTutCheckBox = new JCheckBox("Lab and Tutorial");
		labTutCheckBox.setSelected(true);
		isLabTutChecked = true;
		
		gradCheckBox = new JCheckBox("Graduate Students");
		gradCheckBox.setSelected(true);
		isGradChecked = true;
		
		jobInfoLabel = new JLabel("Job Info:");
		
		applicationInfoLabel = new JLabel("Application Info:");
		
		assignJobButton = new JButton("Assign Job");
		logOutButton = new JButton("Sign Out");
		backButton = new JButton("Back");
		
		jobInfoScrollPane = new JScrollPane();
		applicationInfoScrollPane = new JScrollPane();
		
		// elements for error message
	    errorMessage = new JLabel();
	    errorMessage .setForeground(Color.RED);
	    
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(courseLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
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
											.addComponent(jobComboBox, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(filterLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(gradCheckBox)
												.addComponent(labTutCheckBox)))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(jobInfoLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(jobInfoScrollPane)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(9))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(applicationInfoLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(applicationInfoScrollPane, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(assignJobButton, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(jobInfoLabel)
						.addComponent(jobInfoScrollPane, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(applicationInfoLabel)
						.addComponent(applicationInfoScrollPane, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(logOutButton)
						.addComponent(backButton)
						.addComponent(assignJobButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		applicationInfoTextArea = new JTextArea();
		applicationInfoTextArea.setWrapStyleWord(true);
		applicationInfoTextArea.setLineWrap(true);
		applicationInfoTextArea.setEditable(false);
		applicationInfoScrollPane.setViewportView(applicationInfoTextArea);
		
		jobInfoTextArea = new JTextArea();
		jobInfoTextArea.setEditable(false);
		jobInfoTextArea.setWrapStyleWord(true);
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
		assignJobButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				assignJobButtonActionPerformed();
			}
		});
		jobComboBox.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedJob = cb.getSelectedIndex();
	            displayJobInfo();
	            TamasController tc = new TamasController(rm);
	            if (selectedJob != -1) {
					applicationLists = tc.rankAcceptedOffers(rm.getCourse(selectedCourse).getJob(selectedJob));
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
						if (j.getIsApproved() && (j.getAssignment() == null)) {
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

	protected void assignJobButtonActionPerformed() {
		TamasController tc = new TamasController(rm);
		error = null;
		if (selectedCourse < 0) {
			error = "Course needs to be selected!";
		} else if (selectedJob < 0) {
			error = "Job needs to be selected!";
		} else if (selectedApplication < 0) {
			error = "Applicant needs to be selected!";
		}
		if (error == null) {
			if(selectedList == 0 || selectedList == 1) {
				try {
					tc.assignApplicantToJob(applicationLists.get(selectedList).get(selectedApplication));
				} catch (InvalidInputException e) {
					error = e.getMessage();
					refreshData();
					return;
				}
				boolean isLab;
				if(applicationLists.get(selectedList).get(selectedApplication).getJob() instanceof TAjob) {
					if(((TAjob)applicationLists.get(selectedList).get(selectedApplication).getJob()).getIsLab()) {
						isLab =	true;
					} else {
						isLab = false;
					}
					for(Application a : applicationLists.get(selectedList).get(selectedApplication).getApplicant().getApplications()) {
						if (isLab && !(a == applicationLists.get(selectedList).get(selectedApplication))) {
							if (a.getJob() instanceof TAjob) {
								if (a.getJob().getCourse() == applicationLists.get(selectedList)
										.get(selectedApplication).getJob().getCourse()) {
									if (!((TAjob) a.getJob()).getIsLab() && a.getIsOffered()) {
										try {
											tc.assignApplicantToJob(a);
										} catch (InvalidInputException e) {
											error = e.getMessage();
											refreshData();
											return;
										}
									}
								}
							}
						} else if (!isLab && !(a == applicationLists.get(selectedList).get(selectedApplication))) {
							if (a.getJob() instanceof TAjob) {
								if (a.getJob().getCourse() == applicationLists.get(selectedList)
										.get(selectedApplication).getJob().getCourse()) {
									if (((TAjob) a.getJob()).getIsLab() && a.getIsOffered()) {
										try {
											tc.assignApplicantToJob(a);
										} catch (InvalidInputException e) {
											error = e.getMessage();
											refreshData();
											return;
										}
									}
								}
							}
						} 
					}
				}
			} else {
				try {
					tc.assignApplicantToJob(applicationLists.get(selectedList).get(selectedApplication));
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
			} 
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
		DepartmentMainPage dmp = new DepartmentMainPage(rm);
		this.dispose();
		dmp.setVisible(true);
	}
	
	protected void displayJobInfo() {
		String jobInfo;
		if (selectedJob >= 0) {
			if (jobList.get(selectedJob) instanceof TAjob) {
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
