package medicalstuff.client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import medicalstuff.client.gui.login.LoginPanel;
import medicalstuff.client.gui.medicalstuff.MedicalStuff;
import medicalstuff.client.gui.medicalstuff.menu.Menu;
import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class ClientGUI extends JFrame implements Observer {

	public static final int LOGIN_WIDTH = 300;
	public static final int LOGIN_HEIGHT = 500;
	public static final int MEDICAL_WIDTH = 800;
	public static final int MEDICAL_HEIGHT = 600;

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

		setSize(LOGIN_WIDTH, LOGIN_HEIGHT);
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
			Dimension d = null;
			if (status) {
				main.add(new MedicalStuff(model));
				setJMenuBar(new Menu(model));
				d = new Dimension(MEDICAL_WIDTH, MEDICAL_HEIGHT);
				setResizable(true);
			} else {
				main.add(lp);
				setJMenuBar(null);
				d = new Dimension(LOGIN_WIDTH,LOGIN_HEIGHT);
				pack();
				setResizable(false);
			}
			setPreferredSize(d);
			setMinimumSize(d);
			setMaximumSize(d);
			main.updateUI();
			main.repaint();
		}
		online = status;
	}

}
