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
import medicalstuff.general.medicalpackets.chat.ChatFactory;
import medicalstuff.general.medicalpackets.chat.ChatModel;
import medicalstuff.server.model.data.journal.JournalSnippet;
import medicalstuff.server.model.data.user.User;

public class ConnectionModel implements ChatModel, MedicalModel {
	private SecureClient connection;
	private ServerModel superModel;
	private SSLSocket s;

	public ConnectionModel(SSLSocket s, ServerModel superModel) {
		this.s = s;
		connection = new SecureClient(s);
		connection.addFactory(new ChatFactory(this));
		connection.addFactory(new MedicalFactory(this));
		this.superModel = superModel;
	}

	@Override
	public String chat(String message) {
		StringBuilder sb = new StringBuilder(message).reverse();
		return sb.toString();
	}

	@Override
	public User login() {
		try {
			SSLSession session = s.getSession();
			X509Certificate cert = (X509Certificate) session.getPeerCertificateChain()[0];
			BigInteger bi = cert.getSerialNumber();

			
			User u = superModel.getUser(bi.toString());
	
			if (u == null) {
				System.out.println("Unknown user: " +bi);
				superModel.loglogin(s,bi.toString());
			} else {
				superModel.loglogin(u.getSerial());
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
}
