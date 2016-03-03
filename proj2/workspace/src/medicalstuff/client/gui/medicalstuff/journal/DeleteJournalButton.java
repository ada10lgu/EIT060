package medicalstuff.client.gui.medicalstuff.journal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class DeleteJournalButton extends JButton implements ActionListener {

	private ClientModel model;

	public DeleteJournalButton(ClientModel model) {
		super("Remove journal");
		this.model = model;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.removeJournal();
	}
}