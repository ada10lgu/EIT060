package medicalstuff.client.gui.medicalstuff;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import medicalstuff.client.model.ClientModel;
import medicalstuff.client.model.JournalInfo;

@SuppressWarnings("serial")
public class JournalPane extends JPanel {

	public JournalPane(ClientModel model) {
		setBorder(BorderFactory.createTitledBorder(""));
	}
	
	public void setJournal(JournalInfo ji) {
		System.out.println("Active: " + ji);
	}
}