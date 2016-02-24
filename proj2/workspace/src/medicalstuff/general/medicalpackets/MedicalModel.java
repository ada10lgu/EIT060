package medicalstuff.general.medicalpackets;

import java.util.ArrayList;

import medicalstuff.server.model.data.journal.JournalSnippet;
import medicalstuff.server.model.data.user.User;

public interface MedicalModel {

	
	public User login();

	public ArrayList<JournalSnippet> getJournals();

	public ArrayList<String[]>  getPatients();

	public ArrayList<String[]> getNurses();
	public boolean createJournal(String patient, String nurse);
}