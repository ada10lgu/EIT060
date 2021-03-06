package medicalstuff.client.gui.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel implements ActionListener {

	private static final int MID_WIDTH = 200;
	private static final int MID_HEIGHT = 130;
	
	private JPanel center;
	private JTextField user;
	private JPasswordField password;
	private ClientModel model;

	public LoginPanel(ClientModel model) {
		this.model = model;
		setLayout(null);
		center = new JPanel();
		center.setBackground(Color.YELLOW);

		center.setLayout(new BorderLayout());

		JPanel text = new JPanel();
		JLabel l1 = new JLabel("Username");
		JLabel l2 = new JLabel("Password");
		user = new JTextField(12);
		password = new JPasswordField(12);
		user.setText("");
		password.setText("");
		user.addActionListener(this);
		password.addActionListener(this);

		text.setLayout(new GridLayout(4, 1));
		text.add(l1);
		text.add(user);
		text.add(l2);
		text.add(password);

		JPanel button = new JPanel();
		JButton login = new JButton("Login");
		login.addActionListener(this);
		button.add(login);
		center.add(text, BorderLayout.CENTER);
		center.add(button, BorderLayout.SOUTH);
		add(center);

		repaint();
	}

	@Override
	public void repaint() {
		if (center != null) {
			int width = getWidth();
			int height = getHeight();
			if (height != 0) {
				int x = (width-MID_WIDTH) / 2;
				int y = (height-MID_HEIGHT) / 2;
				center.setBounds(x, y, MID_WIDTH, MID_HEIGHT);
			}
		}
		super.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (model.login(user.getText(), password.getPassword())) {
			user.setText("");
			password.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Could not connect");
		}
	}
}
