package medicalstuff.client.gui.medicalstuff;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class JournalPane extends JPanel {

	public JournalPane(ClientModel model) {
		setBorder(BorderFactory.createTitledBorder(""));
		
		add(new ChatBox(model));
	}
}
