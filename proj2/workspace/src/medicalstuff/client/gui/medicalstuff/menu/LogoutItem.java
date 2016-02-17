package medicalstuff.client.gui.medicalstuff.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class LogoutItem  extends JMenuItem implements ActionListener{

	private ClientModel model;
	
	public LogoutItem(ClientModel model) {
		super("Logout");
		this.model = model;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			model.logout();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error when loggin out");
		}
	}
}
