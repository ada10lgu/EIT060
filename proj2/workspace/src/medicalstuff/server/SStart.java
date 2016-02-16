package medicalstuff.server;

public class SStart {
	public static void main(String[] args) {

		char[] password = "password".toCharArray();
		
		new ServerConnectionHandler(password,12345);
		
	}
}
