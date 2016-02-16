package medicalstuff.client.gui.medicalstuff.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
	public Menu(ClientModel model) {
		JMenu main = new JMenu("File");
		main.add(new ExitItem(model));
		
		JMenu help = new JMenu("Help");
		help.add(new ServerInfo(model));
		help.add(new About());
		
		
		add(main);
		add(help);

	}
}
