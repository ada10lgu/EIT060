package medicalstuff.server.model.other;

public class Nurse {
	
	public String nurseName;
	public HospitalDivision hospitalName;
	
	public Nurse(String nurseName, HospitalDivision hospitalName){
		this.nurseName = nurseName;
		this.hospitalName = hospitalName;
	}

}