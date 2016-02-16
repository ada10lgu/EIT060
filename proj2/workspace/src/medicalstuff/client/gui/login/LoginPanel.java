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
		user.setText("hanna");
		password.setText("password");
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
				int x = width / 2 - 100;
				int y = height / 2 - 150;
				center.setBounds(x, y, 200, 300);
			} else {
				center.setBounds(50, 80, 200, 300);
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
