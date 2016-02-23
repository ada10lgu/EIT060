package medicalstuff.server.model.data.journal;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import medicalstuff.general.csv.CSV;

public class JournalList {

	private CSV csv;

	public JournalList() throws IOException {
		csv = new CSV(new File("data/journal"));

	}

	public synchronized boolean addJournal(String patient) {
		ArrayList<String> data = new ArrayList<>();
		data.add(getNewID());
		data.add(patient);
		data.add(getDateTime());
		csv.getData().add(data);
		try {
			csv.save();
		} catch (IOException e) {
			return false;
		}
		return true;

	}

	public ArrayList<JournalSnippet> getJournals() {
		return null;
	}

	public Journal getJournal(int id) {
		return null;
	}

	public int size() {
		return 0;
	}

	private String getNewID() {
		int id = -1;
		int size = csv.getData().size();
		if (size != 0) {
			id = Integer.parseInt(csv.getData().get(size - 1).get(0));
		}
		id++;
		return "" + id;
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);

	}
}
