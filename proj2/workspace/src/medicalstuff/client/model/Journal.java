package medicalstuff.client.model;

public class Journal {
	public final int id;
	public final String patient;
	public final String pnr;
	public final String doctor;
	public final String nurse;
	public final String created;

	public Journal(int id, String patient, String pnr, String doctor, String nurse, String created) {
		this.id = id;
		this.patient = patient;
		this.pnr = pnr;
		this.doctor = doctor;
		this.nurse = nurse;
		this.created = created;
	}

	@Override
	public int hashCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("Journal-");
		sb.append(id).append("-");
		sb.append(patient).append("-");
		sb.append(doctor).append("-");
		sb.append(nurse).append("-");
		sb.append(created).append("-");

		return sb.toString().hashCode();
	}
}
