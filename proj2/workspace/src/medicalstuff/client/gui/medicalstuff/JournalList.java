package medicalstuff.client.gui.medicalstuff;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;

import medicalstuff.client.model.ClientModel;

@SuppressWarnings("serial")
public class JournalList extends JPanel {

	public JournalList(ClientModel model) {
		setBorder(BorderFactory.createTitledBorder(""));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(200, 100));

		String[] data = { "one", "two", "three", "four" };
		JList<String> myList = new JList<String>(data);
		
		add(new CreateJournalButton(model),BorderLayout.NORTH);
		add(myList,BorderLayout.CENTER);
	}
}
