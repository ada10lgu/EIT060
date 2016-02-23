package medicalstuff.client.gui.medicalstuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class CreateJournalButton extends JButton implements ActionListener {

	private ClientModel model;

	public CreateJournalButton(ClientModel model) {
		super("New Journal");
		this.model = model;
		addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ArrayList<String[]> data = model.getPatients();
		
		PatientInfo[] choices = new PatientInfo[data.size()];
		for(int i = 0; i < data.size(); i++) {
			PatientInfo p = new PatientInfo(data.get(i));
			choices[i] = p;
		}
		
		
		PatientInfo input = (PatientInfo) JOptionPane.showInputDialog(null,
				"Choose patient", "Create Journal",
				JOptionPane.QUESTION_MESSAGE, null, // Use
													// default
													// icon
				choices, // Array of choices
				choices[0]); // Initial choice
		if (input != null) {
			model.createJournal(input.getSerial());
		}
	}
	private class PatientInfo {
		private String patientName, patientSerial;
		
		public PatientInfo(String[] patientInfo) {
			patientName = patientInfo[0];
			patientSerial = patientInfo[1];
		}
		
		public String getSerial() {
			return patientSerial;
		}
		
		@Override
		public String toString() {
			return patientName;
		}
	}
}
