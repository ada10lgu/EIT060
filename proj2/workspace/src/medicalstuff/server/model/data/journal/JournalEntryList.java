package medicalstuff.server.model.data.journal;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import medicalstuff.general.csv.CSV;

public class JournalEntryList {
	private CSV csv;
	private ArrayList<JournalEntry> journalEntries;

	public JournalEntryList() throws IOException {
		csv = new CSV(new File("data/journalentries"));

		journalEntries = new ArrayList<>();

		for (ArrayList<String> data : csv.getData()) {
			journalEntries.add(new JournalEntry(data));
		}
	}

	public synchronized boolean addEntry(int journalId, String user, String entry) {
		ArrayList<String> data = new ArrayList<>();
		data.add(getNewId());
		data.add("" + journalId);
		data.add(user);
		data.add(getDateTime());
		data.add(entry);
		csv.getData().add(data);
		try {
			csv.save();
		} catch (IOException e) {
			return false;
		}
		journalEntries.add(new JournalEntry(data));
		return true;
	}

	private String getNewId() {
		int id = -1;
		int size = csv.getData().size();
		if (size != 0) {
			id = Integer.parseInt(csv.getData().get(size - 1).get(0));
		}
		id++;
		return "" + id;
	}

	// TODO: Duplicated code, move to static class?
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public int size() {
		return journalEntries.size();
	}

	public ArrayList<JournalEntry> getEntries(int journalId) {
		ArrayList<JournalEntry> temp = new ArrayList<JournalEntry>();
		for (JournalEntry je : journalEntries) {
			if (je.getJournalId() == journalId) {
				temp.add(je);
			}
		}
		return temp;
	}

	public void remove(int journalId) {
		ArrayList<JournalEntry> toRemove = new ArrayList<>();

		for (JournalEntry je : journalEntries) {
			if (je.getJournalId() == journalId)
				toRemove.add(je);
		}
		
		for (JournalEntry je : toRemove) {
			journalEntries.remove(je);
			csv.getData().remove(je.getCSVData());
		}
		
		try {
			csv.save();
		} catch (IOException e) {
			System.err.println("Could not save journal entries");
		}
		
	}
}
