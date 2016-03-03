package medicalstuff.client.gui.medicalstuff.journal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import medicalstuff.client.gui.medicalstuff.JournalPane;

@SuppressWarnings("serial")
public class AddJournalEntryButton extends JButton implements ActionListener {
	private JournalPane jp;
	
	public AddJournalEntryButton(JournalPane jp) {
		super("Add journal entry");
		
		this.jp = jp;
		
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		jp.addJournalEntry();
	}

}
