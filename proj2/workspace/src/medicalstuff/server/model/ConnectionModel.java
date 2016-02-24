package medicalstuff.server.model;


import java.math.BigInteger;
import java.util.ArrayList;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.cert.X509Certificate;

import medicalstuff.general.connection.SecureClient;
import medicalstuff.general.medicalpackets.MedicalFactory;
import medicalstuff.general.medicalpackets.MedicalModel;
import medicalstuff.server.model.data.journal.JournalSnippet;
import medicalstuff.server.model.data.user.User;

public class ConnectionModel implements MedicalModel {
	private SecureClient connection;
	private ServerModel superModel;
	private SSLSocket s;

	private String[] user = new String[2];

	public ConnectionModel(SSLSocket s, ServerModel superModel) {
		this.s = s;
		connection = new SecureClient(s);
		connection.addFactory(new MedicalFactory(this));
		this.superModel = superModel;
	}

	@Override
	public User login() {
		try {
			SSLSession session = s.getSession();
			X509Certificate cert = (X509Certificate) session
					.getPeerCertificateChain()[0];
			BigInteger bi = cert.getSerialNumber();

			User u = superModel.getUser(bi.toString());

			if (u == null) {
				System.out.println("Unknown user: " + bi);
				superModel.loglogin(s, bi.toString());
			} else {
				user[0] = u.getSerial();
				user[1] = s.getInetAddress().toString();
				superModel.loglogin(user);
				return u;
			}

		} catch (SSLPeerUnverifiedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<JournalSnippet> getJournals() {
		return superModel.getJournals();
	}

	@Override
	public ArrayList<String[]> getPatients() {
		return superModel.getUsers(3);
	}

	@Override
	public boolean createJournal(String patient, String nurse) {
		return superModel.createJournal(user, patient, user[0], nurse);
	}

	@Override
	public ArrayList<String[]> getNurses() {
		return superModel.getUsers(2);
	}
}
