package medicalstuff.server.model.data.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import medicalstuff.general.csv.CSV;

public class UserList {

	private CSV csv;
	private ArrayList<User> users;
	
	
	public UserList() throws IOException {
		csv = new CSV(new File("data/users"));
		users = new ArrayList<>();
		
		for (ArrayList<String> data : csv.getData()) {
			User u = new User(data);
			users.add(u);
		}
	}
	
	public void addUser(String name, String serial, int group) throws IOException {
		ArrayList<String> data = new ArrayList<>();
		data.add(name);
		data.add(serial);
		data.add("" + group);
		csv.getData().add(data);
		csv.save();
		users.add(new User(data));
	}

	public User getUser(String serial) {
		for (User u : users)
			if (u.getSerial().equals(serial))
				return u;
		return null;
	}
	
	public int size() {
		return users.size();
	}
	
	
	
	
	
}
