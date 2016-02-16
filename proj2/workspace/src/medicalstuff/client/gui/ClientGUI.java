package medicalstuff.client.gui;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import medicalstuff.client.gui.login.LoginPanel;
import medicalstuff.client.gui.medicalstuff.MedicalStuff;
import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame implements Observer {

	private LoginPanel lp;
	private boolean online = true;
	private ClientModel model;
	private JPanel main;

	public ClientGUI(ClientModel model) {
		super("Medical stuff");

		this.model = model;
		model.addObserver(this);
		main = new JPanel(new BorderLayout());
		add(main, BorderLayout.CENTER);

		setSize(300, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		lp = new LoginPanel(model);

		setVisible(true);
		update(null, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		boolean status = model.isConnected();
		if (status != online) {
			main.removeAll();
			if (status) {
				main.add(new MedicalStuff(model));
			} else {
				main.add(lp);
			}
			main.updateUI();
			main.repaint();
		}

		online = status;
	}

}
