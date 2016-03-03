package medicalstuff.client.gui.medicalstuff.journal;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import medicalstuff.client.model.Journal;
import medicalstuff.client.model.JournalEntry;

@SuppressWarnings("serial")
public class EntryListPane extends JPanel {

	public EntryListPane() {
		setLayout(new GridBagLayout());
	}

	public void setJournal(Journal j) {
		removeAll();

		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
		c.gridx = 0;
		c.gridy = 0;

		for (JournalEntry je : j.getJournalEntries()) {
			add(new EntryPane(je), c);
			c.gridy++;
		}
		updateUI();

	}

	private class EntryPane extends JPanel {
		public EntryPane(JournalEntry je) {
			setBorder(BorderFactory.createTitledBorder(je.getTimeStamp()));
			add(new JLabel(je.getEntry()));
		}
	}
}
