package medicalstuff.client.gui.medicalstuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class CreateJournalButton extends JButton implements ActionListener {

	private ClientModel model;

	public CreateJournalButton(ClientModel model) {
		super("New Journal");
		this.model = model;
		addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		model.getPatients();
		
		ArrayList<String[]> data = new ArrayList<>();
		
		String[] choices = { "A", "B", "C", "D", "E", "F" };
		String input = (String) JOptionPane.showInputDialog(null,
				"Choose patient", "Create Journal",
				JOptionPane.QUESTION_MESSAGE, null, // Use
													// default
													// icon
				choices, // Array of choices
				choices[1]); // Initial choice
		System.out.println(input);
	}

}
