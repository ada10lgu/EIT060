package medicalstuff.server.model.data.journal;

import java.util.ArrayList;

public class JournalEntry {
	ArrayList<String> data;

	public JournalEntry(ArrayList<String> data) {
		this.data = data;
	}

	public int getId() {
		return Integer.parseInt(data.get(0));
	}

	public int getJournalId() {
		return Integer.parseInt(data.get(1));
	}

	public String getUser() {
		// returns serial of user that created the entry
		return data.get(2);
	}

	public String getTimeStamp() {
		return data.get(3);
	}

	public String getData() {
		return data.get(4);
	}
}