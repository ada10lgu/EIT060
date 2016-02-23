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
		
		//model.getPatients();
		
		ArrayList<String[]> data = new ArrayList<>();
		String[] patient1 = {"Patient1", "1"};
		String[] patient2 = {"Patient2", "2"};
		String[] patient3 = {"Patient3", "3"};
		
		data.add(patient1);
		data.add(patient2);
		data.add(patient3);
		
		String[] choices = new String[data.size()];
		for(int i = 0; i < data.size(); i++) {
			choices[i] = data.get(i)[0];
		}
		String input = (String) JOptionPane.showInputDialog(null,
				"Choose patient", "Create Journal",
				JOptionPane.QUESTION_MESSAGE, null, // Use
													// default
													// icon
				choices, // Array of choices
				choices[0]); // Initial choice
		System.out.println(input);
	}

}
