package medicalstuff.client.gui.medicalstuff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;

import medicalstuff.client.model.ClientModel;
import medicalstuff.client.model.JournalInfo;

@SuppressWarnings("serial")
public class JournalList extends JPanel implements MouseListener {
	private ClientModel model;
	private JList<JournalInfo> myList;
	private int oldHash;

	public JournalList(ClientModel model) {
		this.model = model;
		myList = new JList<JournalInfo>();
		setBorder(BorderFactory.createTitledBorder(""));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(200, 100));

		add(new CreateJournalButton(model), BorderLayout.NORTH);
		add(myList, BorderLayout.CENTER);
		new JournalListUpdater().start();
	}

	public synchronized void update() {
		ArrayList<JournalInfo> journals = model.getJournals();
		JournalInfo[] temp = new JournalInfo[journals.size()];
		for (int i = 0; i < journals.size(); i++) {
			temp[i] = journals.get(i);
		}

		int newHash = journals.hashCode();
		if (newHash != oldHash) {
			remove(myList);
			myList = new JList<JournalInfo>(temp);
			myList.addMouseListener(this);
			add(myList, BorderLayout.CENTER);
			updateUI();
			oldHash = newHash;
		}
	}

	private class JournalListUpdater extends Thread {

		@Override
		public void run() {
			while (!isInterrupted()) {
				update();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		@SuppressWarnings("unchecked")
		JList<JournalInfo> list = (JList<JournalInfo>) evt.getSource();
		if (evt.getClickCount() == 2) {
			JournalInfo ji = list.getSelectedValue();
			System.out.println(ji);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
