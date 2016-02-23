package medicalstuff.client.gui.medicalstuff;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class MedicalStuff extends JPanel implements ActionListener {

	private ClientModel model;

	public MedicalStuff(ClientModel model) {
		this.model = model;
		setBorder(BorderFactory.createTitledBorder(model.getName()));
		
		setLayout(new BorderLayout());
		
		add(new JournalList(model),BorderLayout.WEST);
		add(new JournalPane(model),BorderLayout.CENTER);
		
//		JButton button = new JButton("Log out");
//		button.addActionListener(this);
//		add(button);
//		add(new ChatBox(model));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			model.logout();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error while logging off");
		}
	}
}
