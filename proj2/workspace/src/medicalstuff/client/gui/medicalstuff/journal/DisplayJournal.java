package medicalstuff.client.gui.medicalstuff.journal;

import javax.swing.JLabel;
import javax.swing.JPanel;

import medicalstuff.client.model.ClientModel;
import medicalstuff.client.model.Journal;

@SuppressWarnings("serial")
public class DisplayJournal extends JPanel {

	private ClientModel model;

	public DisplayJournal(ClientModel model) {
		this.model = model;
		add(new JLabel("fdsjk"));
		new JournalUpdater().start();
		update();
	}
	
	private synchronized void update() {
		
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
					System.out.println(hash);
					update();
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
