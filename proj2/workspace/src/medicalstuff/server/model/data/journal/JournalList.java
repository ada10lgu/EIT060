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

	private ArrayList<Journal> journals;

	public JournalList() throws IOException {
		csv = new CSV(new File("data/journal"));

		journals = new ArrayList<>();

		for (ArrayList<String> data : csv.getData())
			journals.add(new Journal(data));
	}

	public synchronized boolean addJournal(String patient, String doctor, String nurse) {
		ArrayList<String> data = new ArrayList<>();
		data.add(getNewId());
		data.add(patient);
		data.add(doctor);
		data.add(nurse);
		data.add(getDateTime());
		csv.getData().add(data);
		try {
			csv.save();
		} catch (IOException e) {
			return false;
		}
		journals.add(new Journal(data));
		return true;

	}

	public ArrayList<JournalSnippet> getJournals() {
		ArrayList<JournalSnippet> snippets = new ArrayList<>();
		for (Journal j : journals)
			snippets.add(new JournalSnippet(j.getPatient(), j.getId()));
		return snippets;
	}

	public Journal getJournal(int id) {
		for (Journal j : journals)
			if (j.getId() == id)
				return j;
		return null;
	}

	public int size() {
		return journals.size();
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
}
