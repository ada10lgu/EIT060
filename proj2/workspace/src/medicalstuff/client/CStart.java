package medicalstuff.client;

import java.io.IOException;

public class CStart {
	public static void main(String[] args) throws IOException {
		char[] password = "password".toCharArray();
		
		new ClientConnection("hanna", password, "localhost", 12345);
		
		//din dator = localhost = 127.0.0.1 ::
	}
}
