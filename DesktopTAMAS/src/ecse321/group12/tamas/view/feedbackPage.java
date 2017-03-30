package ecse321.group12.tamas.view;

import java.awt.BorderLayout;
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
import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Assignment;
import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.Department;
import ecse321.group12.tamas.model.GraderJob;
import ecse321.group12.tamas.model.Instructor;
import ecse321.group12.tamas.model.Job;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.model.TAjob;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class FeedbackPage extends JFrame {

	private static final long serialVersionUID = -2329371986114703271L;
	
	private JPanel contentPane;
	private ResourceManager rm;
	
	private JLabel courseLabel;
	private JLabel assignmentLabel;
	private JLabel feedbackLabel;
	
	private JComboBox<String> assignmentComboBox;
	private JComboBox<String> courseComboBox;
	
	private JTextArea feedbackTextArea;
	
	private JButton logOutButton;
	private JButton submitFeedbackButton;
	private JButton backButton;
	
	private JCheckBox viewCheckBox;
	
	private String error;
	private JLabel errorMessage;
	
	private Integer selectedAssignment = -1;
	private Integer selectedCourse = -1;
	private List<Assignment> assignmentList = new ArrayList<Assignment>();
	private boolean isViewSelected = false;
	
	/**
	 * Create the frame.
	 */
	public FeedbackPage(ResourceManager rm) {
		this.rm = rm;
		initComponents();
	}
	public void initComponents() {
		
		setBounds(100, 100, 496, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		courseLabel = new JLabel("Course:");
		assignmentLabel = new JLabel("Assignment:");
		feedbackLabel = new JLabel("Feedback:");
		
		assignmentComboBox = new JComboBox<String>(new String[0]);
		courseComboBox = new JComboBox<String>(new String[0]);
		
		feedbackTextArea = new JTextArea();
		feedbackTextArea.setLineWrap(true);
		
		logOutButton = new JButton("Sign Out");
		submitFeedbackButton = new JButton("Submit Feedback");
		backButton = new JButton("Back");
		
		viewCheckBox = new JCheckBox("View");
		viewCheckBox.setSelected(false);
		
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(feedbackLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(feedbackTextArea, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(courseLabel)
							.addGap(18)
							.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(assignmentLabel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(assignmentComboBox, 0, 108, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(viewCheckBox)
							.addGap(21))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(submitFeedbackButton, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(courseLabel)
						.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(assignmentLabel)
						.addComponent(assignmentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(viewCheckBox))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(feedbackLabel)
						.addComponent(feedbackTextArea, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(logOutButton)
						.addComponent(backButton)
						.addComponent(submitFeedbackButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setHonorsVisibility(false);
		contentPane.setLayout(gl_contentPane);
		
		if(rm.getLoggedIn() instanceof Department) {
	    	courseComboBox.setVisible(true);
	    	courseLabel.setVisible(true);
	    	feedbackTextArea.setEditable(true);
	    	submitFeedbackButton.setVisible(true);
	    	viewCheckBox.setVisible(true);
	    } else if(rm.getLoggedIn() instanceof Applicant) {
	    	courseComboBox.setVisible(false);
	    	courseLabel.setVisible(false);
	    	feedbackTextArea.setEditable(false);
	    	submitFeedbackButton.setVisible(false);
	    	viewCheckBox.setVisible(false);
	    } else if(rm.getLoggedIn() instanceof Instructor) {
	    	courseComboBox.setVisible(true);
	    	courseLabel.setVisible(true);
	    	feedbackTextArea.setEditable(true);
	    	submitFeedbackButton.setVisible(true);
	    	viewCheckBox.setVisible(false);
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
		submitFeedbackButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitFeedbackButtonActionPerformed();
			}
		});
		assignmentComboBox.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedAssignment = cb.getSelectedIndex();
	            if(rm.getLoggedIn() instanceof Applicant) {
	            	displayFeedback();
	            } else if(rm.getLoggedIn() instanceof Department && isViewSelected==true) {
	            	displayFeedback();
	            }
	        }
	    });
		viewCheckBox.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				if (evt.getStateChange() == ItemEvent.DESELECTED) {
					isViewSelected = false;
					feedbackTextArea.setEditable(true);
			    	submitFeedbackButton.setVisible(true);
				} else if (evt.getStateChange() == ItemEvent.SELECTED) {
					isViewSelected = true;
					feedbackTextArea.setEditable(false);
			    	submitFeedbackButton.setVisible(false);
			    	if(selectedAssignment != -1) {
			    		displayFeedback();
			    	}
				}
			}
	    	
	    });
	    courseComboBox.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            JComboBox<String> cb = (JComboBox<String>) evt.getSource();
	            selectedCourse = cb.getSelectedIndex();
	            assignmentList.clear();
		    	if (selectedCourse != -1) {
					if (rm.getLoggedIn() instanceof Instructor) {
						for (Job j : ((Instructor) rm.getLoggedIn()).getCourse(selectedCourse).getJobs()) {
							if (j.getIsApproved() && !(j.getAssignment() == null)) {
								assignmentList.add(j.getAssignment());
							}
						}
						assignmentComboBox.removeAllItems();
						int i = 0;
						for (Assignment a : assignmentList) {
							if (a.getJob() instanceof TAjob) {
								if (((TAjob) a.getJob()).getIsLab()) {
									assignmentComboBox.addItem(a.getJob().getCourse().getName() + " " + "TA Lab " + i);
								} else {
									assignmentComboBox
											.addItem(a.getJob().getCourse().getName() + " " + "TA Tutorial " + i);
								}
							} else if (a.getJob() instanceof GraderJob) {
								assignmentComboBox.addItem(a.getJob().getCourse().getName() + " " + "Grader " + i);
							}
							i++;
						} 
					} else if(rm.getLoggedIn() instanceof Department) {
						for (Job j : rm.getCourse(selectedCourse).getJobs()) {
							if (j.getIsApproved() && !(j.getAssignment() == null)) {
								assignmentList.add(j.getAssignment());
							}
						}
						assignmentComboBox.removeAllItems();
						int i = 0;
						for (Assignment a : assignmentList) {
							if (a.getJob() instanceof TAjob) {
								if (((TAjob) a.getJob()).getIsLab()) {
									assignmentComboBox.addItem(a.getJob().getCourse().getName() + " " + "TA Lab " + i);
								} else {
									assignmentComboBox
											.addItem(a.getJob().getCourse().getName() + " " + "TA Tutorial " + i);
								}
							} else if (a.getJob() instanceof GraderJob) {
								assignmentComboBox.addItem(a.getJob().getCourse().getName() + " " + "Grader " + i);
							}
							i++;
						} 
					} else if(rm.getLoggedIn() instanceof Applicant) {
						for (Assignment a : ((Applicant)rm.getLoggedIn()).getAssignments()) {
							assignmentList.add(a);
						}
						assignmentComboBox.removeAllItems();
						int i = 0;
						for (Assignment a : assignmentList) {
							if (a.getJob() instanceof TAjob) {
								if (((TAjob) a.getJob()).getIsLab()) {
									assignmentComboBox.addItem(a.getJob().getCourse().getName() + " " + "TA Lab " + i);
								} else {
									assignmentComboBox
											.addItem(a.getJob().getCourse().getName() + " " + "TA Tutorial " + i);
								}
							} else if (a.getJob() instanceof GraderJob) {
								assignmentComboBox.addItem(a.getJob().getCourse().getName() + " " + "Grader " + i);
							}
							i++;
						} 
					}
					selectedAssignment = -1;
					assignmentComboBox.setSelectedIndex(selectedAssignment);
				}
	        }
	    });
	}
	
	protected void submitFeedbackButtonActionPerformed() {
		TamasController tc = new TamasController(rm);
		if (selectedCourse < 0) {
			error = "Course needs to be selected!";
		}
		if (selectedAssignment < 0) {
			error = "Assignment needs to be selected!";
		}
		if(feedbackTextArea.getText() == null || feedbackTextArea.getText() == "") {
			error = "Feedback must be written!";
		}
		if (error == null) {
			tc.submitFeedback(assignmentList.get(selectedAssignment), feedbackTextArea.getText());
		}
		refreshData();
	}
	protected void displayFeedback() {
		feedbackTextArea.setText(assignmentList.get(selectedAssignment).getFeedback());
		pack();
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
		} else if(rm.getLoggedIn() instanceof Instructor) {
			InstructorMainPage imp = new InstructorMainPage(rm);
			this.dispose();
			imp.setVisible(true);
		} else {
			ApplicantMainPage amp = new ApplicantMainPage(rm);
			this.dispose();
			amp.setVisible(true);
		}
	}
	
	protected void exitProcedure() {
	    TamasController tc = new TamasController(rm);
	    tc.logOut();
		this.dispose();
	    System.exit(0);
	}
	
	private void refreshData() {
	    // error
	    errorMessage.setText(error);
	    if (error == null || error.length() == 0) {
	    	assignmentComboBox.removeAllItems();
	    	selectedAssignment = -1;
	    	assignmentComboBox.setSelectedIndex(selectedAssignment);
	    	
	    	courseComboBox.removeAllItems();
	    	if (rm.getLoggedIn() instanceof Instructor) {
				for (Course c : ((Instructor)rm.getLoggedIn()).getCourses()) {
					courseComboBox.addItem(c.getName());
				} 
			} else {
				for (Course c : rm.getCourses()) {
					courseComboBox.addItem(c.getName());
				} 
			}
			selectedCourse = -1;
	    	courseComboBox.setSelectedIndex(selectedCourse);
	    	// text reset
	    	feedbackTextArea.setText("");
	    }
	    // this is needed because the size of the window changes depending on whether an error message is shown or not
	    pack();
	}
}
