package medicalstuff.client.gui.medicalstuff.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class ServerInfo extends JMenuItem implements ActionListener{

	private ClientModel model;
	
	public ServerInfo(ClientModel model) {
		super("Server Information");
		addActionListener(this);
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		HashMap<String,String> data = model.getServerInfo();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Server information\n");
		sb.append("Serial: ").append(data.get("serial")).append("\n");
		sb.append("Subject: ").append(data.get("subject")).append("\n");
		sb.append("Issuer: ").append(data.get("issuer")).append("\n");
		
		JOptionPane.showMessageDialog(null, sb.toString(),"Server Information",JOptionPane.INFORMATION_MESSAGE);
	}
	
	

}
