package medicalstuff.server.model;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLSocket;

import medicalstuff.general.connection.ConnectionHandler;
import medicalstuff.general.connection.SecureServer;

public class ServerModel implements ConnectionHandler {

	private SecureServer ss;
	
	public ServerModel(int port, File keystore, File truststore, char[] password) throws KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		ss = new SecureServer(port, this, keystore, truststore, password);
		ss.start();
	}

	@Override
	public void addConnection(Socket s) {
	}

	@Override
	public void addSecureConnection(SSLSocket s) {
		new ConnectionModel(s, this);
	}
	
}
