package medicalstuff.server.model.other;

public class JournalEntry {

	public Nurse responsibleNurse;
	public Doctor responsibleDoctor;
	public HospitalDivision hospital;
	public MedicalData medicalData;
	
	public JournalEntry(Nurse responsibleNurse, Doctor responsibleDoctor,
			HospitalDivision hospital, MedicalData medicalData){
		this.responsibleNurse=responsibleNurse;
		this.responsibleDoctor=responsibleDoctor;
		this.hospital=hospital;
		this.medicalData=medicalData;
	}
	
	public Nurse getResponsibleNurse(){
	return responsibleNurse;	
	}

	public Doctor getResponsibleDoctor(){
		return responsibleDoctor;	
	}

	public HospitalDivision getHospital(){
		return hospital;	
	}

	public MedicalData getMedicalData(){
		return medicalData;
	}
}
