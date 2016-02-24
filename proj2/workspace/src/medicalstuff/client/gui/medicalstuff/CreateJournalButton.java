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
		UserInfo patient = selectUser(model.getPatients(), "Choose patient");
		if (patient != null) {
			UserInfo nurse = selectUser(model.getNurses(), "Choose nurse");
			if (nurse != null) {
				if (!model.createJournal(patient.getSerial()))
					JOptionPane.showMessageDialog(null,
							"Could not create Journal");
			}
		}

	}

	private UserInfo selectUser(ArrayList<String[]> users, String message) {
		UserInfo[] choices = new UserInfo[users.size()];
		for (int i = 0; i < users.size(); i++) {
			UserInfo p = new UserInfo(users.get(i));
			choices[i] = p;
		}
		UserInfo user = (UserInfo) JOptionPane.showInputDialog(null, message,
				"Create Journal", JOptionPane.QUESTION_MESSAGE, null, choices,
				choices[0]);
		return user;
	}

	private class UserInfo {
		private String patientName, patientSerial;

		public UserInfo(String[] patientInfo) {
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
