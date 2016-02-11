package medicalstuff.server;

import java.util.ArrayList;

public class Journal {

	public Patient patientName;
	public ArrayList<JournalEntry> journal;

	public Journal(Patient patientName){
		this.patientName = patientName;
		ArrayList<JournalEntry> journal = new ArrayList<JournalEntry>();

	}
	/**
	 * @param responsibleNurse
	 * @param responsibleDoctor
	 * @param hospital
	 * @param medicalData
	 * Creates a journal entry and puts into the journal
	 */
	public void addEntry(Nurse responsibleNurse, Doctor responsibleDoctor,
			HospitalDivision hospital, MedicalData medicalData){

		journal.add(new JournalEntry(responsibleNurse,responsibleDoctor,hospital,
				medicalData));
		
	}

	public Patient getPatientName(){
		return patientName;		
	}
}

