package medicalstuff.server.model;

import java.math.BigInteger;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.cert.X509Certificate;

import medicalstuff.general.connection.SecureClient;
import medicalstuff.general.medicalpackets.ChatFactory;
import medicalstuff.general.medicalpackets.ChatModel;
import medicalstuff.general.medicalpackets.MedicalFactory;
import medicalstuff.general.medicalpackets.MedicalModel;

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
	public String login() {
		try {
			SSLSession session = s.getSession();
			X509Certificate cert = (X509Certificate) session.getPeerCertificateChain()[0];
			BigInteger bi = cert.getSerialNumber();
			return bi.toString();
		} catch (SSLPeerUnverifiedException e) {
			e.printStackTrace();
		}
		return "";
	}
}
