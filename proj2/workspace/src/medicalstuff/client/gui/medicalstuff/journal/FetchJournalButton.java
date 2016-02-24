package medicalstuff.client.gui.medicalstuff.journal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import medicalstuff.client.gui.medicalstuff.JournalPane;

@SuppressWarnings("serial")
public class FetchJournalButton extends JButton implements ActionListener {

	private JournalPane jp;
	
	public FetchJournalButton(JournalPane jp) {
		super("Request journal");
		this.jp = jp;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		jp.showJournal();
	}
	
}
