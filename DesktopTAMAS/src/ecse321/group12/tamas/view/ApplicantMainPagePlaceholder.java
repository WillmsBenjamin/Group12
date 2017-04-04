package ecse321.group12.tamas.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ApplicantMainPagePlaceholder extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicantMainPagePlaceholder frame = new ApplicantMainPagePlaceholder();
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
	public ApplicantMainPagePlaceholder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel errorMessage = new JLabel("New label");
		
		JButton manageApplicationsButton = new JButton("Manage Applications");
		manageApplicationsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton logOutButton = new JButton("Sign Out");
		
		JButton manageJobOffersButton = new JButton("Manage Job Offers");
		
		JButton manageFeedbackButton = new JButton("Feedback");
		
		JScrollPane applicantInfoScrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(applicantInfoScrollPane, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
							.addContainerGap())
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
							.addComponent(manageApplicationsButton, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
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
					.addComponent(applicantInfoScrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(manageApplicationsButton)
						.addComponent(manageJobOffersButton)
						.addComponent(manageFeedbackButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(logOutButton)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JTextArea applicantInfoTextArea = new JTextArea();
		applicantInfoTextArea.setWrapStyleWord(true);
		applicantInfoTextArea.setLineWrap(true);
		applicantInfoTextArea.setEditable(false);
		applicantInfoScrollPane.setViewportView(applicantInfoTextArea);
		contentPane.setLayout(gl_contentPane);
	}

}
