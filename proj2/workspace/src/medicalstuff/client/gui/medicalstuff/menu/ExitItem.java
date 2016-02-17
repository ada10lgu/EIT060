package medicalstuff.client.gui.medicalstuff.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuItem;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class ExitItem extends JMenuItem implements ActionListener {

	private ClientModel model;
	
	public ExitItem(ClientModel model) {
		super("Exit");
		this.model = model;
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			model.logout();
		} catch (IOException e1) {
			System.err.println("Could not close connection.");
		}
		System.exit(0);
	}

}
