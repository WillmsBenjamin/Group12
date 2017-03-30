package ecse321.group12.tamas.view;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.controller.DepartmentRegisteredException;
import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.UserType;
import ecse321.group12.tamas.model.ResourceManager;

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

public class DepartmentMainPage extends JFrame{
	
	private static final long serialVersionUID = -1646811647258555349L;

	private JButton postJobButton;
	private JButton manageCoursesButton;
	private JButton applyToJobsButton;
	private JButton approveJobsButton;
	private JButton offerJobsButton;
	private JButton logOutButton;
	private JButton manageFeedbackButton;
	
	private JTextArea departmentInfoTextArea;
	
	private JPanel contentPane;
	private ResourceManager rm;
	
	private String error = null;
	private JLabel errorMessage;
	
	private GroupLayout layout;
	
	/** Creates new form DepartmentMainPage */
	public DepartmentMainPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	private void initComponents() {
		
		setBounds(100, 100, 450, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	    // elements for navigating department's functions
		//TODO: add more buttons as more functions are completed.
		//TODO: add department data view in the form of multiple small unmodifiable TextAreas. Add edit check box which sets the areas to editable, and a submit button.
		postJobButton = new JButton("Post a Job");
		manageCoursesButton = new JButton("Manage Courses");
		applyToJobsButton = new JButton("Apply to Jobs");
		approveJobsButton = new JButton("Approve Jobs");
		offerJobsButton = new JButton("Offer Jobs");
		logOutButton = new JButton("Sign Out");
		manageFeedbackButton = new JButton("Feedback");
		
		departmentInfoTextArea = new JTextArea();
		departmentInfoTextArea.setEditable(false);
		departmentInfoTextArea.setColumns(10);
		departmentInfoTextArea.setLineWrap(true);
		
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
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(logOutButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(manageCoursesButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(postJobButton, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
								.addComponent(approveJobsButton, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(offerJobsButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(applyToJobsButton, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(departmentInfoTextArea, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(manageFeedbackButton, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(departmentInfoTextArea, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(manageFeedbackButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(manageCoursesButton)
						.addComponent(applyToJobsButton)
						.addComponent(postJobButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(logOutButton)
						.addComponent(offerJobsButton)
						.addComponent(approveJobsButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
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
	    manageFeedbackButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            manageFeedbackButtonActionPerformed();
	        }
	    });
	    postJobButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            postJobButtonActionPerformed();
	        }
	    });
	    manageCoursesButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            manageCoursesButtonActionPerformed();
	        }
	    });
	    applyToJobsButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            manageApplicationsButtonActionPerformed();
	        }
	    });
	    approveJobsButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            approveJobsButtonActionPerformed();
	        }
	    });
	    offerJobsButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            offerJobsButtonActionPerformed();
	        }
	    });
	}
	
	protected void manageFeedbackButtonActionPerformed() {
		FeedbackPage fp = new FeedbackPage(rm);
		this.dispose();
		fp.setVisible(true);
	}

	protected void offerJobsButtonActionPerformed() {
		OfferJobsPage ojp = new OfferJobsPage(rm);
		this.dispose();
		ojp.setVisible(true);
	}
	
	protected void approveJobsButtonActionPerformed() {
		JobApprovalPage jap = new JobApprovalPage(rm);
		this.dispose();
		jap.setVisible(true);
	}

	protected void manageApplicationsButtonActionPerformed() {
		ApplyToJobPage amp = new ApplyToJobPage(rm);
		this.dispose();
		amp.setVisible(true);
	}
	
	protected void manageCoursesButtonActionPerformed() {
		CourseManagementPage cmp = new CourseManagementPage(rm);
		this.dispose();
		cmp.setVisible(true);
	}

	protected void postJobButtonActionPerformed() {
		Calendar calobj = Calendar.getInstance();
		if (!(rm.getDepartment().getDeadline().before(calobj.getTime()))) {
			PostJobPage pjp = new PostJobPage(rm);
			this.dispose();
			pjp.setVisible(true);
			return;
		} else {
			error = "The deadline for posting a job has passed!";
		}
		refreshData();
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
	
	private void displayUserInfo() {
		String depInfo = "Welcome, ";
		depInfo = depInfo + rm.getLoggedIn().getName() + "! | ID: " + rm.getLoggedIn().getId();
		depInfo = depInfo + "\n" + "Number of Courses: " + rm.getCourses().size() + "\n";
		depInfo = depInfo + "Number of Instructors: " + rm.getInstructors().size() + "\n";
		depInfo = depInfo + "Job Posting Deadline: " + rm.getDepartment().getDeadline();
		departmentInfoTextArea.setText(depInfo);
	}
	
	private void refreshData() {
		// error
	    errorMessage.setText(error);
	    displayUserInfo();
	    pack();
	}
}
