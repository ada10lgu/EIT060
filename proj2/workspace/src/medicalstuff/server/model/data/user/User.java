package medicalstuff.server.model.data.user;

import java.util.ArrayList;

public class User {
	private ArrayList<String> data;

	User(ArrayList<String> data) {
		this.data = data;
	}

	public String getName() {
		return data.get(0);
	}

	public String getSerial() {
		return data.get(2);
	}

	public int getGroup() {
		return Integer.parseInt(data.get(3));
	}
}
