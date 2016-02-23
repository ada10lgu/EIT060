package medicalstuff.server.model.data.journal;

import java.util.ArrayList;

public class Journal {
	
	private ArrayList<String> data;
	
	public Journal(ArrayList<String> data) {
		this.data = data;
	}
	
	public String getPatient() {
		return data.get(1);
	}

	public int getID() {
		return Integer.parseInt(data.get(0));
	}

}
