package medicalstuff.server.model;

import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;

import javax.net.ssl.SSLSocket;

import medicalstuff.general.connection.ConnectionHandler;
import medicalstuff.general.connection.SecureServer;
import medicalstuff.server.model.data.journal.Journal;
import medicalstuff.server.model.data.journal.JournalList;
import medicalstuff.server.model.data.journal.JournalSnippet;
import medicalstuff.server.model.data.log.Logger;
import medicalstuff.server.model.data.user.User;
import medicalstuff.server.model.data.user.UserList;

public class ServerModel implements ConnectionHandler {

	private SecureServer ss;
	private UserList users;
	private JournalList journals;
	private Logger logger;

	public ServerModel(int port, File keystore, File truststore,
			char[] password, boolean verbose) throws KeyManagementException,
			UnrecoverableKeyException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {

		System.out.print("Loading logfile...\t");
		logger = new Logger();
		System.out.println("done");

		System.out.print("Loading users...\t");
		users = new UserList();
		System.out.println("loaded " + users.size() + " users");

		System.out.print("Loading journals...\t");
		journals = new JournalList();
		System.out.println("loaded " + journals.size() + " journals");

		try {
			ss = new SecureServer(port, this, keystore, truststore, password,
					verbose);
			ss.start();
		} catch (BindException e) {
			System.out
					.println("Could not start server since port is allready in use.");
			System.out.println("Terminating program.");
			System.exit(-1);
		}
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

	public ArrayList<JournalSnippet> getJournals() {
		return journals.getJournals();
	}

	public Journal getJournal(String[] user, int id) {
		Journal j = journals.getJournal(id);
		logger.log(user[0], j.getPatient(), "requested journal",user[1]);
		return null;
	}

	public void loglogin(String[] user) {
		logger.log(user[0], "-1", "logged in",user[1]);
	}

	public void loglogin(SSLSocket s,String serial) {
		String info = s.toString();
		logger.log(serial, "-1", "unknown user ("+info+")",s.getInetAddress().toString());
	}

	public ArrayList<String[]> getPatients() {
		return users.getUsers(3);
	}

	public boolean createJournal(String[]user ,String patient) {
		boolean b = journals.addJournal(patient);
		logger.log(user[0], patient, "created journal", user[1]);
		return b;
	}

}
