package ecse321.group12.tamas.view;

import javax.swing.JFrame;

import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.Instructor;
import ecse321.group12.tamas.model.ResourceManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.WindowConstants;

public class InstructorMainPage extends JFrame{
	
	private static final long serialVersionUID = -3213812345258555349L;

	private JButton postJobButton;
	private JButton logOutButton;
	private JButton manageFeedbackButton;
	
	private JTextArea instructorInfoTextArea;
	
	private ResourceManager rm;
	private JPanel contentPane;
	
	private String error = null;
	private JLabel errorMessage;
	
	/** Creates new form InstructorMainPage */
	public InstructorMainPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	private void initComponents() {
	    // elements for navigating instructor's functions
		setBounds(100, 100, 377, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		postJobButton = new JButton("Post a Job");
		logOutButton = new JButton("Sign Out");
		manageFeedbackButton = new JButton("Feedback");
		
		instructorInfoTextArea = new JTextArea();
		instructorInfoTextArea.setLineWrap(true);
		instructorInfoTextArea.setEditable(false);
		
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(errorMessage, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(instructorInfoTextArea, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(postJobButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(manageFeedbackButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(instructorInfoTextArea, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(logOutButton)
						.addComponent(postJobButton)
						.addComponent(manageFeedbackButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
	    postJobButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            postJobButtonActionPerformed();
	        }
	    });
	}
	
	protected void manageFeedbackButtonActionPerformed() {
		ManageFeedbackPage fp = new ManageFeedbackPage(rm);
		this.dispose();
		fp.setVisible(true);
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
	
	protected void displayInstructorInfo() {
		String info = "Welcome, " + rm.getLoggedIn().getName() + "! | ID: " + rm.getLoggedIn().getId() + "\n";
		info = info + "Your Courses: ";
		int i = 0;
		for(Course c : ((Instructor)rm.getLoggedIn()).getCourses()) {
			i++;
			info = info + c.getName();
			if(i < ((Instructor)rm.getLoggedIn()).getCourses().size()) {
				info = info + ", ";
			} else {
				info = info + "\n";
			}
		}
		info = info + "Job Posting Deadline: " + rm.getDepartment().getDeadline();
		instructorInfoTextArea.setText(info);
	}
	
	private void refreshData() {
		// error
	    errorMessage.setText(error);
	    
	    displayInstructorInfo();
	    pack();
	}
}

