package medicalstuff.client.gui.medicalstuff.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class Menu extends JMenuBar {
	public Menu(ClientModel model) {
		JMenu main = new JMenu("File");
		main.setMnemonic(KeyEvent.VK_F);
		main.add(new LogoutItem(model));
		main.addSeparator();
		main.add(new ExitItem(model));
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		help.add(new ServerInfo(model));
		help.add(new About());
		
		add(main);
		add(help);
	}
}
