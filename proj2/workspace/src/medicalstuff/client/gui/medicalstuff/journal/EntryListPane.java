package medicalstuff.client.gui.medicalstuff.journal;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import medicalstuff.client.model.ClientModel;
import medicalstuff.client.model.Journal;
import medicalstuff.client.model.JournalEntry;

@SuppressWarnings("serial")
public class EntryListPane extends JPanel {

	private AddJournalEntryButton button;
	private ClientModel model;

	public EntryListPane(ClientModel model) {
		this.model = model;
		setLayout(new GridBagLayout());
		button = new AddJournalEntryButton(model);
	}

	public void setJournal(Journal j) {
		removeAll();

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
		c.gridx = 0;
		c.gridy = 0;

		if (j.doctor.equals(model.getName()) || j.nurse.equals(model.getName())) {
			add(button, c);
			c.gridy++;
		}
		for (JournalEntry je : j.getJournalEntries()) {
			add(new EntryPane(je), c);
			c.gridy++;
		}
		updateUI();

	}

	private class EntryPane extends JPanel {
		public EntryPane(JournalEntry je) {
			setBorder(BorderFactory.createTitledBorder(je.getTimeStamp() + " by " + je.getUserName()));
			add(new JLabel(je.getEntry()));
		}
	}
}
