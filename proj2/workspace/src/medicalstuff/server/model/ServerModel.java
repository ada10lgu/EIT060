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
import medicalstuff.server.model.data.log.Logger;
import medicalstuff.server.model.data.user.User;
import medicalstuff.server.model.data.user.UserList;

public class ServerModel implements ConnectionHandler {

	private SecureServer ss;
	private UserList users;
	private Logger logger;

	public ServerModel(int port, File keystore, File truststore, char[] password) throws KeyManagementException,
			UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

		System.out.print("Loading logfile...");
		logger = new Logger();
		System.out.println("done");
		
		System.out.print("Loading users...");
		users = new UserList();
		System.out.println("loaded " +users.size() + " users");

		
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

	public User getUser(String serial) {
		return users.getUser(serial);
	}

}
