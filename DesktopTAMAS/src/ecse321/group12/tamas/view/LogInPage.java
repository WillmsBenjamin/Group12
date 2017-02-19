package ecse321.group12.tamas.view;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.UserTypes;
import ecse321.group12.tamas.model.ResourceManager;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


public class LogInPage extends JFrame {

	private static final long serialVersionUID = -3813819647258555349L;
	
	private JTextField userNameTextField;
	private JTextField logInIdTextField;
	private JLabel userNameLabel;
	private JLabel logInIdLabel;
	private JButton registerUserButton;
	
	private ResourceManager rm;
	
	private String error = null;
	private JLabel errorMessage;
	
	/** Creates new form ParticipantPage */
	public LogInPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	private void initComponents() {
	    // elements for Registering
	    userNameTextField = new JTextField();
	    userNameLabel = new JLabel();
	    registerUserButton = new JButton();
	    
	    // elements for error message
	    errorMessage = new JLabel();
	    errorMessage.setForeground(Color.RED);

	    // global settings and listeners
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("TAMAS LOGIN/REGISTER");

	    userNameLabel.setText("Name:");
	    registerUserButton.setText("Sign-up");

	    // layout
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);

	    layout.setHorizontalGroup(
	    	layout.createParallelGroup()
	        .addComponent(errorMessage)
	        .addGroup(layout.createSequentialGroup()
	        .addComponent(participantNameLabel)
	        .addGroup(layout.createParallelGroup()
	        	.addComponent(participantNameTextField, 200, 200, 400)
	        	.addComponent(addParticipantButton))
	        ));

	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addParticipantButton, participantNameTextField});

	    layout.setVerticalGroup(
	    	layout.createSequentialGroup()
	        .addComponent(errorMessage)
	        .addGroup(layout.createParallelGroup()
	        	.addComponent(participantNameLabel)
	        	.addComponent(participantNameTextField))
	        .addComponent(addParticipantButton)
	        );

	    pack();
	    
	    addParticipantButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            addParticipantButtonActionPerformed();
	        }
	    });
	}
	
	private void refreshData() {
		// error
	    errorMessage.setText(error);
	    if (error == null || error.length() == 0) {
	        // participant
	        participantNameTextField.setText("");
	    }
	    pack();
	}
	
	private void addParticipantButtonActionPerformed() {
		// create and call the controller
		EventRegistrationController erc = new EventRegistrationController(rm);
		error = null;
		try {
		    erc.createParticipant(participantNameTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//update visuals
		refreshData();
	}

}
