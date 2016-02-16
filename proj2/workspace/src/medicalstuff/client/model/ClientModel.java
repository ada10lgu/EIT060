package medicalstuff.client.model;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Observable;

import medicalstuff.general.connection.SecureClient;

public class ClientModel extends Observable {

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
	
	public boolean isConnected() {
		if (connection == null)
			return false;
		return connection.isAlive();
	}
}
