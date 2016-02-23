package medicalstuff.client.model;

public class JournalInfo {
	private String user;
	private int id;
	
	public JournalInfo(String user, int id) {
		this.user = user;
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}
	
	public int getID() {
		return id;
	}
	
	@Override
	public String toString() {
		return user;
	}
}
