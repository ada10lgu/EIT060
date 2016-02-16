package medicalstuff.client.gui.medicalstuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class MedicalStuff extends JPanel implements ActionListener {

	private ClientModel model;

	public MedicalStuff(ClientModel model) {
		this.model = model;

		JButton button = new JButton("Log out");
		button.addActionListener(this);
		add(button);
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
