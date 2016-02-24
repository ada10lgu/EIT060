package medicalstuff.client.gui.medicalstuff.journal;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import medicalstuff.client.model.Journal;

@SuppressWarnings("serial")
public class JournalHeaderPane extends JPanel {

	private Journal j = null;;

	private JLabel doctor;
	private JLabel nurse;
	private JLabel patient;
	private JLabel created;
	private JLabel id;

	public JournalHeaderPane() {
		setBorder(BorderFactory.createTitledBorder("General Information"));

		doctor = new JLabel();
		nurse = new JLabel();
		patient = new JLabel();
		created = new JLabel();
		id = new JLabel();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		
		add(new JLabel("Journal #"),c);
		
		
		updateUI();
	}

	public void setJournal(Journal j) {
		doctor.setText(j.doctor);
		nurse.setText(j.nurse);
		patient.setText(j.patient);
		created.setText(j.created);
		id.setText(""+j.id);
		updateUI();
	}
}
