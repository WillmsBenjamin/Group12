package ecse321.group12.tamas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class feedbackPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					feedbackPage frame = new feedbackPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public feedbackPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel errorMessage = new JLabel("New label");
		
		JLabel courseLabel = new JLabel("Course:");
		
		JComboBox courseComboBox = new JComboBox();
		
		JLabel assignmentLabel = new JLabel("Assignment:");
		
		JComboBox assignmentComboBox = new JComboBox();
		
		JLabel lblFeedback = new JLabel("Feedback:");
		
		JTextArea feedbackTextArea = new JTextArea();
		feedbackTextArea.setLineWrap(true);
		
		JButton logOutButton = new JButton("Sign Out");
		
		JButton submitFeedbackButton = new JButton("Submit Feedback");
		
		JButton backButton = new JButton("Back");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFeedback)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(feedbackTextArea, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(courseLabel)
							.addGap(18)
							.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(assignmentLabel, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(assignmentComboBox, 0, 93, Short.MAX_VALUE)
							.addGap(68))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(backButton, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(submitFeedbackButton)
							.addContainerGap())
						.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)))
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
						.addComponent(assignmentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFeedback)
						.addComponent(feedbackTextArea, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(submitFeedbackButton)
						.addComponent(logOutButton)
						.addComponent(backButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
