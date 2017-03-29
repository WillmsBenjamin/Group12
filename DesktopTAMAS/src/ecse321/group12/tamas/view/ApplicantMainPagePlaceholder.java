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
		setBounds(100, 100, 493, 273);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel errorMessage = new JLabel("New label");
		
		JTextArea applicantInfoTextArea = new JTextArea();
		applicantInfoTextArea.setLineWrap(true);
		applicantInfoTextArea.setEditable(false);
		
		JButton manageApplicationsButton = new JButton("Manage Applications");
		manageApplicationsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton logOutButton = new JButton("Sign Out");
		
		JButton manageJobOffersButton = new JButton("Manage Job Offers");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(applicantInfoTextArea, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
									.addGap(19))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(logOutButton, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(manageJobOffersButton)
									.addGap(18)))
							.addComponent(manageApplicationsButton, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
					.addContainerGap())
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
						.addComponent(logOutButton))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
