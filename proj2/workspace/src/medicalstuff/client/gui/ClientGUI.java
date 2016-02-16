package medicalstuff.client.gui;

import javax.swing.JFrame;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame {

	public ClientGUI(ClientModel model) {
		super("Medical stuff");
		setSize(300, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new LoginPanel(model));
		
		setVisible(true);
		
	}

}
