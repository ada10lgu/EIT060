package medicalstuff.server;

public class Doctor {
	
	public String doctorName;
	public HospitalDivision hospitalName;
	
	public Doctor(String doctorName, HospitalDivision hospitalName){
		this.doctorName = doctorName;
		this.hospitalName = hospitalName;
	}
}
