package ecse321.group12.tamas.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class OfferJobsPage extends JFrame {

	private static final long serialVersionUID = 9055837201225197595L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfferJobsPage frame = new OfferJobsPage();
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
	public OfferJobsPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel errorMessage = new JLabel("New label");
		
		JLabel courseLabel = new JLabel("Course:");
		courseLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JComboBox courseComboBox = new JComboBox();
		
		JLabel jobLabel = new JLabel("Job:");
		jobLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JComboBox jobComboBox = new JComboBox();
		
		JLabel applicationLabel = new JLabel("Application:");
		
		JComboBox applicationComboBox = new JComboBox();
		
		JLabel filterLabel = new JLabel("Application Filter:");
		
		JCheckBox labTutCheckBox = new JCheckBox("Lab and Tutorial");
		labTutCheckBox.setSelected(true);
		
		JCheckBox gradCheckBox = new JCheckBox("Graduate Students");
		gradCheckBox.setSelected(true);
		
		JLabel jobInfoLabel = new JLabel("Job Info:");
		
		JTextArea jobInfoTextArea = new JTextArea();
		jobInfoTextArea.setEditable(false);
		jobInfoTextArea.setLineWrap(true);
		
		JLabel applicationInfoLabel = new JLabel("Application Info:");
		
		JTextArea applicationInfoTextArea = new JTextArea();
		applicationInfoTextArea.setEditable(false);
		
		JButton offerJobButton = new JButton("Offer Job");
		
		JButton logOutButton = new JButton("Sign Out");
		
		JButton backButton = new JButton("Back");
		
		JButton rejectApplication = new JButton("Reject Application");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(errorMessage, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(courseLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
										.addComponent(applicationLabel))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(applicationComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(courseComboBox, 0, 80, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(jobLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jobComboBox, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(filterLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(gradCheckBox)
												.addComponent(labTutCheckBox)))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(jobInfoLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jobInfoTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(9))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(applicationInfoLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(applicationInfoTextArea, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(backButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rejectApplication, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(offerJobButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(courseLabel)
						.addComponent(jobLabel)
						.addComponent(jobComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(applicationLabel)
						.addComponent(applicationComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(filterLabel)
						.addComponent(labTutCheckBox))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gradCheckBox)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(jobInfoLabel)
						.addComponent(jobInfoTextArea, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(applicationInfoLabel)
						.addComponent(applicationInfoTextArea, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(offerJobButton)
						.addComponent(logOutButton)
						.addComponent(rejectApplication)
						.addComponent(backButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
