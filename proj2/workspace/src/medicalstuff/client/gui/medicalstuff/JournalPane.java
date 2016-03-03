package medicalstuff.client.gui.medicalstuff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import medicalstuff.client.gui.medicalstuff.journal.AddJournalEntryButton;
import medicalstuff.client.gui.medicalstuff.journal.DisplayJournal;
import medicalstuff.client.gui.medicalstuff.journal.FetchJournalButton;
import medicalstuff.client.model.ClientModel;
import medicalstuff.client.model.Journal;
import medicalstuff.client.model.JournalEntry;
import medicalstuff.client.model.JournalInfo;

@SuppressWarnings("serial")
public class JournalPane extends JPanel {

	private ClientModel model;
	private int activeJournal = -1;
	private FetchJournalButton button;
	private DisplayJournal display;
	private JPanel entryPanel;
	
	public JournalPane(ClientModel model) {
		setBorder(BorderFactory.createTitledBorder(""));
		this.model = model;
		entryPanel = new JPanel();
		entryPanel.setLayout(new FlowLayout());
		button = new FetchJournalButton(this);
		display = new DisplayJournal(model, this);
		add(new JLabel("Welcome"),BorderLayout.CENTER);
		add(entryPanel, BorderLayout.SOUTH);
		entryPanel.add(new JLabel("TESTTEST"));
	}
	
	public void setJournal(JournalInfo ji) {
		if (ji.getID() == activeJournal)
			return;
		
		activeJournal = ji.getID();
		removeAll();
		setLayout(new FlowLayout());
		add(button,BorderLayout.CENTER);
		updateUI();
	}

	public void showJournal() {
		model.setActiveJournal(activeJournal);
		Journal j = model.getJournal();
		ArrayList<JournalEntry> jEntries = j.getJournalEntries();
		setJournalEntries(jEntries);
		refresh();
	}
	
	public void refresh() {
		removeAll(); 
		setLayout(new BorderLayout());
		add(display,BorderLayout.CENTER);
		add(entryPanel, BorderLayout.SOUTH);
		add(new AddJournalEntryButton(this), BorderLayout.WEST);
		updateUI();
	}
	
	
	public void setJournalEntries(ArrayList<JournalEntry> journalEntries) {
		entryPanel.removeAll();
		for(JournalEntry entry : journalEntries) {
			JPanel temp = new JPanel();
			temp.setLayout(new BorderLayout());
			temp.add(new JLabel(entry.getTimeStamp()), BorderLayout.WEST);
			temp.add(new JLabel(entry.getUserName()), BorderLayout.EAST);
			temp.add(new JLabel(entry.getEntry()), BorderLayout.CENTER);
			entryPanel.add(temp);
		}
	}
	
	public void addJournalEntry() {
		JTextArea txtEntry = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(txtEntry,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(600, 400));
		int result = JOptionPane.showConfirmDialog(null, scrollPane, "Enter journal entry:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			boolean b = model.addJournalEntry(activeJournal, txtEntry.getText());
			if(b) {
				refresh();
			} else {
				System.out.println("DEBUG: Failed");
			}
		}
	}
}