package medicalstuff.client.gui.medicalstuff;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import medicalstuff.client.model.ClientModel;
import medicalstuff.client.model.JournalInfo;

@SuppressWarnings("serial")
public class JournalPane extends JPanel {

	private ClientModel model;
	
	public JournalPane(ClientModel model) {
		setBorder(BorderFactory.createTitledBorder(""));
		this.model = model;
	}
	
	public void setJournal(JournalInfo ji) {
		model.getJournal(ji.getID());
	}
}