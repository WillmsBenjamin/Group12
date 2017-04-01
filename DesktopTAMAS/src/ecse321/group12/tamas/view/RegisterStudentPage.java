package ecse321.group12.tamas.view;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.model.ResourceManager;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.event.ItemEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


public class RegisterStudentPage extends JFrame {

	private static final long serialVersionUID = -3813819647258666349L;
	
	private Boolean isGraduateChecked;

	private JTextField nameTextField;
	private JTextField idTextField;
	private JTextField cGPATextField;
	private JTextArea skillsTextArea;
	private JCheckBox isGraduateCheckBox;
	private JLabel nameLabel;
	private JLabel idLabel;
	private JLabel cGPALabel;
	private JLabel skillsLabel;
	private JButton registerButton;
	private JButton backButton;
	
	private JScrollPane skillsScrollPane;
	
	private ResourceManager rm;
	
	private String error = null;
	private JLabel errorMessage;
	
	/** Creates new form RegisterStudentPage */
	public RegisterStudentPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	private void initComponents() {
	    // elements for registering and switching views
		nameTextField = new JTextField();
		idTextField = new JTextField();
		cGPATextField = new JTextField();
		skillsTextArea = new JTextArea(7, 30);
		skillsTextArea.setWrapStyleWord(true);
		skillsTextArea.setLineWrap(true);
		nameLabel = new JLabel("name:");
		idLabel = new JLabel("Id:");
		cGPALabel = new JLabel("CGPA:");
		skillsLabel = new JLabel("skills:");
		registerButton = new JButton("Register");
		backButton = new JButton("Back");
		isGraduateCheckBox = new JCheckBox("Graduate?");
		isGraduateCheckBox.setSelected(false);
		isGraduateChecked = false;
		
		skillsScrollPane = new JScrollPane();
	    
	    // elements for error messages
	    errorMessage = new JLabel();
	    errorMessage.setForeground(Color.RED);

	    // global settings and listeners
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("TAMAS: REGISTER STUDENT");

	    // layout
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    

	    layout.setHorizontalGroup(
	    	layout.createParallelGroup()
	    	.addComponent(errorMessage)
	    	.addGroup(layout.createSequentialGroup()
	    		.addComponent(nameLabel)
	    		.addComponent(nameTextField, 200, 200, 400))
	    	.addGroup(layout.createSequentialGroup()
		    	.addComponent(idLabel)
		    	.addComponent(idTextField, 200, 200, 400)
		    	.addComponent(isGraduateCheckBox))
	    	.addGroup(layout.createSequentialGroup()
	    		.addComponent(cGPALabel)
	    		.addComponent(cGPATextField, 200, 200, 400))
	    	.addGroup(layout.createSequentialGroup()
	    		.addComponent(skillsLabel)
	    		.addComponent(skillsScrollPane))
	    	.addGroup(layout.createSequentialGroup()
	    		.addComponent(backButton)
		    	.addComponent(registerButton))
	        );

	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {backButton, registerButton, cGPATextField, idTextField, nameTextField});
	    layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {nameLabel, idLabel, cGPALabel, skillsLabel});

	    layout.setVerticalGroup(
	    	layout.createSequentialGroup()
		    .addComponent(errorMessage)
		    .addGroup(layout.createParallelGroup()
		    	.addComponent(nameLabel, Alignment.CENTER)
		    	.addComponent(nameTextField))
		    .addGroup(layout.createParallelGroup()
			   	.addComponent(idLabel, Alignment.CENTER)
			   	.addComponent(idTextField)
			   	.addComponent(isGraduateCheckBox, Alignment.CENTER))
		    .addGroup(layout.createParallelGroup()
		    	.addComponent(cGPALabel, Alignment.CENTER)
		    	.addComponent(cGPATextField))
		    .addGroup(layout.createParallelGroup()
		    	.addComponent(skillsLabel, Alignment.CENTER)
		    	.addComponent(skillsScrollPane))
		    .addGroup(layout.createParallelGroup()
		    	.addComponent(backButton)
			   	.addComponent(registerButton))
	    	);
	    skillsScrollPane.setViewportView(skillsTextArea);
	    
	    this.setLocationRelativeTo(null);
	    pack();
	    
	    backButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            backButtonActionPerformed();
	        }
	    });
	    registerButton.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            registerButtonActionPerformed();
	        }
	    });
	    isGraduateCheckBox.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(ItemEvent evt) {
				if (evt.getStateChange() == ItemEvent.DESELECTED) {
					isGraduateChecked = false;
				} else if (evt.getStateChange() == ItemEvent.SELECTED) {
					isGraduateChecked = true;
				}
			}
	    	
	    });
	}
	
	protected void registerButtonActionPerformed() {
		// create and call the controller
		TamasController tc = new TamasController(rm);
		error = null;
		try {
			tc.registerApplicant(nameTextField.getText(), idTextField.getText(), cGPATextField.getText(), skillsTextArea.getText(), isGraduateChecked);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//update visuals
		refreshData();
	}

	protected void backButtonActionPerformed() {
		LogInPage lip = new LogInPage(rm);
		this.dispose();
		lip.setVisible(true);
	}

	private void refreshData() {
		// error
	    errorMessage.setText(error);
	    if (error == null || error.length() == 0) {
	        nameTextField.setText("");
	        idTextField.setText("");
	        cGPATextField.setText("");
	        skillsTextArea.setText("");
	        isGraduateCheckBox.setSelected(false);
	        isGraduateChecked = false;
	    }
	    pack();
	}
}
