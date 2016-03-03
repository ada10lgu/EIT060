package medicalstuff.client.model;

public class JournalEntry {
	private final String userName, timeStamp, entry;

	public JournalEntry(String userName, String timeStamp, String entry) {
		this.userName = userName;
		this.timeStamp = timeStamp;
		this.entry = entry;
	}

	public String getEntry() {
		return entry;
	}

	public String getUserName() {
		return userName;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	@Override
	public String toString() {
		return userName + "-" + timeStamp + "-" + entry;
	}
}
