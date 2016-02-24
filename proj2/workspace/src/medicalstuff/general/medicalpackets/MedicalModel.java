package medicalstuff.general.medicalpackets;

import java.util.ArrayList;

import medicalstuff.server.model.data.journal.Journal;
import medicalstuff.server.model.data.journal.JournalEntry;
import medicalstuff.server.model.data.journal.JournalSnippet;
import medicalstuff.server.model.data.user.User;

public interface MedicalModel {

	
	public User login();

	public ArrayList<JournalSnippet> getJournals();

	public Journal getJournal(int id);
	
	public ArrayList<String[]>  getPatients();

	public ArrayList<String[]> getNurses();
	
	public String getUserName(String userSerial);
	
	public ArrayList<JournalEntry> getJournalEntries(int journalId);
	 
	public boolean createJournal(String patient, String nurse);
}