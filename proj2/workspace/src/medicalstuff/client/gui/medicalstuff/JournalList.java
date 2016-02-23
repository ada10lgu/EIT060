package medicalstuff.client.gui.medicalstuff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;

import medicalstuff.client.model.ClientModel;
import medicalstuff.client.model.JournalInfo;

@SuppressWarnings("serial")
public class JournalList extends JPanel {
	private ClientModel model;
	private JList<JournalInfo> myList;
	public JournalList(ClientModel model) {
		this.model = model;
		myList = new JList<JournalInfo>();
		setBorder(BorderFactory.createTitledBorder(""));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(200, 100));
		
		add(new CreateJournalButton(model),BorderLayout.NORTH);
		add(myList,BorderLayout.CENTER);
		JournalListUpdater jlu = new JournalListUpdater();
		jlu.start();
	}
	
	public void update() {
		ArrayList<JournalInfo> journals = model.getJournals();
		myList = new JList<JournalInfo>((JournalInfo[]) journals.toArray());
		remove(myList);
		add(myList, BorderLayout.CENTER);
	}
	
	private class JournalListUpdater extends Thread{
		
		@Override
		public void run() {
			while(!isInterrupted()) {
				update();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
