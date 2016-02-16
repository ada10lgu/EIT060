package medicalstuff.client.gui.medicalstuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class ChatBox extends JTextField implements ActionListener {

	private ClientModel m;
	
	public ChatBox(ClientModel m) {
		super(10);
		addActionListener(this);
		this.m = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String message = getText();
		String resonse = m.sendMessage(message);
		JOptionPane.showMessageDialog(null, resonse);
	}

}
