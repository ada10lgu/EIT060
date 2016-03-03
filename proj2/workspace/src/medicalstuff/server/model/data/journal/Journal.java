package medicalstuff.server.model.data.journal;

import java.util.ArrayList;

public class Journal {
	private ArrayList<String> data;

	public Journal(ArrayList<String> data) {
		this.data = data;
	}

	public int getId() {
		return Integer.parseInt(data.get(0));
	}

	public String getPatient() {
		return data.get(1);
	}

	public String getDoctor() {
		return data.get(2);
	}

	public String getNurse() {
		return data.get(3);
	}

	public String getDate() {
		return data.get(4);
	}

	@Override
	public String toString() {
		return "Patient:" + getPatient() + " Doctor:" + getDoctor() + " Nurse:" + getNurse();
	}

	ArrayList<String> getData() {
		return data;
	}
}
