package ecse321.group12.tamas.view;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.ResourceManager;

public class InstructorManagementPage {

	private JDatePickerImpl contactTimeDatePicker;
	private JLabel contactTimeDateLabel;
	private JSpinner startTimeSpinner;
	private JLabel startTimeLabel;
	private JSpinner endTimeSpinner;
	private JLabel endTimeLabel;
	private JButton addContactTimeButton;
	
	private JLabel instructorLabel;
	private JComboBox<String> instructorList;
	
	private ResourceManager rm;
	
	private String error = null;
	private JLabel errorMessage;
	
	private Integer selectedInstructor = -1;
	
	/** Creates new form InstructorManagementPage */
	public InstructorManagementPage(ResourceManager rm) {
	    this.rm = rm;
	    initComponents();
	}
	
	private void initComponents() {
		SqlDateModel model = new SqlDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
	    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	    contactTimeDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	    
	    contactTimeDateLabel = new JLabel("Date:");
	    startTimeSpinner = new JSpinner( new SpinnerDateModel() );
	    JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
	    startTimeSpinner.setEditor(startTimeEditor); // will only show the current time
	    startTimeLabel = new JLabel("Start Time:");
	    endTimeSpinner = new JSpinner( new SpinnerDateModel() );
	    JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
	    endTimeSpinner.setEditor(endTimeEditor); // will only show the current time
	    endTimeLabel = new JLabel("End Time:");
		addContactTimeButton = new JButton("Add Contact Time");
		
		instructorLabel = new JLabel("Instructor:");
		instructorList = new JComboBox<String>(new String[0]);
		
	}
	
	
	protected void addContactTimeButtonActionPerformed() {
		// create and call the controller 
		TamasController tc = new TamasController(rm);
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime((Date) startTimeSpinner.getValue());
	    calendar.set(2000, 1, 1);
	    Time startTime = new Time(calendar.getTime().getTime());
	    calendar.setTime((Date) endTimeSpinner.getValue());
	    calendar.set(2000, 1, 1);
	    Time endTime = new Time(calendar.getTime().getTime());
		error = null;
		try {
			tc.addContactTime((java.sql.Date) contactTimeDatePicker.getModel().getValue(), startTime, endTime, rm.getInstructor(selectedInstructor));
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		//update visuals
		refreshData();
	}
}
