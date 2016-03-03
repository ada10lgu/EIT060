package medicalstuff.client.gui.medicalstuff.journal;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import medicalstuff.client.model.Journal;

@SuppressWarnings("serial")
public class JournalHeaderPane extends JPanel {

	private JLabel doctor;
	private JLabel nurse;
	private JLabel patient;
	private JLabel pnr;
	private JLabel created;
	private JLabel id;

	public JournalHeaderPane() {
		setBorder(BorderFactory.createTitledBorder("General Information"));

		doctor = new JLabel();
		nurse = new JLabel();
		patient = new JLabel();
		pnr = new JLabel();
		created = new JLabel();
		id = new JLabel();

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel("Journal #"), c);
		c.gridx = 1;
		c.weightx = 100;
		add(id, c);

		c.anchor = GridBagConstraints.CENTER;
		c.gridy = 1;
		c.gridx = 0;
		c.weightx = 1;
		add(new JLabel("Patient:"), c);
		c.gridx = 1;
		add(patient, c);

		c.gridy = 2;
		c.gridx = 0;
		c.weightx = 1;
		add(new JLabel("Personal number:"), c);
		c.gridx = 1;
		add(pnr, c);

		c.gridy = 0;
		c.gridx = 3;
		add(new JLabel("Created:"), c);
		c.gridy = 1;
		add(created, c);

		c.gridy = 2;
		c.gridx = 3;
		add(new JLabel("Doctor:"), c);
		c.gridy = 3;
		add(doctor, c);

		c.gridy = 4;
		c.gridx = 3;
		add(new JLabel("Nurse:"), c);
		c.gridy = 5;
		add(nurse, c);

		updateUI();
	}

	public void setJournal(Journal j) {
		if (j == null) {
			doctor.setText("");
			nurse.setText("");
			patient.setText("");
			pnr.setText("");
			created.setText("");
			id.setText("");
		} else {
			doctor.setText(j.doctor);
			nurse.setText(j.nurse);
			patient.setText(j.patient);
			pnr.setText(j.pnr);
			created.setText(j.created);
			id.setText("" + j.id);
		}
		updateUI();
	}
}
