package medicalstuff.client.gui.medicalstuff.journal;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import medicalstuff.client.gui.medicalstuff.JournalPane;
import medicalstuff.client.model.ClientModel;
import medicalstuff.client.model.Journal;

@SuppressWarnings("serial")
public class DisplayJournal extends JPanel {

	private ClientModel model;
	private JournalHeaderPane header;
	private EntryListPane text;

	public DisplayJournal(ClientModel model, JournalPane jp) {
		this.model = model;
		header = new JournalHeaderPane();
		text = new EntryListPane(model, jp);
		
		setLayout(new BorderLayout());
		add(header,BorderLayout.NORTH);
		add(text,BorderLayout.CENTER);

		new JournalUpdater().start();
	}

	private synchronized void update(Journal j) {
		header.setJournal(j);
		text.setJournal(j);
		System.out.println("Update");
	}

	private class JournalUpdater extends Thread {
		@Override
		public void run() {
			int lastHash = -1;
			while (!isInterrupted()) {
				Journal j = model.getJournal();
				int hash = -1;
				if (j != null)
					hash = j.hashCode();
				if (hash != lastHash) {
					update(j);
					updateUI();
				}
				lastHash = hash;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}
