package medicalstuff.server.model.data.journal;

public class JournalSnippet {
	private String user;
	private int id;
	
	public JournalSnippet(String user, int id) {
		this.user = user;
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}
	
	public int getID() {
		return id;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return user;
	}
}
