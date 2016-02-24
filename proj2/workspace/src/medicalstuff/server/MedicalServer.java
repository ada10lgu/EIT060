package medicalstuff.server;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import medicalstuff.server.model.ServerModel;

public class MedicalServer {
	public static void main(String[] args) throws KeyManagementException,
			UnrecoverableKeyException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {

		char[] password = "password".toCharArray();
		int port = 12345;
		File keystore = new File("certs/server_key");
		File truststore = new File("certs/server_trust");

		new ServerModel(port, keystore, truststore, password, false);
	}
}
