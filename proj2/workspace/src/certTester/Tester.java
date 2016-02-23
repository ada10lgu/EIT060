package certTester;

import java.io.File;

public class Tester {
	public static void main(String[] args) {

		int port = 54345;

		File clientKeyStore = new File("hanna_key");
		File clientTrsutStore = new File("hanna_trust");
		String clientPassword = "password";

		File serverKeyStore = new File("serverkeystore");
		File serverTrustStore = new File("servertruststore");
		String serverPassword = "password";

		try {
			new TestServer(serverKeyStore, serverTrustStore,
					serverPassword.toCharArray(), port);
			
			Thread.sleep(200);
			try {
				TestClient.testClient(clientKeyStore, clientTrsutStore,
						"localhost", port, clientPassword.toCharArray(), true);

			} catch (Exception e) {
				System.out.println("Could not start client");
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			System.out.println("Could not start server");
			System.out.println(e.getMessage());
		}

	}
}
