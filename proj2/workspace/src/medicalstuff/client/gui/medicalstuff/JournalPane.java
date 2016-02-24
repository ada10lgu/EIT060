package medicalstuff.client.gui.medicalstuff;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import medicalstuff.client.gui.medicalstuff.journal.DisplayJournal;
import medicalstuff.client.gui.medicalstuff.journal.FetchJournalButton;
import medicalstuff.client.model.ClientModel;
import medicalstuff.client.model.JournalInfo;

@SuppressWarnings("serial")
public class JournalPane extends JPanel {

	private ClientModel model;
	private int activeJournal = -1;
	private FetchJournalButton button;
	private DisplayJournal display;
	
	public JournalPane(ClientModel model) {
		setBorder(BorderFactory.createTitledBorder(""));
		this.model = model;
		button = new FetchJournalButton(this);
		display = new DisplayJournal(model);
		add(new JLabel("Welcome"),BorderLayout.CENTER);
	}
	
	public void setJournal(JournalInfo ji) {
		if (ji.getID() == activeJournal)
			return;
		
		activeJournal = ji.getID();
		removeAll();
		setLayout(new FlowLayout());
		add(button,BorderLayout.CENTER);
		updateUI();
	}

	public void showJournal() {
		model.setActiveJournal(activeJournal);
		removeAll();
		setLayout(new BorderLayout());
		add(display,BorderLayout.CENTER);
		updateUI();
	}
}