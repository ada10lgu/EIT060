package medicalstuff.client;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import medicalstuff.general.connection.SecureClient;

public class CStart {
	public static void main(String[] args) throws IOException {
		char[] password = "password".toCharArray();
		
//		new ClientConnection("hanna", password, "localhost", 12345);
		

		try {
			SecureClient sc = new SecureClient("localhost", 12345, new File("hanna_key"), new File("hanna_trust"), password);
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
