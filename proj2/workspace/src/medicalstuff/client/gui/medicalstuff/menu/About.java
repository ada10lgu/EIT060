package medicalstuff.client.gui.medicalstuff.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class About extends JMenuItem implements ActionListener {
	
	
	public About() {
		super("About");
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder sb = new StringBuilder();
		sb.append("Medical Stuff\n");
		sb.append("Version 0.1\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("By:\n");
		sb.append("David\n");
		sb.append("Hanna\n");
		sb.append("Hannes\n");
		sb.append("Lars Gustafson (ada10lgu)\n");
		
		
		JOptionPane.showMessageDialog(null, sb.toString(),"About",JOptionPane.INFORMATION_MESSAGE);
	}

}
