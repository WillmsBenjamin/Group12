package ecse321.group12.tamas.view;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Application;
import ecse321.group12.tamas.model.Department;
import ecse321.group12.tamas.model.GraderJob;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.model.TAjob;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.JButton;

public class ManageJobOffersPage extends JFrame {
	
	private static final long serialVersionUID = -3287934789209234637L;
	
	private JTextArea jobInfoTextArea;
	private JLabel jobInfoLabel;

	private JLabel applicantLabel;
	private JComboBox/*<String>*/ applicantComboBox;
	private JLabel jobLabel;
	private JComboBox/*<String>*/ jobComboBox;
	private JButton acceptOfferButton;
	private JButton backButton;
	private JButton logOutButton;
	private JButton rejectOfferButton;
	
	private JScrollPane jobInfoScrollPane;
	
	private String error = null;
	private JLabel errorMessage;
	
	private Integer selectedApplicant = -1;
	private Integer selectedJob = -1;
	
	private List<Application> offeredJobs = new ArrayList<Application>();

	private JPanel contentPane;
	private ResourceManager rm;

	
	/**
	 * Create the frame.
	 */
	public ManageJobOffersPage(ResourceManager rm) {
		this.rm = rm;
		initComponents();
	}
	
	public void initComponents() {
		
		setBounds(100, 100, 439, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		applicantLabel = new JLabel("Applicant:");
		applicantComboBox = new JComboBox()/*<String>(new String[0])*/;
		
		jobLabel = new JLabel("Job Offer:");
		jobComboBox = new JComboBox()/*<String>(new String[0])*/;

		jobInfoLabel = new JLabel("Job Info:");
		jobInfoScrollPane = new JScrollPane();
		
		logOutButton = new JButton("Sign Out");
		backButton = new JButton("Back");
		acceptOfferButton = new JButton("Accept Offer");
		rejectOfferButton = new JButton("Reject Offer");
		
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
						.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(backButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rejectOfferButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(acceptOfferButton, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(jobInfoLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jobLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(applicantLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(jobComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(applicantComboBox, 0, 93, Short.MAX_VALUE))
											.addGap(213))
										.addComponent(jobInfoScrollPane, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))))
							.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(applicantLabel)
						.addComponent(applicantComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(jobLabel)
						.addComponent(jobComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(jobInfoLabel)
						.addComponent(jobInfoScrollPane, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(logOutButton)
						.addComponent(acceptOfferButton)
						.addComponent(backButton)
						.addComponent(rejectOfferButton))
					.addContainerGap())
		);
		
		jobInfoTextArea = new JTextArea();
		jobInfoTextArea.setWrapStyleWord(true);
		jobInfoTextArea.setLineWrap(true);
		jobInfoTextArea.setEditable(false);
		jobInfoScrollPane.setViewportView(jobInfoTextArea);
		contentPane.setLayout(gl_contentPane);
		
		gl_contentPane.setHonorsVisibility(false);
	    if(rm.getLoggedIn() instanceof Department) {
	    	applicantComboBox.setVisible(true);
	    	applicantLabel.setVisible(true);
	    } else if(rm.getLoggedIn() instanceof Applicant) {
	    	applicantComboBox.setVisible(false);
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
	    acceptOfferButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	acceptOfferButtonActionPerformed();
	        }
	    });
	    rejectOfferButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	rejectOfferButtonActionPerformed();
	        }
	    });
	    applicantComboBox.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedApplicant = cb.getSelectedIndex();
	            int i = 0;
		    	if (rm.getLoggedIn() instanceof Department) {
		    		jobComboBox.removeAllItems();
			    	offeredJobs.clear();
					if (selectedApplicant != -1) {
						for (Application a : rm.getApplicant(selectedApplicant).getApplications()) {
							if (a.getIsOffered() && (a.getJob().getAssignment() == null) && !(a.getIsAccepted())) {
								if (a.getJob() instanceof TAjob) {
									if (((TAjob) a.getJob()).getIsLab()) {
										jobComboBox.addItem(a.getJob().getCourse().getName() + " " + "TA Lab " + i);
									} else {
										jobComboBox
												.addItem(a.getJob().getCourse().getName() + " " + "TA Tutorial " + i);
									}
								} else if (a.getJob() instanceof GraderJob) {
									jobComboBox.addItem(a.getJob().getCourse().getName() + " " + "Grader " + i);
								}
								offeredJobs.add(a);
								i++;
							}
						} 
					}
					selectedJob = -1;
			    	jobComboBox.setSelectedIndex(selectedJob);
				}
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
	
	protected void rejectOfferButtonActionPerformed() {
		// create and call the controller 
		TamasController tc = new TamasController(rm);
		error = null;
		if (selectedJob < 0) {
			error = "Job needs to be selected!";
		}
		if (rm.getLoggedIn() instanceof Department) {
			if (selectedApplicant < 0) {
				error = "Applicant needs to be selected!";
			}
			if (error == null) {
				tc.deleteApplication(offeredJobs.get(selectedJob));
			} 
		} else if (rm.getLoggedIn() instanceof Applicant) {
			if (error == null) {
				tc.deleteApplication(offeredJobs.get(selectedJob));
			} 
		}
		//update visuals
		refreshData();
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
			jobInfo = jobInfo + "Required Experience: " + rm.getJob(selectedJob).getRequiredExperience() + "\n";
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
	
	protected void acceptOfferButtonActionPerformed() {
		// create and call the controller 
		TamasController tc = new TamasController(rm);
		error = null;
		if (selectedJob < 0) {
			error = "Job needs to be selected!";
		}
		if (rm.getLoggedIn() instanceof Department) {
			if (selectedApplicant < 0) {
				error = "Applicant needs to be selected!";
			}
			if (error == null) {
				try {
					tc.acceptJobOffer(offeredJobs.get(selectedJob));
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
			} 
		} else if (rm.getLoggedIn() instanceof Applicant) {
			if (error == null) {
				try {
					tc.acceptJobOffer(offeredJobs.get(selectedJob));
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
	    	jobComboBox.removeAllItems();
	    	offeredJobs.clear();
	    	if(rm.getLoggedIn() instanceof Applicant) {
	    		int i = 0;
	    		for(Application a : ((Applicant)rm.getLoggedIn()).getApplications()) {
	    			if (a.getIsOffered() && (a.getJob().getAssignment() == null) && !(a.getIsAccepted())) {
	    				if (a.getJob() instanceof TAjob) {
	    					if (((TAjob)a.getJob()).getIsLab()) {
	    						jobComboBox.addItem(a.getJob().getCourse().getName() + " " + "TA Lab " + i);
	    					} else {
	    						jobComboBox.addItem(a.getJob().getCourse().getName() + " " + "TA Tutorial " + i);
	    					}
	    				} else if (a.getJob() instanceof GraderJob) {
	    					jobComboBox.addItem(a.getJob().getCourse().getName() + " " + "Grader " + i);
	    				}
	    				offeredJobs.add(a);
	    				i++;
	    			}
	    		}
	    	}
			selectedJob = -1;
	    	jobComboBox.setSelectedIndex(selectedJob);
	    	applicantComboBox.removeAllItems();
	    	for (Applicant a : rm.getApplicants()) {
	    		applicantComboBox.addItem(a.getName());
	    	}
	    	selectedApplicant = -1;
	    	applicantComboBox.setSelectedIndex(selectedApplicant);
	    	// text reset
	    	jobInfoTextArea.setText("");
	    }
	    // this is needed because the size of the window changes depending on whether an error message is shown or not
	    pack();
	}
}
