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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class InstructorMainPage extends JFrame{
	
	private static final long serialVersionUID = -3213812345258555349L;

	private JButton postJobButton;
	private JButton logOutButton;
	
	private ResourceManager rm;
	
	private String error = null;
	private JLabel errorMessage;
	
	private GroupLayout layout;
	
	/** Creates new form InstructorMainPage */
	public InstructorMainPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	private void initComponents() {
	    // elements for navigating instructor's functions
		//TODO: add more buttons as more functions are completed.
		//TODO: add instructor data view in the form of multiple small unmodifiable TextAreas. Add edit check box which sets the areas to editable, and a submit button.
		postJobButton = new JButton("Post a Job");
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
	    setTitle("TAMAS INSTRUCTOR MAIN PAGE");

	    // layout
	    layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    

	    layout.setHorizontalGroup(
	    	layout.createParallelGroup()
	        .addComponent(errorMessage)
	        .addGroup(layout.createSequentialGroup()
	        	.addComponent(logOutButton)
	        	.addComponent(postJobButton))
	        );

	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {logOutButton, postJobButton});

	    
	    layout.setVerticalGroup(
	    	layout.createSequentialGroup()
		    .addComponent(errorMessage)
		    .addGroup(layout.createParallelGroup()
		        .addComponent(logOutButton)
		        .addComponent(postJobButton))
		    );

	    this.setLocationRelativeTo(null);
	    pack();
	    
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
	
	private void refreshData() {
		// error
	    errorMessage.setText(error);
	    pack();
	}
}

