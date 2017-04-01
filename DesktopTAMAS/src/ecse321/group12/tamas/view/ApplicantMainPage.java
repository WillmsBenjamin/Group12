package ecse321.group12.tamas.view;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.controller.DepartmentRegisteredException;
import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.UserType;
import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Application;
import ecse321.group12.tamas.model.Assignment;
import ecse321.group12.tamas.model.GraderJob;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.model.TAjob;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class ApplicantMainPage extends JFrame{
	
	private static final long serialVersionUID = -1646811647253165349L;

	private JButton manageApplicationsButton;
	private JButton logOutButton;
	private JButton manageJobOffersButton;
	private JButton manageFeedbackButton;
	
	private JTextArea applicantInfoTextArea;
	
	private ResourceManager rm;
	private JPanel contentPane;
	
	private String error = null;
	private JLabel errorMessage;
	
	/** Creates new form ApplicantMainPage */
	public ApplicantMainPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	private void initComponents() {
		
		setBounds(100, 100, 493, 273);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
	    // elements for navigating applicant's functions
		manageApplicationsButton = new JButton("Apply to Jobs");
		logOutButton = new JButton("Sign Out");
		manageJobOffersButton = new JButton("Manage Job Offers");
		manageFeedbackButton = new JButton("Feedback");
		
		applicantInfoTextArea = new JTextArea();
		applicantInfoTextArea.setLineWrap(true);
		applicantInfoTextArea.setEditable(false);
		
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
	    GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(applicantInfoTextArea, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
											.addGap(19))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(manageFeedbackButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
											.addComponent(manageJobOffersButton)
											.addGap(18)))
									.addComponent(manageApplicationsButton, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(logOutButton, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
							.addGap(344))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(applicantInfoTextArea, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(manageApplicationsButton)
						.addComponent(manageJobOffersButton)
						.addComponent(manageFeedbackButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(logOutButton)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);

	    this.setLocationRelativeTo(null);
	    pack();
	    refreshData();
	    
	    manageFeedbackButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            manageFeedbackButtonActionPerformed();
	        }
	    });
	    logOutButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            logOutButtonActionPerformed();
	        }
	    });
	    manageApplicationsButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            manageApplicationsButtonActionPerformed();
	        }
	    });
	}
	
	protected void manageFeedbackButtonActionPerformed() {
		FeedbackPage fp = new FeedbackPage(rm);
		this.dispose();
		fp.setVisible(true);
	}
	
	protected void manageApplicationsButtonActionPerformed() {
		ApplyToJobPage amp = new ApplyToJobPage(rm);
		this.dispose();
		amp.setVisible(true);
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
	
	private void displayApplicantInfo() {
		String appInfo = "Welcome, ";
		appInfo = appInfo + rm.getLoggedIn().getName() + "! | ID: " + rm.getLoggedIn().getId() + "\n";
		appInfo = appInfo + "CGPA: " + ((Applicant)rm.getLoggedIn()).getCGPA() + " | Number of Applications: " + ((Applicant)rm.getLoggedIn()).getApplications().size() + "\n";
		appInfo = appInfo + "Number of Job Offers: ";
		int numOffs = 0;
		for(Application a : ((Applicant)rm.getLoggedIn()).getApplications()) {
			if(a.getIsOffered()) {
				numOffs++;
			}
		}
		appInfo = appInfo + numOffs + "\n";
		appInfo = appInfo + "Skills: " + ((Applicant)rm.getLoggedIn()).getSkills() + "\n" + "Assignments: ";
		int i = 0;
		for(Assignment a : ((Applicant)rm.getLoggedIn()).getAssignments()) {
			i++;
			if (a.getJob() instanceof TAjob) {
				if (((TAjob)a.getJob()).getIsLab()) {
					appInfo = appInfo + a.getJob().getCourse().getName() + " " + "TA Lab";
				} else {
					appInfo = appInfo + a.getJob().getCourse().getName() + " " + "TA Tutorial";
				}
			} else if (a.getJob() instanceof GraderJob) {
				appInfo = appInfo + a.getJob().getCourse().getName() + " " + "Grader";
			}
			if(i < ((Applicant)rm.getLoggedIn()).getAssignments().size()) {
				appInfo = appInfo + ", ";
			}
		}
		applicantInfoTextArea.setText(appInfo);
	}
	
	private void refreshData() {
		// error
	    errorMessage.setText(error);
	    displayApplicantInfo();
	    pack();
	}
}
