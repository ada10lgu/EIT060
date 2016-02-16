package medicalstuff.client.gui;

import javax.swing.JFrame;

import medicalstuff.client.gui.LoginPanel;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame {

	public ClientGUI() {
		super("Medical stuff");
		setSize(300, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new LoginPanel());
		
		setVisible(true);
		
	}

}
