package medicalstuff.client.model;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import medicalstuff.general.connection.SecureClient;

public class ClientModel {

	private String addr;
	private int port;

	private SecureClient connection;

	public ClientModel(String addr, int port) {
		this.addr = addr;
		this.port = port;
	}

	public boolean login(String username, char[] password) {

		try {
			connection = new SecureClient(addr, port, new File(username + "_key"), new File(username + "_key"),
					password);
			return true;
		} catch (UnrecoverableKeyException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException
				| CertificateException | IOException e) {
			return false;
			}
	}
}
