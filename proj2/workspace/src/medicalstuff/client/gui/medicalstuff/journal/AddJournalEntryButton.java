package medicalstuff.client.gui.medicalstuff.journal;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class AddJournalEntryButton extends JButton implements ActionListener {
	private ClientModel model;
	
	public AddJournalEntryButton(ClientModel model) {
		super("Add journal entry");
		
		this.model = model;
		
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextArea txtEntry = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(txtEntry, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(600, 400));
		int result = JOptionPane.showConfirmDialog(null, scrollPane, "Enter journal entry:",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			boolean b = model.addJournalEntry(txtEntry.getText());
			if (!b)
				JOptionPane.showMessageDialog(null, "Could not create entry");
		}
	}

}
