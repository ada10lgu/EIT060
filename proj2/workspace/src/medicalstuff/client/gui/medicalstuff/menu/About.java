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
		sb.append("Version 1.0\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("By:\n");
		sb.append("David Norrestam (fys11dno)\n");
		sb.append("Hanna Andreason (bte12han)\n");
		sb.append("Hannes Fornell (cek11hfo)\n");
		sb.append("Lars Gustafson (ada10lgu)\n");
		
		JOptionPane.showMessageDialog(null, sb.toString(),"About",JOptionPane.INFORMATION_MESSAGE);
	}

}
