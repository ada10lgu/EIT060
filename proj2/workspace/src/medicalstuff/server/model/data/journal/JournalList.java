package medicalstuff.server.model.data.journal;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import medicalstuff.general.csv.CSV;
import medicalstuff.server.model.data.user.User;
import medicalstuff.server.model.data.user.UserList;

public class JournalList {

	private CSV csv;

	private ArrayList<Journal> journals;
	private UserList ul;

	public JournalList(UserList ul) throws IOException {
		this.ul = ul;

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

	public ArrayList<JournalSnippet> getJournals(User u) {
		ArrayList<JournalSnippet> snippets = new ArrayList<>();
		for (Journal j : journals) {
			JournalSnippet js = new JournalSnippet(j.getPatient(), j.getId());
			if (u.getGroup() == 0) {
				snippets.add(js);
			} else if (u.getGroup() == 1) {
				User doctor = ul.getUser(j.getDoctor());
				if (doctor.getDivision().equals(u.getDivision()))
					snippets.add(js);
			} else if (u.getGroup() == 2 && j.getNurse().equals(u.getSerial()))
				snippets.add(js);
			else if (u.getGroup() == 3 && j.getPatient().equals(u.getSerial()))
				snippets.add(js);
		}
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
		int id = 2182;
		int size = csv.getData().size();
		if (size != 0) {
			id = Integer.parseInt(csv.getData().get(size - 1).get(0));
		}
		id += new Random().nextInt(20) + 2;
		return "" + id;
	}

	// TODO: Duplicated code, move to static class?
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void remove(int journalId) {
		for (int i = 0; i < journals.size(); i++) {
			Journal j = journals.get(i);
			if (j.getId() == journalId) {
				journals.remove(j);
				csv.getData().remove(j.getData());
				try {
					csv.save();
				} catch (IOException e) {
					System.err.println("Error removing journal");
				}
			}
		}
	}
}
